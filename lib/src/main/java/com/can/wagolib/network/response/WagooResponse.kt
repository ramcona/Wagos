package com.can.wagolib.network.response

import java.io.Serializable

class WagooResponse : Serializable {

    val error:Boolean  = false
    val code:Int = 0
    val message:String = ""
    var token:String = ""
}