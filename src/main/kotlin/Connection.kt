package main.kotlin

import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONArray
import org.json.JSONObject

class Connection(user: String) {
  val url = "http://localhost:4000"
  val socket: Socket = IO.socket("$url?user=$user")

  var onNotification: (JSONObject) -> Unit = {}

  init {
    socket.connect()
    socket.on(Socket.EVENT_CONNECT, {
      println("connected")
      socket.emit("get")
    })

    socket.on("notifications", { data ->
      val notifications = data[0] as JSONArray
      for (notification in notifications) {
        println("Notification: $notification")
      }
    })

    socket.on("notification", { data ->
      val notification = data[0] as JSONObject
      onNotification(notification)
    })

    socket.on(Socket.EVENT_DISCONNECT, { println("disconnected") })
  }

  fun connect() = socket.connect()!!

  fun sendNotification(destination: String, payload: Any) {
    val data = JSONObject()
    data.put("destination", destination)
    data.put("payload", payload)
    socket.emit("new", data)
  }
}