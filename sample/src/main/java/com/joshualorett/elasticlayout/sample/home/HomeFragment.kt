package com.joshualorett.elasticlayout.sample.home

import android.os.Bundle
import android.transition.Fade
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.joshualorett.elasticlayout.sample.R
import com.joshualorett.elasticlayout.sample.detail.DetailFragment
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(), HomeListItemViewHolder.ItemClickListener {
    private val backStackChangedListener = FragmentManager.OnBackStackChangedListener {
        val count = requireFragmentManager().backStackEntryCount
        if(count == 0) {
            removeScrim()
        } else {
            applyScrim()
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enterTransition = Fade()
        val mockData = requireContext().resources.getStringArray(R.array.homeData).asList()
        list.adapter = HomeListAdapter(mockData, this)
        (requireActivity() as AppCompatActivity).setSupportActionBar(homeToolbar)
        requireFragmentManager().addOnBackStackChangedListener(backStackChangedListener)
    }

    override fun onDestroy() {
        requireFragmentManager().removeOnBackStackChangedListener(backStackChangedListener)
        super.onDestroy()
    }

    override fun onItemClick(position: Int, text: String, view: View) {
        navigateToDetail(text)
    }

    private fun navigateToDetail(text: String) {
        val fragment = DetailFragment()
        val bundle = Bundle()
        bundle.putString(DetailFragment.textTag, text)
        fragment.arguments = bundle
        requireFragmentManager().beginTransaction()
            .add(R.id.fragmentContainer, fragment, DetailFragment::class.java.simpleName)
            .addToBackStack(DetailFragment::class.java.simpleName)
            .commit()
    }

    fun updateScrimAlpha(alpha: Float) {
        homeScrim.alpha = alpha
    }

    private fun applyScrim() {
        homeScrim.alpha = 0F
        homeScrim.visibility = View.VISIBLE
        homeScrim.animate().alpha(1F)
            .setDuration(200)
            .setListener(null)
    }

    private fun removeScrim() {
        homeScrim.alpha = 0F
        homeScrim.visibility = View.GONE
    }
}
