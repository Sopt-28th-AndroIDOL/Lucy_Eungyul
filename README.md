화면전환 후 데이터를 가져온 로직

- Signin Activity

class SignInActivity : AppCompatActivity() {
    
    private lateinit var binding:ActivitySignInBinding

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
    }
    }
    
- SignUp Activity

class SignUpActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_main)
    }

    private fun initActivityResult() {
        binding.buttonSignup.setOnClickListener() {
            val signupName = binding.editextSignupName.text
            val signupID = binding.editextSignupId.text
            val signupPwd = binding.editextSignupPwd.text

            if (signupID.isNullOrBlank() || signupName.isNullOrBlank() || signupPwd.isNullOrBlank()) {
                Toast.makeText(this@SignUpActivity, "빈칸이 있는지 확인해주세요", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent()
                intent.putExtra("id", signupID.toString())
                intent.putExtra("pwd", signupPwd.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }
  
 생명 주기
 - 에뮬레이터가 실행이 되지 않아서 이 부분은 해결한 뒤에 생명 주기 올리도록 해야할 것 같다.. 
 
 배운 내용
- 안드로이드에 대해 아예 처음 배워봤는데 어려웠지만 따라가다 보니 전체적인 흐름을 알 것 같다.. 코드를 짜고 그것이 어떻게 화면에 보여지는지 알 수 있었다. 
    
    
