package org.sopt.androidseminar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.sopt.androidseminar.databinding.ItemRepositoryBinding
import org.sopt.androidseminar.response.RepositoryInfo

class RepositoryAdapter: RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>(){
    val repoList = mutableListOf<RepositoryInfo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepositoryViewHolder {
        val binding = ItemRepositoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    return RepositoryViewHolder(
        binding
    )
    }

    override fun getItemCount(): Int = repoList.size

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
       holder.onBind(repoList[position])
    }

    class RepositoryViewHolder(
        private val binding: ItemRepositoryBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun onBind(repositoryInfo: RepositoryInfo){
            binding.homeRepoName.text = repositoryInfo.repositoryName
            binding.homeRepoDescription.text = repositoryInfo.repositoryDescription
            binding.homeRepoLanguage.text = repositoryInfo.repositoryLanguage
        }
    }

}

