package com.namboori.reminder

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var context : Context
    lateinit var alarmManager: AlarmManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        alarmManager = getSystemService(Context.ALARM_SERVICE)as AlarmManager
        btncreate.setOnClickListener{
            val second = timer.text.toString().toInt() * 1000
            val intent = Intent(context,Reciever::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
            Log.d("MainActivity","Create : "+Date().toString())
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + second,pendingIntent)
        }

        btnupdate.setOnClickListener{
            val second = timer.text.toString().toInt() * 1000
            val intent = Intent(context,Reciever::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
            Log.d("MainActivity","Update : "+Date().toString())
            alarmManager.setExact(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + second,pendingIntent)
        }

        btndelete.setOnClickListener{
            val intent = Intent(context,Reciever::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context,0,intent,PendingIntent.FLAG_UPDATE_CURRENT)
            Log.d("MainActivity","Delete : "+Date().toString())
            alarmManager.cancel(pendingIntent)
        }
    }

    class Reciever : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("MainActivity","Reciever : " + Date().toString())
        }
    }
}
