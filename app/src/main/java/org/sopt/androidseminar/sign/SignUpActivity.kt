package org.sopt.androidseminar.sign

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.util.Log
import android.widget.Toast
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
            val signupName = binding.editextSignupName.text
            val signupID = binding.editextSignupId.text
            val signupPwd = binding.editextSignupPwd.text
            val signupSex = binding.edittextSignupSex.text
            val signupPhone = binding.edittextPhone.text
            val signupBirth = binding.edittextBirth.text
            if (signupID.isNullOrBlank() || signupName.isNullOrBlank() || signupPwd.isNullOrBlank() || signupBirth.isNullOrBlank()
                || signupPhone.isNullOrBlank() || signupSex.isNullOrBlank()) {
                Toast.makeText(this@SignUpActivity, "빈칸이 있는지 확인해주세요", Toast.LENGTH_SHORT).show()
            } else {
                val requestSignUpData = RequestSignUpData(
                        email = binding.editextSignupId.text.toString(),
                        password = binding.editextSignupPwd.text.toString(),
                        sex = binding.edittextSignupSex.text.toString(),
                        birth = binding.edittextBirth.text.toString(),
                        phone = binding.edittextPhone.text.toString(),
                        nickname = binding.editextSignupName.text.toString()
                )

                val call: Call<ResponseSignUpData> = ServiceCreator.soptService.postSignUp(requestSignUpData)
                call.enqueue(object : Callback<ResponseSignUpData>{
                    override fun onResponse(
                            call: Call<ResponseSignUpData>,
                            response: Response<ResponseSignUpData>
                    ) {
                        if (response.isSuccessful){
                            Toast.makeText(this@SignUpActivity, "회원가입 완료", Toast.LENGTH_SHORT).show()
                            StartSignInActivity()
                        } else{

                        }
                    }

                    override fun onFailure(call: Call<ResponseSignUpData>, t: Throwable) {
                        Log.d("NetworkTest", "error:$t")
                    }
                })

            }


        }

    }

    private fun StartSignInActivity(){
        val intent = Intent(this@SignUpActivity, SignInActivity::class.java)
        intent.putExtra("name",binding.editextSignupName.text.toString())
        intent.putExtra("id", binding.editextSignupId.text.toString())
        intent.putExtra("pwd", binding.editextSignupPwd.text.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}
