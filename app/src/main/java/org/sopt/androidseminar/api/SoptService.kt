package org.sopt.androidseminar.api

import org.sopt.androidseminar.response.ResponseLoginData
import org.sopt.androidseminar.request.RequestLoginData
import org.sopt.androidseminar.request.RequestSignUpData
import org.sopt.androidseminar.response.ResponseSignUpData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("/login/signin") //signin
    fun postLogin(
        @Body body: RequestLoginData
    ): Call<ResponseLoginData>

    @POST("/login/signup") //signup
    fun postSignUp(
        @Body body: RequestSignUpData
    ): Call<ResponseSignUpData>
}