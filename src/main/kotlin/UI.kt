package main.kotlin

import java.awt.*
import javax.swing.JFrame
import javax.swing.JLabel

class UI : JFrame() {
  val label = JLabel()

  var image = Toolkit.getDefaultToolkit().getImage("images/trayicon.png")
  var trayIcon = TrayIcon(image, "Test")

  init {
    title = "Notifo Client"
    defaultCloseOperation = EXIT_ON_CLOSE

    label.text = "Notification"
    add(label)

    pack()

    initTray()
  }

  fun initTray() {
    if (!SystemTray.isSupported()) {
      println("Tray not supported")
      return
    }

    val tray = SystemTray.getSystemTray()

    trayIcon.setImageAutoSize(true)

    val popup = PopupMenu()
    val exitItem = MenuItem("Exit")
    popup.add(exitItem)

    trayIcon.popupMenu = popup

    try {
      tray.add(trayIcon)
    } catch (e: AWTException) {
      System.err.println("TrayIcon could not be added.")
    }
  }

  fun setLabelText(text: String) {
    label.text = text
  }

  fun displayTrayMessage(text: String, type: TrayIcon.MessageType = TrayIcon.MessageType.INFO) {
    if (SystemTray.isSupported()) {
      trayIcon.displayMessage("Notifo", text, type)
    }
  }
}