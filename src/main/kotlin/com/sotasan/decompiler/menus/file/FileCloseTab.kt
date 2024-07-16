package com.sotasan.decompiler.menus.file

import com.formdev.flatlaf.extras.components.FlatMenuItem
import com.sotasan.decompiler.controllers.TabsController
import com.sotasan.decompiler.services.LanguageService
import java.awt.Toolkit
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import javax.swing.KeyStroke

object FileCloseTab : FlatMenuItem(), ActionListener {

    init {
        accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_W, Toolkit.getDefaultToolkit().menuShortcutKeyMaskEx)
        isEnabled = false
        mnemonic = KeyEvent.VK_W
        text = LanguageService.getTranslation("file.closeTab")
        addActionListener(this)
    }

    override fun actionPerformed(p0: ActionEvent?) {
        TabsController.INSTANCE.closeTab()
    }

}