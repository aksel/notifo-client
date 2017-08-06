package main.kotlin

import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONArray
import org.json.JSONObject

class Connection(user: String) {
  val url = "http://localhost:4000"
  val socket: Socket = IO.socket("$url?user=$user")
  init {
    socket.connect()

    socket.on(Socket.EVENT_CONNECT, { socket.emit("get") })

    socket.on("notifications", { data ->
      val notifications = data[0] as JSONArray
      for (notification in notifications) {
        println("Notification: $notification")
      }
    })

    socket.on("notification", { data ->
      val notification = data[0] as JSONObject
      println("Notification: $notification")
    })

    socket.on(Socket.EVENT_DISCONNECT, { println("disconnected") })
  }
}