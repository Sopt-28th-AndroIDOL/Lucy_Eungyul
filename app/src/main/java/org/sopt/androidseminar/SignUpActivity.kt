package org.sopt.androidseminar

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Toast
import org.sopt.androidseminar.databinding.ActivitySignUpBinding


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("LC", "SignUp_onCreate")
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_main)
    }

    private fun initActivityResult() {
        binding.buttonSignup.setOnClickListener() {
            val signupName = binding.editextSignupName.text
            val signupID = binding.editextSignupId.text
            val signupPwd = binding.editextSignupPwd.text

            if (signupID.isNullOrBlank() || signupName.isNullOrBlank() || signupPwd.isNullOrBlank()) {
                Toast.makeText(this@SignUpActivity, "빈칸이 있는지 확인해주세요", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent()
                intent.putExtra("id", signupID.toString())
                intent.putExtra("pwd", signupPwd.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
    override fun onStart() {
        super.onStart()
        Log.d("LC", "SignUp_onStart")
    }
    override fun onRestart() {
        super.onRestart()
        Log.d("LC", "SignUp_onRestart")
    }
    override fun onResume() {
        super.onResume()
        Log.d("LC", "SignUp_onResume")
    }
    override fun onPause() {
        super.onPause()
        Log.d("LC", "SignUp_onPause")
    }
    override fun onStop() {
        super.onStop()
        Log.d("LC", "SignUp_onStop")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d("LC", "SignUp_onDestroy")
    }

}
