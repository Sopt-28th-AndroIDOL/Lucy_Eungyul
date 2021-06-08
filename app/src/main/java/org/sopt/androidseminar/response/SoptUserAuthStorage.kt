package org.sopt.androidseminar.response

import android.content.Context

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

    fun hasUserData(context: Context) = getUserId(context).isNotEmpty() && getUserPw(context).isNotEmpty()
}