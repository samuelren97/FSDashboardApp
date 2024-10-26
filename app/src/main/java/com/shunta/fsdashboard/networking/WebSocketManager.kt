package com.shunta.fsdashboard.networking

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import java.util.concurrent.TimeUnit

object WebSocketManager {
    private lateinit var client: OkHttpClient
    private lateinit var websocket: WebSocket

    private lateinit var oh: () -> Unit
    private lateinit var mh: (String) -> Unit
    private lateinit var fh: (String) -> Unit

    fun init(
        openHandler: () -> Unit,
        messageHandler: (text: String) -> Unit,
        failureHandler: (reason: String) -> Unit
    ) {
        oh = openHandler
        mh = messageHandler
        fh = failureHandler
    }

    fun connectWebSocket(url: String) {
        client = OkHttpClient.Builder()
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            .build()

        val request = Request.Builder()
            .url(url)
            .build()

        websocket = client.newWebSocket(request, object : WebSocketListener() {

            override fun onOpen(webSocket: WebSocket, response: Response) {
                oh()
            }

            override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
                Log.d("WEBSOCKET", reason)
            }

            override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
                Log.d("WEBSOCKET", reason)
            }

            override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
                fh(t.message.toString())
            }

            override fun onMessage(webSocket: WebSocket, text: String) {
                mh(text)
            }

            override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
                Log.d("WEBSOCKET", bytes.hex())
            }
        })
    }

    fun disconnect() {
        websocket.close(1000, "Closing")
    }
}