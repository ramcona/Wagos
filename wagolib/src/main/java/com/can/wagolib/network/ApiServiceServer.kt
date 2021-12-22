package com.can.wagolib.network

import com.can.wagolib.model.Verification
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
    @POST("auth/request")
    fun request(
        @Header("Token") token:String,
        @Body data:Verification
    ): Observable<WagooResponse>

    @Headers(Config.API)
    @POST("auth/verification")
    fun verification(
        @Header("Token") token:String,
        @Body data:Verification
    ): Observable<WagooResponse>

    @Headers(Config.API)
    @POST("auth/resend")
    fun resendCode(
        @Header("Token") token:String,
        @Body data:Verification
    ): Observable<WagooResponse>

    @Headers(Config.API)
    @POST("auth/valid")
    fun checkValidWhatsapp(
        @Header("Token") token:String,
        @Body data:Verification
    ): Observable<WagooResponse>


}