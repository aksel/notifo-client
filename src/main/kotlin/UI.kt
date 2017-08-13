package main.kotlin

import javax.swing.JFrame
import javax.swing.JLabel

class UI: JFrame() {
  val label = JLabel()
  init {
    title = "Notifo Client"
    defaultCloseOperation = EXIT_ON_CLOSE

    label.text = "Notification"
    add(label)

    pack()
  }

  fun setLabelText(text: String) {
    label.text = text
  }
}