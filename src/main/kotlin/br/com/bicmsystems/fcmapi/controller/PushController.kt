package br.com.bicmsystems.fcmapi.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import java.net.URL
import java.net.HttpURLConnection
import org.json.JSONObject
import java.io.OutputStreamWriter

@RestController
@RequestMapping()
class NoteController {

	val userDeviceID = "f8WiuEMFa1s:APA91bGHJU3LL7eoYgW90GG4R5yAqap7cVe4uVS7lUUdw34KPnvqNVX0YUaOKoVZEouGI9Tli6-BHlMMZNm0y06pe-FQLB0wK4N3UorYf5m-_YgoeOyPNxYhiJxPlRZeOPU9h879FC7X"
	@PostMapping("/push")
	fun list(): String {
		pushFCMNotification(userDeviceID)
		return "OK"
	}

	// Method to send Notifications from server to client end.

	// https://console.firebase.google.com/project/fcmapp-23b06/settings/cloudmessaging/android:br.com.bicmsystems.fcmapp
	// Chave herdada do servidor... 
	val AUTH_KEY_FCM = "AIzaSyDRHIx1Yiv7iMdGGQVDUJ3hdLrnvVINXeY"
	val API_URL_FCM = "https://fcm.googleapis.com/fcm/send"

	// userDeviceIdKey is the device id you will query from your database

/*
 
POST /fcm/send HTTP/1.1
Host: fcm.googleapis.com
Authorization: key=AIzaSyDRHIx1Yiv7iMdGGQVDUJ3hdLrnvVINXeY
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: ac429d55-90d2-44db-9e82-bcb94ed5abba

{"notification":{"title":"Notificatoin Title","body":"Hello Test notification","click_action":"br.com.bicmsystems.detail"},"data":{"id":"Bruno Istvan Campos Monteiro","outroparam":"teste"},"to":"f8WiuEMFa1s:APA91bGHJU3LL7eoYgW90GG4R5yAqap7cVe4uVS7lUUdw34KPnvqNVX0YUaOKoVZEouGI9Tli6-BHlMMZNm0y06pe-FQLB0wK4N3UorYf5m-_YgoeOyPNxYhiJxPlRZeOPU9h879FC7X"}
 	
*/ 		
	
	@Throws(Exception::class)
	fun pushFCMNotification(userDeviceIdKey: String) {

		val authKey = AUTH_KEY_FCM // You FCM AUTH key
		val FMCurl = API_URL_FCM

		val url = URL(FMCurl)
		val conn = url.openConnection() as HttpURLConnection

		conn.useCaches = false
		conn.doInput = true
		conn.doOutput = true

		conn.requestMethod = "POST"
		conn.setRequestProperty("Authorization", "key=$authKey")
		conn.setRequestProperty("Content-Type", "application/json")

		val data = JSONObject()
		data.put("id", "1234567890")
		data.put("outroparam", "teste")


		val json = JSONObject()
		json.put("to", userDeviceIdKey.trim { it <= ' ' })
		val info = JSONObject()
		info.put("title", "Notificatoin Title") // Notification title
		info.put("body", "Hello Test notification") // Notification body
		info.put("click_action", "br.com.bicmsystems.detail")

		json.put("notification", info)
		json.put("data", data)

		val wr = OutputStreamWriter(conn.outputStream)
		wr.write(json.toString())
		wr.flush()
		conn.inputStream

	}
}