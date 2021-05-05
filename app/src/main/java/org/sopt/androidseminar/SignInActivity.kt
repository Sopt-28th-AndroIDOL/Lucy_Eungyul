package org.sopt.androidseminar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.androidseminar.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding:ActivitySignInBinding
    private val signUpActivityLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initButtonClickEvent()
    }

    private fun initButtonClickEvent(){
        binding.btnLogin.setOnClickListener{
            val userId: String = binding.editextSigninId.text.toString()
            val userPw: String = binding.editextSigninPwd.text.toString()
            if(userId.isNullOrBlank() || userPw.isNullOrBlank()){
                Toast.makeText(
                    this@SignInActivity, "아이디/비밀번호를 확인해주세요",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this@SignInActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                startActivity(intent)
            }
        }

        binding.btnLogin.setOnClickListener{
            val intent = Intent(this@SignInActivity,SignUpActivity::class.java)
            signUpActivityLauncher.launch(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("LC", "SignIn_onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("LC", "SignIn_onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LC", "SignIn_onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LC", "SignIn_onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LC", "SignIn_onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LC", "SignIn_onDestroy")
    }

}

