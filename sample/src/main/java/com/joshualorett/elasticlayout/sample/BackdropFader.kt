package com.joshualorett.elasticlayout.sample

import androidx.annotation.FloatRange

/**
 * Fades backdrop alpha.
 * Created by Joshua on 5/10/2020.
 */
interface BackdropFader {
    fun fade(@FloatRange(from=0.0, to=1.0) alpha: Float)
}