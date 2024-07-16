package com.sotasan.decompiler.menus

import com.formdev.flatlaf.extras.components.FlatMenuBar
import com.sotasan.decompiler.menus.file.File
import com.sotasan.decompiler.menus.help.Help

class MenuBar : FlatMenuBar() {

    init {
        add(File())
        add(Help())
    }

}