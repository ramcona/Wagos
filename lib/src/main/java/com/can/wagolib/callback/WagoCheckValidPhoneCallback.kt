package com.can.wagolib.callback

import com.can.wagolib.network.response.WagooResponse

interface WagoCheckValidPhoneCallback {

    fun onLoading(loading:Boolean)
    fun onError(msg:String)
    fun resultValidNumberCheck(response:WagooResponse)

}