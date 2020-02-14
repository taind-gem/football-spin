package com.tafi.footballspin.view.spinningwheel

import android.graphics.Matrix
import android.graphics.Point
import kotlin.math.min

internal class Circle() {
    var cx = 0f
        private set
    var cy = 0f
        private set
    var radius = 0f
        private set
    private val matrix: Matrix = Matrix()

    constructor(width: Float, height: Float) : this() {
        cx = width / 2f
        cy = height / 2f
        radius = min(cx, cy)
    }

    fun contains(x: Float, y: Float): Boolean {
        var x = x
        var y = y
        x = cx - x
        y = cy - y
        return x * x + y * y <= radius * radius
    }

    fun rotate(
        angle: Float,
        x: Float,
        y: Float
    ): Point {
        matrix.setRotate(angle, cx, cy)
        val pts = FloatArray(2)
        pts[0] = x
        pts[1] = y
        matrix.mapPoints(pts)
        return Point(pts[0].toInt(), pts[1].toInt())
    }

}