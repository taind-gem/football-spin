package com.tafi.footballspin.view.spinningwheel

import android.content.Context
import android.graphics.*
import android.os.Build
import android.text.TextPaint
import android.text.TextUtils
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import androidx.annotation.ArrayRes
import androidx.annotation.ColorInt
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.tafi.footballspin.R
import com.tafi.footballspin.model.Team
import com.tafi.footballspin.utils.CommonUtils
import com.tafi.footballspin.view.spinningwheel.WheelRotation.Companion.init
import com.tafi.footballspin.view.spinningwheel.WheelRotation.RotationListener

class SpinningWheel : View, RotationListener {
    @ColorInt
    private var wheelStrokeColor = 0
    private var wheelStrokeWidth = 0f
    private var wheelStrokeRadius = 0f
    private var wheelTextColor = 0
    private var wheelTextSize = 0f
    private var wheelArrowColor = 0
    private var wheelArrowWidth = 0f
    private var wheelArrowHeight = 0f
    private var wheelRotation: WheelRotation? = null
    private var circle: Circle? = null
    private var angle = 0f
    private var previousX = 0f
    private var previousY = 0f
    private var items: MutableList<*>? = null
    private var points: Array<Point?>? = null
    @ColorInt
    private lateinit var colors: IntArray
    var onRotationListener: OnRotationListener<*>? = null
    private var onRotationListenerTicket = false
    private var onRotation = false
    private var textPaint: Paint? = null
    private var strokePaint: Paint? = null
    private var trianglePaint: Paint? = null
    private var itemPaint: Paint? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        initAttrs(attrs)
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        initAttrs(attrs)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        initCircle()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (circle == null) {
            initCircle()
        }
        if (hasData() && (points == null || points!!.size != itemSize)) {
            initPoints()
        }
        drawCircle(canvas)
        drawWheel(canvas)
        drawWheelItems(canvas)
//        drawTriangle(canvas)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (circle == null || !isEnabled || onRotation) {
            return false
        }
        val x = event.x
        val y = event.y
        if (!circle!!.contains(x, y)) {
            return false
        }
        when (event.action) {
            MotionEvent.ACTION_DOWN -> onRotationListenerTicket = true
            MotionEvent.ACTION_MOVE -> {
                var dx = x - previousX
                var dy = y - previousY
                // reverse direction of rotation above the mid-line
                if (y > circle!!.cy) {
                    dx *= -1
                }
                // reverse direction of rotation to left of the mid-line
                if (x < circle!!.cx) {
                    dy *= -1
                }
                rotate((dx + dy) * TOUCH_SCALE_FACTOR)
            }
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> onRotationListenerTicket = false
        }
        previousX = x
        previousY = y
        return true
    }

    override fun onRotate(angle: Float) {
        rotate(angle)
        onRotationListener!!.onRotation(getSelectedItem())
    }

    override fun onStop() {
        onRotation = false
        onRotationListener?.onStopRotation(getSelectedItem())
    }

    // angle mod 360 prevent to big angle, and overflow float
    // rotate without animation
    private fun rotate(angle: Float) {
        this.angle += angle
        this.angle %= ANGLE
        invalidate()
        if (onRotationListenerTicket && angle != 0f && onRotationListener != null) {
            onRotationListenerTicket = false
        }
    }

    /**
     * Rotate wheel with animation
     *
     * @param maxAngle: Max angle for render rotation
     * @param duration: time in millis wheel for rotation
     * @param interval: time to render rotation
     */
    fun rotate(maxAngle: Float, duration: Long, interval: Long) {
        if (maxAngle == 0f) {
            return
        }
        onRotationListenerTicket = true
        onRotation = true
        if (wheelRotation != null) {
            wheelRotation!!.cancel()
        }
        wheelRotation = init(duration, interval)
            .setMaxAngle(maxAngle)
            .setListener(this)
        wheelRotation!!.start()
    }

    fun getWheelStrokeColor(): Int {
        return wheelStrokeColor
    }

    fun setWheelStrokeColor(wheelStrokeColor: Int) {
        this.wheelStrokeColor = wheelStrokeColor
        invalidate()
    }

    fun getWheelStrokeWidth(): Float {
        return wheelStrokeWidth
    }

    fun setWheelStrokeWidth(wheelStrokeWidth: Float) {
        this.wheelStrokeWidth = wheelStrokeWidth
        initWheelStrokeRadius()
        invalidate()
    }

    fun getWheelTextSize(): Float {
        return wheelTextSize
    }

    fun setWheelTextSize(wheelTextSize: Float) {
        this.wheelTextSize = wheelTextSize
        invalidate()
    }

    fun getWheelTextColor(): Int {
        return wheelTextColor
    }

    fun setWheelTextColor(wheelTextColor: Int) {
        this.wheelTextColor = wheelTextColor
        invalidate()
    }

    fun getWheelArrowColor(): Int {
        return wheelArrowColor
    }

    fun setWheelArrowColor(wheelArrowColor: Int) {
        this.wheelArrowColor = wheelArrowColor
        invalidate()
    }

    fun setWheelArrowWidth(wheelArrowWidth: Float) {
        this.wheelArrowWidth = wheelArrowWidth
        invalidate()
    }

    fun setWheelArrowHeight(wheelArrowHeight: Float) {
        this.wheelArrowHeight = wheelArrowHeight
        invalidate()
    }

    fun getColors(): IntArray {
        return colors
    }

    fun setColors(colors: IntArray) {
        this.colors = colors
        invalidate()
    }

    // Set colors with array res
    // Minimal length 3
    fun setColors(@ArrayRes colorsResId: Int) {
        if (colorsResId == 0) { // init default colors
            setColors(COLORS_RES)
            return
        }
        val typedArray: IntArray
        // if in edit mode
        if (isInEditMode) {
            val sTypeArray = resources.getStringArray(colorsResId)
            typedArray = IntArray(sTypeArray.size)
            for (i in sTypeArray.indices) {
                typedArray[i] = Color.parseColor(sTypeArray[i])
            }
        } else {
            typedArray = resources.getIntArray(colorsResId)
        }
        if (typedArray.size < MIN_COLORS) { // init default colors
            setColors(COLORS_RES)
            return
        }
        val colors = IntArray(typedArray.size)
        for (i in typedArray.indices) {
            colors[i] = typedArray[i]
        }
        setColors(colors)
    }

    fun getItems(): MutableList<*>? {
        return items
    }

    fun setItems(items: MutableList<*>?) {
        this.items = items
        setColors(CommonUtils.generateRandomColorList(items?.size))
        initPoints()
        invalidate()
    }

    fun setItems(@ArrayRes itemsResId: Int) {
        if (itemsResId == 0) {
            return
        }
        val typedArray = resources.getStringArray(itemsResId)
        val items: MutableList<Any> = mutableListOf()
        for (i in typedArray.indices) {
            items.add(typedArray[i])
        }
        setItems(items)
    }

    private fun <T> getSelectedItem(): T? {
        if (circle == null || points == null) {
            return null
        }
        val itemSize = itemSize
        val cx = circle!!.cx
        for (i in points!!.indices) {
            if (points!![i]!!.x <= cx && cx <= points!![(i + 1) % itemSize]!!.x) { // validate point x
                return items!![i] as T
            }
        }
        return null
    }

    private fun initAttrs(attrs: AttributeSet?) {
        if (attrs == null) {
            return
        }
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SpinningWheel, 0, 0)
        try {
            val colorsResId = typedArray.getResourceId(R.styleable.SpinningWheel_wheel_colors, 0)
            setColors(colorsResId)
            val wheelStrokeColor = typedArray.getColor(
                R.styleable.SpinningWheel_wheel_stroke_color,
                ContextCompat.getColor(context, android.R.color.transparent)
            )
            setWheelStrokeColor(wheelStrokeColor)
            val wheelStrokeWidth = typedArray.getDimension(R.styleable.SpinningWheel_wheel_stroke_width, 0f)
            setWheelStrokeWidth(wheelStrokeWidth)
            val itemsResId = typedArray.getResourceId(R.styleable.SpinningWheel_wheel_items, 0)
            setItems(itemsResId)
            val wheelTextSize = typedArray.getDimension(
                R.styleable.SpinningWheel_wheel_text_size,
                TEXT_SIZE.toFloat()
            )
            setWheelTextSize(wheelTextSize)
            val wheelTextColor =
                typedArray.getColor(R.styleable.SpinningWheel_wheel_text_color, TEXT_COLOR)
            setWheelTextColor(wheelTextColor)
            val wheelArrowColor =
                typedArray.getColor(R.styleable.SpinningWheel_wheel_arrow_color, ARROW_COLOR)
            setWheelArrowColor(wheelArrowColor)
            val wheelArrowWidth = typedArray.getDimension(
                R.styleable.SpinningWheel_wheel_arrow_width,
                dpToPx(ARROW_SIZE).toFloat()
            )
            setWheelArrowWidth(wheelArrowWidth)
            val wheelArrowHeight = typedArray.getDimension(
                R.styleable.SpinningWheel_wheel_arrow_height,
                dpToPx(ARROW_SIZE).toFloat()
            )
            setWheelArrowHeight(wheelArrowHeight)
        } finally {
            typedArray.recycle()
        }
        init()
    }

    private fun init() {
        textPaint = Paint()
        textPaint!!.style = Paint.Style.FILL
        textPaint!!.color = wheelTextColor
        textPaint!!.textSize = wheelTextSize
        strokePaint = Paint()
        strokePaint?.apply {
            style = Paint.Style.STROKE
            color = wheelStrokeColor
            strokeWidth = wheelStrokeWidth
            strokeCap = Paint.Cap.ROUND
        }
        trianglePaint = Paint()
        trianglePaint?.apply {
            color = wheelArrowColor
            style = Paint.Style.FILL_AND_STROKE
            isAntiAlias = true
        }
        itemPaint = Paint()
        itemPaint!!.style = Paint.Style.FILL
    }

    private fun initWheelStrokeRadius() {
        wheelStrokeRadius = wheelStrokeWidth / 2
        wheelStrokeRadius = if (wheelStrokeRadius == 0f) 1f else wheelStrokeRadius
    }

    private fun initCircle() {
        val width = if (measuredWidth == 0) width else measuredWidth
        val height = if (measuredHeight == 0) height else measuredHeight
        circle = Circle(width.toFloat(), height.toFloat())
    }

    private fun initPoints() {
        if (items != null && items!!.isNotEmpty()) {
            points = arrayOfNulls(items!!.size)
        }
    }

    private fun drawCircle(canvas: Canvas) {
        canvas.drawCircle(circle!!.cx, circle!!.cy, circle!!.radius, Paint())
        drawCircleStroke(canvas)
    }

    private fun drawCircleStroke(canvas: Canvas) {
        strokePaint?.let { paint ->
            canvas.drawCircle(circle!!.cx, circle!!.cy, circle!!.radius - wheelStrokeRadius, paint)
        }
    }

    private fun drawWheel(canvas: Canvas) {
        if (!hasData()) {
            return
        }
        // Prepare Point
        val cx = circle!!.cx
        val cy = circle!!.cy
        val radius = circle!!.radius
        val endOfRight = cx + radius
        val left = cx - radius + wheelStrokeRadius * 2
        val top = cy - radius + wheelStrokeRadius * 2
        val right = cx + radius - wheelStrokeRadius * 2
        val bottom = cy + radius - wheelStrokeRadius * 2
        // Rotate Wheel
        canvas.rotate(angle, cx, cy)
        // Prepare Pie
        val rectF = RectF(left, top, right, bottom)
        var angle = 0f
        for (i in 0 until itemSize) {
            canvas.save()
            canvas.rotate(angle, cx, cy)
            getItemPaint(i)?.let { paint ->
                canvas.drawArc(rectF, 0f, anglePerItem, true, paint)
            }
            canvas.restore()
            points!![i] = circle!!.rotate(angle + this.angle, endOfRight, cy)
            angle += anglePerItem
        }
    }

    private fun drawWheelItems(canvas: Canvas) {
        val cx = circle!!.cx
        val cy = circle!!.cy
        val radius = circle!!.radius
        val x = cx - radius + wheelStrokeRadius * 5
        val textWidth = radius - wheelStrokeRadius * 10
        val textPaint = TextPaint()
        textPaint.set(this.textPaint)
        var angle = anglePerItem / 2
        for (i in 0 until itemSize) {
            val item = TextUtils
                .ellipsize((items!![i] as Team).abbr, textPaint, textWidth, TextUtils.TruncateAt.END)
            canvas.save()
            canvas.rotate(angle + 180, cx, cy) // +180 for start from right
            this.textPaint?.let { paint ->
                paint.textSize = CommonUtils.spToPx(context, 20f).toFloat()
                paint.color = ContextCompat.getColor(context, R.color.wheel_text_color)
                paint.isAntiAlias = true
                paint.style = Paint.Style.FILL
                paint.isFakeBoldText = true

                canvas.drawText(item.toString(), x, cy, paint)
            }
            canvas.restore()
            angle += anglePerItem
        }
    }

    private fun drawTriangle(
        canvas: Canvas,
        paint: Paint?,
        x: Float,
        y: Float,
        width: Float,
        height: Float
    ) {
        val halfWidth = width / 2
        val halfHeight = height / 2
        val path = Path()
        path.moveTo(x - halfWidth, y - halfHeight) // Top left
        path.lineTo(x + halfWidth, y - halfHeight) // Top right
        path.lineTo(x, y + halfHeight) // Bottom Center
        path.lineTo(x - halfWidth, y - halfHeight) // Back to top left
        path.close()
        paint?.let { canvas.drawPath(path, it) }
    }

    private fun getItemPaint(position: Int): Paint? {
        itemPaint!!.color = colors[position]
        return itemPaint
    }

    private val itemSize: Int
        get() = if (items == null) 0 else items!!.size

    private val anglePerItem: Float
        get() = ANGLE / itemSize.toFloat()

    private fun hasData(): Boolean {
        return items != null && !items!!.isEmpty()
    }

    private fun dpToPx(dp: Int): Int {
        val displayMetrics = context.resources.displayMetrics
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
    }

    interface OnRotationListener<T> {
        fun onRotation(item: T?)
        fun onStopRotation(item: T?)
    }

    companion object {
        private const val MIN_COLORS = 3
        private const val ANGLE = 360f
        private const val COLORS_RES = R.array.rainbow_dash
        private const val TOUCH_SCALE_FACTOR = 180.0f / 320 / 2
        private const val TEXT_SIZE = 25
        private const val TEXT_COLOR = Color.BLACK
        private const val ARROW_COLOR = Color.BLACK
        private const val ARROW_SIZE = 50
    }
}