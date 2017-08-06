import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONArray
import org.json.JSONObject

fun main(args: Array<String>) {
  println("Notifo client")
  connect()
}

fun connect() {
  val socket = IO.socket("http://localhost:4000?user=aksel")
  socket.connect()
      .on(Socket.EVENT_CONNECT, {
        println("connected")
        socket.emit("get")
      })
      .on("notifications", { data ->
        val notifications = data[0] as JSONArray
        for (notification in notifications) {
          println("Notification: $notification")
        }
      })
      .on("notification", { data ->
        val notification = data[0] as JSONObject
        println("Notification: $notification")
      })
      .on(Socket.EVENT_DISCONNECT, { println("disconnected") })
}