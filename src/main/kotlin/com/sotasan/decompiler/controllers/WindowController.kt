package com.sotasan.decompiler.controllers

import com.sotasan.decompiler.views.WindowView

object WindowController : BaseController<WindowView>(WindowView()) {

    fun show() {
        view.isVisible = true
    }
    
    fun activate() {
        view.contentPane = view.splitPane
        view.validate()
    }

    fun dispose() {
        view.dispose()
    }

}