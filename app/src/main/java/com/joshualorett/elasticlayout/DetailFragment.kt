package com.joshualorett.elasticlayout


import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_detail.*

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment(), ElasticLayout.DismissListener, ElasticLayout.DragThresholdListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(detailToolbar)
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

    override fun onDismiss() {
        requireActivity().onBackPressed()
    }

    override fun onThresholdReached() {
    }
}
