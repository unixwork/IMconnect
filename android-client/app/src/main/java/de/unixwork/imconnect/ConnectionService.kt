package de.unixwork.imconnect

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Messenger
import android.util.Log


class ConnectionService : Service() {
    companion object {
        var isRunning: Boolean = false
    }

    class MessageHandler : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: android.os.Message) {

        }
    }

    final val messenger = Messenger(MessageHandler())

    override fun onCreate() {
        super.onCreate()
        isRunning = true
    }

    override fun onDestroy() {
        super.onDestroy()
        isRunning = false
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
                return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}