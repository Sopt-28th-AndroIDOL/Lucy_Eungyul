package org.sopt.androidseminar.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import okhttp3.Response
import org.sopt.androidseminar.HomeActivity
import org.sopt.androidseminar.request.ResponseLoginData

import org.sopt.androidseminar.api.ServiceCreator
import org.sopt.androidseminar.databinding.ActivitySignInBinding
import org.sopt.androidseminar.request.RequestLoginData
import javax.security.auth.callback.Callback

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

        loginButtonClickEvent()

        signUpTextClickEvent()
    }

    private fun loginButtonClickEvent() {
        binding.btnLogin.setOnClickListener {
            val userId = binding.editextSigninId.text
            val userPw = binding.editextSigninPwd.text
            if (userId.isNullOrBlank() || userPw.isNullOrBlank()) {
                Toast.makeText(
                        this@SignInActivity, "아이디/비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@SignInActivity, "로그인 성공", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@SignInActivity, HomeActivity::class.java)
                startActivity(intent)
            }

            val requestLoginData = RequestLoginData(
                    id = binding.editextSigninId.text.toString(),
                    password = binding.editextSigninPwd.text.toString()
            )
           val call: Call<ResponseLoginData> = ServiceCreator.soptService.postLogin(requestLoginData)

            call.enqueue(object : Callback<ResponseLoginData>) {
                override fun onResponse(
                        call: Call<ResponseLoginData>,
                        response: Response<ResponseLoginData>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        Toast.makeText(this@SignInActivity, data?.user_nickname, Toast.LENGTH_SHORT).show()

                    } else {

                    }
                }
            }
            override fun onFailure(call: Call<ResponseLoginData> t: Throwable) {
                Log.d("NetworkTest", "error:$t")
            }

        }
    }

    private fun signUpTextClickEvent(){
        binding.TextSignIn.setOnClickListener{
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            signUpActivityLauncher.launch(intent)
        }
    }

}

