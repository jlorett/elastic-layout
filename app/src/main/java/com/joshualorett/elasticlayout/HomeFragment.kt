package com.joshualorett.elasticlayout


import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(), HomeListItemViewHolder.ItemClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        enterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.fade)
        val mockData = requireContext().resources.getStringArray(R.array.homeData).asList()
        list.adapter = HomeListAdapter(mockData, this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireActivity() as AppCompatActivity).setSupportActionBar(homeToolbar)
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

    override fun onItemClick(position: Int, text: String, view: View) {
        navigateToDetail(text)
    }
}
