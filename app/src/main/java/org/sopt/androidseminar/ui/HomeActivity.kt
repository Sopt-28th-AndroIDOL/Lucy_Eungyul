package org.sopt.androidseminar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.androidseminar.databinding.ActivityHomeBinding
import org.sopt.androidseminar.adapter.RepositoryAdapter
import org.sopt.androidseminar.response.RepositoryInfo
import org.sopt.androidseminar.response.SoptUserAuthStorage

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private lateinit var repositoryAdapter: RepositoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moreButtonClickEvent()
        closeButtonClickEvent()

        repositoryAdapter = RepositoryAdapter()
        binding.homeRcv.adapter = repositoryAdapter
        repositoryAdapter.repoList.addAll(listOf<RepositoryInfo>(
            RepositoryInfo(
                repositoryName = "Lucy_Eungual",
                repositoryDescription = "은결",
                repositoryLanguage = "Kotlin"
            ),
            RepositoryInfo(
                repositoryName = "Semi_Semin",
                repositoryDescription = "세민",
                repositoryLanguage = "Kotlin"
            ),
            RepositoryInfo(
                repositoryName = "Joy_Seunghee",
                repositoryDescription = "승희",
                repositoryLanguage = "Kotlin"
            ),
            RepositoryInfo(
                repositoryName = "Rimi_Sungrim",
                repositoryDescription = "성림",
                repositoryLanguage = "Kotlin"
            ),
            RepositoryInfo(
                repositoryName = "Lucy_Eungual",
                repositoryDescription = "은결",
                repositoryLanguage = "Kotlin"
            ),
            RepositoryInfo(
                repositoryName = "Semi_Semin",
                repositoryDescription = "세민",
                repositoryLanguage = "Kotlin"
            ),
            RepositoryInfo(
                repositoryName = "Joy_Seunghee",
                repositoryDescription = "승희",
                repositoryLanguage = "Kotlin"
            ),
            RepositoryInfo(
                repositoryName = "Rimi_Sungrim",
                repositoryDescription = "성림",
                repositoryLanguage = "Kotlin"
            ),
            RepositoryInfo(
                repositoryName = "...",
                repositoryDescription = "...",
                repositoryLanguage = "Kotlin")
            )
        )
        repositoryAdapter.notifyDataSetChanged()
    }


    private fun moreButtonClickEvent(){
        binding.morebtn.setOnClickListener {
            val intent = Intent(this@HomeActivity,
                UserInfoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun closeButtonClickEvent() {
        binding.closeImageview.setOnClickListener{
            SoptUserAuthStorage.clearAuthStorage(this)
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}