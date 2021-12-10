package com.example.aop_part3_chapter4

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.pm.PackageManager

import android.content.pm.PackageInfo
import android.util.Base64
import android.util.Log
import com.example.aop_part3_chapter4.api.BookService
import com.example.aop_part3_chapter4.model.SearchBookDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getHashKey()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://dapi.kakao.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val bookService = retrofit.create(BookService::class.java)
        bookService.getBooksByName("KakaoAK 424afec09442f753bcabd2f7d9166293", "안녕하세요")
            .enqueue(object : Callback<SearchBookDto> {
                override fun onResponse(
                    call: Call<SearchBookDto>,
                    response: Response<SearchBookDto>
                ) {
                    if (response.isSuccessful.not()) {
                        return
                    }

                    response.body()?.let {
                        Log.d(TAG, "MainActivity - $it")

                        it.books.forEach { book ->
                            Log.d(TAG, "MainActivity - $book")
                        }
                    }
                }

                override fun onFailure(call: Call<SearchBookDto>, t: Throwable) {
                    Log.d(TAG, "MainActivity - $t")
                }
            })
    }

    private fun getHashKey() {
        var packageInfo: PackageInfo? = null
        try {
            packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SIGNATURES)
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        if (packageInfo == null) Log.e("KeyHash", "KeyHash:null")
        for (signature in packageInfo!!.signatures) {
            try {
                val md: MessageDigest = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            } catch (e: NoSuchAlgorithmException) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=$signature", e)
            }
        }
    }
}