package com.can.wagolib.repositories

import com.can.wagolib.model.Data
import com.can.wagolib.network.response.WagooResponse
import com.can.wagolib.utilities.BaseHelper
import io.reactivex.Observable

class AuthRepo: BaseHelper(){

    fun requestOTP(phone:String, apptoken:String): Observable<WagooResponse> {
        val model = Data()
        model.phone = phone
        model.app_token = apptoken

        return  ApiServiceServer.request(apptoken, model)
    }

    fun resend(token:String, apptoken:String): Observable<WagooResponse> {
        val model = Data()
        model.token = token
        model.app_token = apptoken

        return  ApiServiceServer.resendCode(apptoken, model)
    }

    fun verification(token:String, otpCode:String, apptoken:String): Observable<WagooResponse> {
        val model = Data()
        model.token = token
        model.otp_code = otpCode
        model.app_token = apptoken

        return  ApiServiceServer.verification(apptoken, model)
    }

    fun checkValidNumber(phone:String, apptoken:String): Observable<WagooResponse> {
        val model = Data()
        model.phone = phone
        model.app_token = apptoken

        return  ApiServiceServer.checkValidWhatsapp(apptoken, model)
    }

}