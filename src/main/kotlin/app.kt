import io.socket.client.IO
import io.socket.client.Socket

fun main(args: Array<String>) {
  println("Notifo client")
  connect()
}

fun connect() {
  val socket = IO.socket("http://localhost:4000?user=aksel")
  socket.connect()
      .on(Socket.EVENT_CONNECT, { println("connected") })
      .on(Socket.EVENT_DISCONNECT, { println("disconnected") })
}