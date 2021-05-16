1. retrofit interface 코드

        interface SoptService {
         @POST("/login/signin")
            fun postLogin(
             @Body body: RequestLoginData
            ): Call<ResponseLoginData>
            fun postSignUp(
        @Body body: RequestSignUpData
            ): Call<ResponseSignUpData>
        }
  
 2. retrofit interface 구현체 코드
 
        object ServiceCreator {
         private const val BASE_URL = "http://cherishserver.com"

         private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

        val soptService: SoptService = retrofit.create(SoptService::class.java)
        }

3. callback 연결 부분 (SignInActivity)

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
            
 4. callback 연결 부분 (SignUpActivity)

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
    
    5. 배운 내용 

