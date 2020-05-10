package com.joshualorett.elasticlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), ScreenAlphaListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, HomeFragment(), HomeFragment::class.java.simpleName)
            .commit()
    }

    override fun onAlphaChanged(alpha: Float) {
        val homeFragment = supportFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName) as HomeFragment?
        homeFragment?.updateAlpha(alpha)
    }
}
