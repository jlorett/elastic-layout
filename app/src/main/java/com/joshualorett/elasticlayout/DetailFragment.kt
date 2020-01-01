package com.joshualorett.elasticlayout

import android.os.Bundle
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.HapticFeedbackConstants
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import kotlinx.android.synthetic.main.fragment_detail.*


/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment(), ElasticLayout.DismissListener, ElasticLayout.DragThresholdListener {
    private lateinit var text: String
    private lateinit var transition: Transition
    private val transitionListener= object : Transition.TransitionListener {
        override fun onTransitionEnd(transition: Transition?) {
            detailText.animate().alpha(1F)
                .setDuration(200)
                .setInterpolator(FastOutLinearInInterpolator())
        }

        override fun onTransitionResume(transition: Transition?) {
        }

        override fun onTransitionPause(transition: Transition?) {
        }

        override fun onTransitionCancel(transition: Transition?) {
            detailText.alpha = 1F
        }

        override fun onTransitionStart(transition: Transition?) {
            detailText.alpha = 0F
        }
    }

    companion object {
        const val textTag = "textTag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transition = TransitionInflater.from(context).inflateTransition(R.transition.transition_home_item)
        transition.addListener(transitionListener)
        enterTransition = transition
        text = arguments?.getString(textTag, "") ?: ""
        postponeEnterTransition()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val app = requireActivity() as AppCompatActivity
        app.setSupportActionBar(detailToolbar)
        app.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        detailToolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        detailText.text = text
        startPostponedEnterTransition()
    }

    override fun onResume() {
        super.onResume()
        detailContainer.dismissListener = this
        detailContainer.dragThresholdListener = this
    }

    override fun onPause() {
        detailContainer.dragThresholdListener = null
        detailContainer.dismissListener = null
        super.onPause()
    }

    override fun onDestroy() {
        transition.removeListener(transitionListener)
        super.onDestroy()
    }

    override fun onDismiss() {
        requireActivity().onBackPressed()
    }

    override fun onThresholdReached() {
        if (detailContainer?.isHapticFeedbackEnabled == true) {
            detailContainer?.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
        }
    }
}
