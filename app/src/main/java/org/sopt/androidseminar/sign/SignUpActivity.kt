package org.sopt.androidseminar.sign

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import org.sopt.androidseminar.api.ServiceCreator
import org.sopt.androidseminar.databinding.ActivitySignUpBinding
import org.sopt.androidseminar.request.RequestSignUpData
import org.sopt.androidseminar.response.ResponseSignUpData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


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
            val signupName = binding.edittextNickname.text
            val signupID = binding.edittextGithubId.text
            val signupPwd = binding.edittextPw.text
            val signupSex = binding.edittextSex.text
            val signupPhone = binding.edittextPhone.text
            val signupBirth = binding.edittextBirth.text
            if (signupID.isNullOrBlank() || signupName.isNullOrBlank() || signupPwd.isNullOrBlank() || signupBirth.isNullOrBlank()
                || signupPhone.isNullOrBlank() || signupSex.isNullOrBlank()) {
                Toast.makeText(this@SignUpActivity, "빈칸이 있는지 확인해주세요", LENGTH_SHORT).show()
            } else {
                //회원가입 서버 통신
                val requestSignUpData = RequestSignUpData(
                        email = binding.edittextGithubId.text.toString(),
                        password = binding.edittextPw.text.toString(),
                        sex = binding.edittextSex.text.toString(),
                        birth = binding.edittextBirth.text.toString(),
                        phone = binding.edittextPhone.text.toString(),
                        nickname = binding.edittextNickname.text.toString()
                )

                val call: Call<ResponseSignUpData> = ServiceCreator.soptService.postSignUp(requestSignUpData)
                call.enqueue(object : Callback<ResponseSignUpData>{
                    override fun onResponse(
                            call: Call<ResponseSignUpData>,
                            response: Response<ResponseSignUpData>
                    ) {
                        if (response.isSuccessful){
                            Toast.makeText(this@SignUpActivity, "회원가입 완료", LENGTH_SHORT).show()
                            startSignInActivity()
                        } else{
                            Toast.makeText(this@SignUpActivity,"회원가입 에러", LENGTH_SHORT).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                        Log.d("NetworkTest", "error:$t")
                    }
                })

            }


        }

    }

    private fun startSignInActivity(){
        val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
        intent.putExtra("name",binding.edittextNickname.text.toString())
        intent.putExtra("id", binding.edittextGithubId.text.toString())
        intent.putExtra("pwd", binding.edittextPw.text.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
