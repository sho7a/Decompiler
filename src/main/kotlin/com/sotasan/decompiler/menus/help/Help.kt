package com.sotasan.decompiler.menus.help

import com.formdev.flatlaf.extras.components.FlatMenu
import com.sotasan.decompiler.services.LanguageService
import java.awt.Desktop
import java.awt.event.KeyEvent

class Help : FlatMenu() {

    init {
        isVisible = Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.APP_ABOUT)
        mnemonic = KeyEvent.VK_H
        text = LanguageService.getTranslation("help")

        add(HelpAbout())
    }
    
}