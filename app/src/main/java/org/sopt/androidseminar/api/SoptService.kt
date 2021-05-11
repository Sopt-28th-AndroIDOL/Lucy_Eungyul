package org.sopt.androidseminar.api

import org.sopt.androidseminar.request.ResponseLoginData
import org.sopt.androidseminar.request.RequestLoginData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("/login/signin")
    fun postLogin(
        @Body body: RequestLoginData
    ): Call<ResponseLoginData>
}