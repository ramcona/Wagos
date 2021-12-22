package com.can.wagolib.utilities

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {

    val token = "Session"
    private val mypref = "MAIN_PREF_WA_VERIF"

    private val sp: SharedPreferences = context.getSharedPreferences(mypref, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sp.edit()


    fun setToken(value:String){
        setString(token, value)
    }

    fun getTokens():String{
        return  sp.getString(token, "") ?: ""
    }


    fun setString(keySP: String, value: String) {
        editor.putString(keySP, value)
        editor.commit()
    }

    fun clearAll() {
        editor.clear()
        editor.commit()
    }


}