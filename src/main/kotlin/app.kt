import main.kotlin.Connection

fun main(args: Array<String>) {
  println("Notifo client")
  val conn = Connection("aksel")
  conn.sendNotification("aksel2", "Hello world")
}