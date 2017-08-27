import main.kotlin.Connection
import main.kotlin.UI

fun main(args: Array<String>) {
  println("Notifo client")

  val ui = UI()

  val conn = Connection("aksel")

  conn.onNotification = { notification -> ui.displayTrayMessage(notification.get("payload").toString()) }

  conn.connect()
}