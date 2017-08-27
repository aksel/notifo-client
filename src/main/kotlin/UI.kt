package main.kotlin

import javax.swing.JFrame
import javax.swing.JLabel
import java.awt.AWTException
import java.awt.SystemTray
import java.awt.TrayIcon
import java.awt.Toolkit
import java.awt.event.MouseEvent
import java.awt.event.MouseAdapter
import javax.swing.SwingUtilities


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

    trayIcon.addMouseListener(object : MouseAdapter() {
      override fun mouseReleased(e: MouseEvent) {
        if (SwingUtilities.isRightMouseButton(e)) {
          println("Right click")
        }
      }
    })

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