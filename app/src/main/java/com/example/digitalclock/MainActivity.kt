package com.example.digitalclock

//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//
//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//}
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var timeTextView: TextView
    private lateinit var formatTextView: TextView
    private lateinit var dateTextView: TextView

    private val timeFormat = SimpleDateFormat("hh:mm:ss", Locale.getDefault())
    private val formatFormat = SimpleDateFormat("a", Locale.getDefault())
    private lateinit var dateFormat: SimpleDateFormat

    private val handler = Handler(Looper.getMainLooper())
    private val updateTimeRunnable = object : Runnable {
        override fun run() {
            updateTime()
            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timeTextView = findViewById(R.id.time)
        formatTextView = findViewById(R.id.format)
        dateTextView = findViewById(R.id.date)

        dateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())

        handler.post(updateTimeRunnable)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(updateTimeRunnable)
    }

    private fun updateTime() {
        val currentTime = Calendar.getInstance().time
        val formattedTime = timeFormat.format(currentTime)
        val formattedFormat = formatFormat.format(currentTime)
        val formattedDate = dateFormat.format(currentTime)

        timeTextView.text = formattedTime
        formatTextView.text = formattedFormat
        dateTextView.text = formattedDate
    }
}




