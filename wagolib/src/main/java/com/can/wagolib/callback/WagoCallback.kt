package com.can.wagolib.callback

import com.can.wagolib.network.response.WagooResponse

interface WagoCallback {

    fun onLoading(value:Boolean)
    fun onError(msg:String)
    fun onSuccessVerification(response:WagooResponse)
    fun onSuccessRequest(token:String)
    fun onSuccessResendCode()

}