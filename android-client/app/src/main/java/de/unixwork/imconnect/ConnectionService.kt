package de.unixwork.imconnect

import android.app.Service
import android.content.Intent
import android.os.IBinder
import java.net.Socket

class ConnectionService : Service() {
    var host: String? = null
    var port: Int = 5080

    var ioThread: Thread? = null

    override fun onCreate() {
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        host = intent?.getStringExtra("host")
        port = intent?.getIntExtra("port", port) ?: port



        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}