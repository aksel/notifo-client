import main.kotlin.Connection
import main.kotlin.UI

fun main(args: Array<String>) {
  println("Notifo client")

  val ui: UI = UI()
  ui.isVisible = true

  val conn = Connection("aksel")

  conn.onNotification = { notification -> ui.setLabelText(notification.get("payload").toString()) }

  conn.connect()
}