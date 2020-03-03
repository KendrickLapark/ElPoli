package servicio;
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast


class Servicio : Service() {


    override fun onCreate() {
        super.onCreate()
    }


    override fun onBind(intent: Intent): IBinder {
        return onBind(intent);

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(this, "Iniciando el juego...", Toast.LENGTH_LONG).show()
        Log.d("Servicio", intent!!.getStringExtra("mensaje"))
        return Service.START_NOT_STICKY;

    }
}