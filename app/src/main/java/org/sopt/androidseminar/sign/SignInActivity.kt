package org.sopt.androidseminar.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.androidseminar.HomeActivity

import org.sopt.androidseminar.api.ServiceCreator
import org.sopt.androidseminar.databinding.ActivitySignInBinding
import org.sopt.androidseminar.request.RequestLoginData
import org.sopt.androidseminar.response.ResponseLoginData as RequestResponseLoginData

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private val signUpActivityLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
    ) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
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
                val requestLoginData = RequestLoginData(
                        id = binding.editextSigninId.text.toString(),
                        password = binding.editextSigninPwd.text.toString()
                )
                val call: retrofit2.Call<RequestResponseLoginData> = ServiceCreator.soptService.postLogin(requestLoginData)
                call. enqueue(object : retrofit2.Callback<RequestResponseLoginData> {
                    override fun onResponse(
                            call: retrofit2.Call<RequestResponseLoginData>,
                            response: retrofit2.Response<RequestResponseLoginData>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body()?.data
                            Toast.makeText(this@SignInActivity,"로그인 완료", Toast.LENGTH_SHORT).show()
                            startHomeActivity()
                        } else {

                        }
                    }

                    override fun onFailure(call: retrofit2.Call<RequestResponseLoginData>, t:Throwable){
                        Log.d("NetworkTest","error:$t")
                    }
                })

            }

            }

        }

    private fun startHomeActivity(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }


    private fun signUpTextClickEvent() {
        binding.TextSignIn.setOnClickListener {
            val intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            signUpActivityLauncher.launch(intent)
        }
    }
}



