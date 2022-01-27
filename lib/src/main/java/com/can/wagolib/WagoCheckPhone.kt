package com.can.wagolib

import android.content.Context
import com.can.wagolib.callback.WagoCheckValidPhoneCallback
import com.can.wagolib.utilities.SharedPref
import com.can.wagolib.viewModel.CheckValidViewModel

class WagoCheckPhone(val context: Context, private val callbackCheckPhone:WagoCheckValidPhoneCallback) {

    private var pref = SharedPref(context)
    private var viewModel:CheckValidViewModel = CheckValidViewModel(context, callbackCheckPhone)
    private var apptoken = pref.getTokens()


    fun check(phone:String){
        if (apptoken.isEmpty()){
            callbackCheckPhone.onError(context.getString(R.string.teks_anda_tidak_memiliki_token))
        }else{
            viewModel.hit(phone, apptoken)
        }
    }

}