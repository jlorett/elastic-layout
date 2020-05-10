package com.joshualorett.elasticlayout.listeners

import com.joshualorett.elasticlayout.ElasticLayout

/**
 * Listens for when an [ElasticLayout] has been dragged and released past its threshold.
 * Created by Joshua on 5/10/2020.
 */
interface ElasticDragReleaseListener {
    fun onDragReleased()
}