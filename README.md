 # Postman Test 
<img width="806" alt="화면 캡처 2021-05-15 210533" src="https://user-images.githubusercontent.com/76424700/118394463-e2f8f380-b67f-11eb-96ef-8f078e0b5a81.png">
<img width="799" alt="화면 캡처 2021-05-15 210753" src="https://user-images.githubusercontent.com/76424700/118394465-e5f3e400-b67f-11eb-9a25-57cdc1a99aa8.png">

# 
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
    
    안드로이드는 클라이언트로써 서버와 통신해서 원활하게 상호작용을 할 수 있는 프로그램을 만들 수 있다는 점이 매우 신기했다...! 하나의 서비스가 만들어지기 위해 안드로이드, 서버, 디자인 등 여러 파트가 협업하는데 이 과정에서 "Gson"이라는 만국의 공통어(?)가 존재한다는 점도 흥미로웠다. retrofit interface를 만들고 따로 구현체를 만들어서 구현하고 서버통신을 하기 위해 callback으로 연결한다는 것을 이해하는 것이 조금 힘들었지만 더 복습하고 공부할 것이다 !!
