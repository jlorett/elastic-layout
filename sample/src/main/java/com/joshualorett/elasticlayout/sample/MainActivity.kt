package com.joshualorett.elasticlayout.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.joshualorett.elasticlayout.sample.home.HomeFragment

class MainActivity : AppCompatActivity(), BackdropFader {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, HomeFragment(), HomeFragment::class.java.simpleName)
            .commit()
    }

    override fun fade(alpha: Float) {
        val homeFragment = supportFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName) as HomeFragment?
        homeFragment?.updateScrimAlpha(alpha)
    }
}
