package org.sopt.androidseminar.request

data class GithubRepositoryInfo (
    val id: Int,
    val node_id: String,
    val name: String,
    val full_name: String,
    val private: Boolean,
    val owner: GithubUserInfo,
    val description: String,
    val collaborators_url: String,
    val watchers_count: Int,
    val language: String
)