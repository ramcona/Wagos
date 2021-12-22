package com.can.wagolib

import android.content.Context
import com.can.wagolib.callback.WagoCallback
import com.can.wagolib.utilities.SharedPref
import com.can.wagolib.viewModel.VerificationViewModel

class WagoVerification(private val context: Context, private val wagoCallback: WagoCallback) {

    private var pref = SharedPref(context)
    private var viewModel:VerificationViewModel = VerificationViewModel(context, wagoCallback)
    private var apptoken = pref.getTokens()

    fun setTokenApp(apptoken:String){
        pref.setToken(apptoken)
    }

    fun myAppToken():String{
        return pref.getTokens()
    }

    fun requestOTP(phone:String){
        if (apptoken.isEmpty()){
            wagoCallback.onError(context.getString(R.string.teks_anda_tidak_memiliki_token))
        }else{
            viewModel.requestOTP(phone, apptoken)
        }
    }

    fun resendCode(token:String){
        if (apptoken.isEmpty()){
            wagoCallback.onError(context.getString(R.string.teks_anda_tidak_memiliki_token))
        }else{
            viewModel.resendCode(token, apptoken)
        }
    }

    fun verification(token:String, otpCode:String){
        if (apptoken.isEmpty()){
            wagoCallback.onError(context.getString(R.string.teks_anda_tidak_memiliki_token))
        }else{
            viewModel.verification(otpCode, token, apptoken)
        }
    }

}