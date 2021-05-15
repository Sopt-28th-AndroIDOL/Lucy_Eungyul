package org.sopt.androidseminar.api

import org.sopt.androidseminar.request.GithubRepositoryInfo
import org.sopt.androidseminar.request.GithubUserInfo
import retrofit2.Call
import retrofit2.http.GET

interface GithubService {
    @GET("/users/hongeungual/repos")
    fun getRepositories(): Call<List<GithubRepositoryInfo>>

    @GET("/users/hongeungual")
    fun getUser(): Call<GithubUserInfo>

    @GET("/users/hongeungual/followers")
    fun getFollower():Call<List<GithubUserInfo>>
}