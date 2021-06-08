package org.sopt.androidseminar.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.androidseminar.response.SoptUserAuthStorage
import org.sopt.androidseminar.api.ServiceCreator
import org.sopt.androidseminar.databinding.ActivitySignInBinding
import org.sopt.androidseminar.request.RequestLoginData
import org.sopt.androidseminar.response.ResponseLoginData as RequestResponseLoginData

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private val signUpActivityLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
    ) {
        binding.edittextId.setText(it.data?.getStringExtra("id"))
        binding.edittextPw.setText(it.data?.getStringExtra("password"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(SoptUserAuthStorage.hasUserData(this)){
            startHomeActivity()
            finish()
        }

        loginButtonClickEvent()
        signUpTextClickEvent()
        searchUserAuthStorage()
    }

    private fun requestLoginData(requestLoginData: RequestLoginData) {
        val call: retrofit2.Call<org.sopt.androidseminar.response.ResponseLoginData> = ServiceCreator.soptService
            .postLogin(requestLoginData)
    }

    private fun searchUserAuthStorage() {
        if (hasUserAuthData()) {
            requestLoginData(
                RequestLoginData(
                    id = SoptUserAuthStorage.getUserId(this),
                    password = SoptUserAuthStorage.getUserPw(this)
                )
            )
        }
    }

    private fun hasUserAuthData() = SoptUserAuthStorage.getUserId(this).isNotEmpty() &&
            SoptUserAuthStorage.getUserPw(this).isNotEmpty()

    private fun loginButtonClickEvent() {
        binding.btnLogin.setOnClickListener {
            val userId = binding.edittextId.text
            val userPw = binding.edittextPw.text
            if (userId.isNullOrBlank() || userPw.isNullOrBlank()) {
                Toast.makeText(
                        this@SignInActivity, "아이디/비밀번호를 확인해주세요", LENGTH_SHORT
                ).show()
            } else {
                //로그인 서버 통신
                val requestLoginData = RequestLoginData(
                        id = binding.edittextId.text.toString(),
                        password = binding.edittextPw.text.toString()
                )
                val call: retrofit2.Call<RequestResponseLoginData> = ServiceCreator.soptService.postLogin(requestLoginData)
                call. enqueue(object : retrofit2.Callback<RequestResponseLoginData> {
                    override fun onResponse(
                            call: retrofit2.Call<RequestResponseLoginData>,
                            response: retrofit2.Response<RequestResponseLoginData>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body()?.data
                            if (!hasUserAuthData()) {
                                with(binding) {
                                    SoptUserAuthStorage.savUserId(this@SignInActivity, edittextId.text.toString())
                                    SoptUserAuthStorage.saveUserPw(this@SignInActivity,edittextPw.text.toString())
                                }
                            }
                            Toast.makeText(this@SignInActivity,"로그인 완료", LENGTH_SHORT).show()
                            startHomeActivity()
                        } else {
                            Toast.makeText(this@SignInActivity,"로그인 에러", LENGTH_SHORT).show()
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



