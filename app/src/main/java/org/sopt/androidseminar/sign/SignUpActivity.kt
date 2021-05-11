package org.sopt.androidseminar.sign

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import org.sopt.androidseminar.databinding.ActivitySignUpBinding


class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUpButtonClickEvent()
    }

    private fun signUpButtonClickEvent() {
        binding.btnSignUp.setOnClickListener() {
            val signupName = binding.editextSignupName.text
            val signupID = binding.editextSignupId.text
            val signupPwd = binding.editextSignupPwd.text
            if (signupID.isNullOrBlank() || signupName.isNullOrBlank() || signupPwd.isNullOrBlank()) {
                Toast.makeText(this@SignUpActivity, "빈칸이 있는지 확인해주세요", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent()
                intent.putExtra("name",signupName)
                intent.putExtra("id", signupID)
                intent.putExtra("pwd", signupPwd)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
}
