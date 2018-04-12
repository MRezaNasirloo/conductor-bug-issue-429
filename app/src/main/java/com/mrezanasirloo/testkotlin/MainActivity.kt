package com.mrezanasirloo.testkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bluelinelabs.conductor.Conductor
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.SimpleSwapChangeHandler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        router = Conductor.attachRouter(this, container, savedInstanceState)

        button.setOnClickListener {
            ControllerHome.counter = 0
            router.popToRoot(SimpleSwapChangeHandler()) // <=== Causes the isBeingDestroyed to return false
            // router.popToRoot(HorizontalChangeHandler()) // Works as expected
        }
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(ControllerHome()))
        }

    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }

}
