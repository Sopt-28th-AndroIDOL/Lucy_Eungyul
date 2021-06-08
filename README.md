# Lv1-1 Activity 처리 과정 
    
 - SignInActivity 처음 들어왔을 때 sharedPreference에 id/pw가 있을 경우
 
  
       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(SoptUserAuthStorage.UserData(this)){
            startHomeActivity()
            finish()
        }

        loginButtonClickEvent()
        signUpTextClickEvent()
        searchUserAuthStorage()
       }
 
 - SignInActivity 로그인 할 때 성공 시 sharedPreference 집어 넣기 


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
                    
 - HomeActivity 로그아웃할 때 sharedPreference clear 
 : closeButtonClickEvent 함수 따로 만들어서 구현
 
       private fun closeButtonClickEvent() {
        binding.closeImageview.setOnClickListener{
            SoptUserAuthStorage.clearAuthStorage(this)
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
        
 # LV1-2 구현한 SharedPreference 코드 
 
    object SoptUserAuthStorage {
    private const val STORAGE_KEY = "user_auth"
    private const val USER_ID_KEY = "user_id"
    private const val USER_PW_KEY = "user_pw"

    fun getUserId(context: Context): String {
        val sharedPreferences = context.getSharedPreferences(
            "${context.packageName}.$STORAGE_KEY",
            Context.MODE_PRIVATE
        )
        return sharedPreferences.getString(USER_ID_KEY, "") ?: ""
    }

    fun getUserPw(context: Context): String {
        val sharedPreferences = context.getSharedPreferences(
            "${context.packageName}.$STORAGE_KEY",
            Context.MODE_PRIVATE
        )
        return sharedPreferences.getString(USER_PW_KEY, "") ?: ""
    }

    fun savUserId(context: Context, id: String)
    {
        val sharedPreferences = context.getSharedPreferences(
            "${context.packageName}.$STORAGE_KEY",
            Context.MODE_PRIVATE
        )
        sharedPreferences.edit()
            .putString(USER_ID_KEY, id)
            .apply()
    }

    fun saveUserPw(context: Context, pw: String) {
        val sharedPreferences = context.getSharedPreferences(
            "${context.packageName}.$STORAGE_KEY",
            Context.MODE_PRIVATE
        )
        sharedPreferences.edit()
            .putString(USER_PW_KEY, pw)
            .apply()
    }


    fun clearAuthStorage(context: Context){
        val sharedPreferences = context.getSharedPreferences(
            "${context.packageName}.$STORAGE_KEY",
            Context.MODE_PRIVATE
        )
        sharedPreferences.edit()
            .clear()
            .apply()
    }

    fun UserData(context: Context) = getUserId(context).isNotEmpty() && getUserPw(context).isNotEmpty()
    }

# 배운 내용: 
sharedPreference를 사용하는 방법에 대해서 배울 수 있었고, 확장 함수에 대해 알게 된게 신기했다. clean code가 뭔지 알게 된 거 같다. 하지만 남발하면 안된다는 것... 
