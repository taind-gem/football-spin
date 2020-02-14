package com.tafi.footballspin.ui.main

import android.os.Bundle
import android.widget.Toast
import com.tafi.footballspin.R
import com.tafi.footballspin.ui.base.BaseActivity
import com.tafi.footballspin.view.spinningwheel.SpinningWheel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), IMainView, SpinningWheel.OnRotationListener<String?> {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initView() {
        wheel.setItems(R.array.dummy)
        wheel.onRotationListener = this

        rotate.setOnClickListener { spinWheel() }
    }

    override fun spinWheel() {
        wheel.rotate(50f, 3000, 50)
    }

    override fun onRotation() {

    }

    override fun onStopRotation(item: String?) {
        Toast.makeText(this, item, Toast.LENGTH_LONG).show()
    }

}
