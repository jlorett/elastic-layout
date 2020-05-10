package com.joshualorett.elasticlayout.listeners

/**
 * Listens for drag events
 * Created by Joshua on 5/10/2020.
 */
interface ElasticDragListener {
    /**
     * Value from [0, 1] indicating the drag offset.
     */
    fun onDrag(offset: Float)
}