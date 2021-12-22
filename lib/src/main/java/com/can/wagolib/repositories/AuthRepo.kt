package com.can.wagolib.repositories

import com.can.wagolib.model.Verification
import com.can.wagolib.network.response.WagooResponse
import com.can.wagolib.utilities.BaseHelper
import io.reactivex.Observable

class AuthRepo: BaseHelper(){

    fun requestOTP(phone:String, apptoken:String): Observable<WagooResponse> {
        val model = Verification()
        model.phone = phone
        return  ApiServiceServer.request(apptoken, model)
    }

    fun resend(token:String, apptoken:String): Observable<WagooResponse> {
        val model = Verification()
        model.token = token

        return  ApiServiceServer.resendCode(apptoken, model)
    }

    fun verification(token:String, otpCode:String, apptoken:String): Observable<WagooResponse> {
        val model = Verification()
        model.token = token
        model.otp_code = otpCode

        return  ApiServiceServer.verification(apptoken, model)
    }

    fun checkValidNumber(phone:String, apptoken:String): Observable<WagooResponse> {
        val model = Verification()
        model.phone = phone

        return  ApiServiceServer.checkValidWhatsapp(apptoken, model)
    }

}