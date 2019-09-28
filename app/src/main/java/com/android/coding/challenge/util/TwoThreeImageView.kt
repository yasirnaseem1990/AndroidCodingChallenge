package com.android.coding.challenge.util

import android.content.Context

import android.util.AttributeSet
import android.view.View

import androidx.appcompat.widget.AppCompatImageView


class TwoThreeImageView : AppCompatImageView {

    companion object {
        const val TWO = 2
        const val THREE = 3
    }
    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    /**
     * This method measures the view and its content to determine the measured width and the measured
     * height, which will make 2:3 aspect ratio.
     *
     * @param widthMeasureSpec horizontal space requirements as imposed by the parent
     * @param heightMeasureSpec vertical space requirements as imposed by th parent
     */
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val twoThreeHeight = View.MeasureSpec.getSize(widthMeasureSpec) * THREE / TWO
        val twoThreeHeightSpec =
            View.MeasureSpec.makeMeasureSpec(twoThreeHeight, View.MeasureSpec.EXACTLY)
        super.onMeasure(widthMeasureSpec, twoThreeHeightSpec)
    }
}
