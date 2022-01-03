package com.can.wagolib.network

import com.can.wagolib.model.Data
import com.can.wagolib.network.response.WagooResponse
import com.can.wagolib.utilities.Config
import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiServiceServer {

    @Headers(Config.API)
    @POST("default")
    fun default(): Observable<WagooResponse>

    @Headers(Config.API)
    @POST("request_otp")
    fun request(
        @Header("app_token") token:String,
        @Body data:Data
    ): Observable<WagooResponse>

    @Headers(Config.API)
    @POST("verification_post")
    fun verification(
        @Header("app_token") token:String,
        @Body data:Data
    ): Observable<WagooResponse>

    @Headers(Config.API)
    @POST("resend_otp")
    fun resendCode(
        @Header("app_token") token:String,
        @Body data:Data
    ): Observable<WagooResponse>

    @Headers(Config.API)
    @POST("check_wa")
    fun checkValidWhatsapp(
        @Header("app_token") token:String,
        @Body data:Data
    ): Observable<WagooResponse>


}