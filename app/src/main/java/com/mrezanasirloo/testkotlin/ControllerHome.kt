package com.mrezanasirloo.testkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.SimpleSwapChangeHandler

/**
 * @author : M.Reza.Nasirloo@gmail.com
 *         Created on: 2018-04-13
 */
class ControllerHome : Controller() {

    companion object {
        var counter: Int = 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        println("ControllerHome.onCreateView: $id")
        val view = inflater.inflate(R.layout.controller_home, container, false)
        onViewCreated(view)
        return view
    }

    private val id = counter.toString()

    private fun onViewCreated(view: View?) {
        val button = view?.findViewById<Button>(R.id.button_next)
        button?.text = id
        button?.setOnClickListener { _ ->
            counter++
            router.pushController(RouterTransaction.with(ControllerHome())
                    .popChangeHandler(SimpleSwapChangeHandler())
                    .pushChangeHandler(SimpleSwapChangeHandler()))
        }
    }

    override fun handleBack(): Boolean {
        counter--
        return super.handleBack()
    }

    override fun onDestroyView(view: View) {
        println("ControllerHome.onDestroyView: $id")
        println("ControllerHome.isBeingDestroyed: $id $isBeingDestroyed")
    }

    override fun onDestroy() {
        println("ControllerHome.onDestroy: $id")
    }
}