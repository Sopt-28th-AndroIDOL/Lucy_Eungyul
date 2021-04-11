package org.sopt.androidseminar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Log.d("LC", "Home_onStart")
    }
    override fun onStart() {
        super.onStart()
        Log.d("LC", "Home_onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("LC", "Home_onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LC", "Home_onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LC", "Home_onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LC", "Home_onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LC", "Home_onDestroy")
    }
}