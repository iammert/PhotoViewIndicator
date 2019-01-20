package com.iammert.photoviewindicatorlib

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.github.chrisbanes.photoview.OnMatrixChangedListener
import com.github.chrisbanes.photoview.PhotoView

class PhotoViewIndicator @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr), OnMatrixChangedListener {

    private var viewWidth = 0
    private var viewHeight = 0
    private var indicatorWidth = 0

    private var backgroundRecF: RectF = RectF()
    private var indicatorRectF: RectF = RectF()

    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val indicatorPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var initialPhotoViewRectF: RectF? = null

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.PhotoViewIndicator)
        val barColor = attributes.getColor(R.styleable.PhotoViewIndicator_barColor, ContextCompat.getColor(context, R.color.colorPhotoViewIndicatorBarBackground))
        val indicatorColor = attributes.getColor(R.styleable.PhotoViewIndicator_indicatorColor, ContextCompat.getColor(context, R.color.colorPhotoViewIndicatorColor))
        attributes.recycle()

        backgroundPaint.color = barColor
        indicatorPaint.color = indicatorColor
    }

    fun setPhotoView(photoView: PhotoView) {
        photoView.setOnMatrixChangeListener(this)
        photoView.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ -> initialPhotoViewRectF = photoView.attacher.displayRect }
    }

    override fun onMatrixChanged(rect: RectF?) {
        val matrixWidth = Math.abs(rect!!.left) + Math.abs(rect.right)
        val scrollableWidth = matrixWidth - screenWidth()

        val ratio = matrixWidth / screenWidth()
        indicatorWidth = (viewWidth / ratio).toInt()

        val currentMatrixLeft = Math.abs(rect.left)
        val widthDifference = viewWidth - indicatorWidth

        val indicatorLeftPosition = widthDifference * currentMatrixLeft / scrollableWidth

        indicatorRectF.set(indicatorLeftPosition, 0f, indicatorLeftPosition + indicatorWidth.toFloat(), viewHeight.toFloat())
        postInvalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewWidth = w
        viewHeight = h
        backgroundRecF.set(0f, 0f, viewWidth.toFloat(), viewHeight.toFloat())
        initialPhotoViewRectF?.let { onMatrixChanged(it) }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawRoundRect(backgroundRecF, viewHeight / 2.toFloat(), viewHeight / 2.toFloat(), backgroundPaint)
        canvas?.drawRoundRect(indicatorRectF, viewHeight / 2.toFloat(), viewHeight / 2.toFloat(), indicatorPaint)
    }
}