package com.joshualorett.elasticlayout

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import kotlin.math.abs

/**
 * A [ConstraintLayout] with an elastic scroll effect. This requires a child
 * [androidx.core.widget.NestedScrollView] or [androidx.recyclerview.widget.RecyclerView].
 *
 * @property [R.styleable.ElasticLayout_elasticity] controls how elastic the layout is, the smaller
 * the number, the more stiff the layout becomes. Defaults to 0.4.
 *
 * @property [R.styleable.ElasticLayout_threshold] sets a drag boundary on the layout. Defaults to
 * [Float.MAX_VALUE].
 * Created by Joshua on 12/25/2019.
 */
open class ElasticLayout(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    ConstraintLayout(context, attrs, defStyleAttr) {
    private var lastActionEvent: Int? = null
    private var totalDragDistance = 0F
    private var dragThreshold = 0F
    private val elasticity: Float
    private val fastOutSlowInInterpolator = FastOutSlowInInterpolator()
    private var lastTranslationY = 0F

    var dragThresholdListener: DragThresholdListener? = null
    var dismissListener: DismissListener? = null

    constructor(context: Context?) : this(context, null, 0)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    init {
        val attributes = getContext().obtainStyledAttributes(attrs, R.styleable.ElasticLayout, 0, 0)
        dragThreshold =  attributes.getDimension(R.styleable.ElasticLayout_threshold, Float.MAX_VALUE)
        elasticity = attributes.getFloat(R.styleable.ElasticLayout_elasticity, 0.4F)
        attributes.recycle()
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        lastActionEvent = event.action
        return super.onInterceptTouchEvent(event)
    }

    override fun onStartNestedScroll(child: View?, target: View?, nestedScrollAxes: Int): Boolean {
        return nestedScrollAxes and View.SCROLL_AXIS_VERTICAL != 0
    }

    override fun onNestedScroll(target: View?, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int,
        dyUnconsumed: Int) {
        translate(dyUnconsumed)
    }

    override fun onStopNestedScroll(child: View?) {
        if(abs(lastTranslationY) >= dragThreshold) {
            dismissListener?.onDismiss() ?: settleLayout()
        } else {
            settleLayout()
        }
    }

    private fun settleLayout() {
        if(lastActionEvent == MotionEvent.ACTION_DOWN) {
            // reset for new gestures
            translationY = 0F
        } else {
            this.animate()
                .translationY(0F)
                .setDuration(200L)
                .setInterpolator(fastOutSlowInInterpolator)
        }
        totalDragDistance = 0F
        lastTranslationY = 0F
    }

    /***
     *  Translates the layout based on the nested scroll. Applies more friction
     *  as the scroll goes past the drag threshold.
     */
    private fun translate(scroll: Int) {
        if (scroll == 0) {
            return
        }
        if(abs(lastTranslationY) >= dragThreshold) {
            dragThresholdListener?.onThresholdReached()
            val thresholdFraction = 1 + abs(lastTranslationY/dragThreshold)
            totalDragDistance += scroll/thresholdFraction
        } else {
            totalDragDistance += scroll
        }
        val translation = totalDragDistance * elasticity * -1
        translationY = translation
        lastTranslationY = translation
    }

    /**
     * Listens for when an [ElasticLayout] has been dragged and released past its threshold.
     * Created by Joshua on 12/30/2019.
     */
    interface DismissListener {
        fun onDismiss()
    }

    /**
     * Listens for when an [ElasticLayout] was dragged past its threshold.
     * Created by Joshua on 12/24/2019.
     */
    interface DragThresholdListener {
        fun onThresholdReached()
    }
}