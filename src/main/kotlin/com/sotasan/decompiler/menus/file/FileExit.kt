package com.sotasan.decompiler.menus.file

import com.formdev.flatlaf.extras.components.FlatMenuItem
import com.sotasan.decompiler.controllers.WindowController
import com.sotasan.decompiler.services.LanguageService
import java.awt.Desktop
import java.awt.Toolkit
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyEvent
import javax.swing.KeyStroke

class FileExit : FlatMenuItem(), ActionListener {

    init {
        accelerator = KeyStroke.getKeyStroke(KeyEvent.VK_Q, Toolkit.getDefaultToolkit().menuShortcutKeyMaskEx)
        isVisible = !(Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.APP_QUIT_HANDLER))
        mnemonic = KeyEvent.VK_Q
        text = LanguageService.getTranslation("file.exit")
        addActionListener(this)

        if (!isVisible)
            Desktop.getDesktop().setQuitHandler { _, _ -> actionPerformed(null) }
    }

    override fun actionPerformed(p0: ActionEvent?) {
        WindowController.dispose()
    }

}