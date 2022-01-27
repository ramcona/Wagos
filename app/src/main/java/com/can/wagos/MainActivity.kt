package com.can.wagos

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.can.wagolib.WagoCheckPhone
import com.can.wagolib.WagoVerification
import com.can.wagolib.callback.WagoCallback
import com.can.wagolib.callback.WagoCheckValidPhoneCallback
import com.can.wagolib.network.response.WagooResponse
import com.google.android.material.button.MaterialButton

/**
 * 1. implement WagoCallback for verification callback and
 * WagoCheckValidPhoneCallback for check valid number
 * MAKE SURE you was add network permission at manifest*/

class MainActivity : AppCompatActivity(), WagoCallback, WagoCheckValidPhoneCallback {

    /*member untuk verifikasi*/
    private lateinit var verification: WagoVerification

    /*member untuk check nomor terdaftar / tidak di wa  */
    private lateinit var checkValidPhone: WagoCheckPhone

    private var requestToken:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * 2. present WagoVerificaiton class and WagoCheckPhone
         * */
        verification = WagoVerification(this, this)

        checkValidPhone = WagoCheckPhone(this, this)


        /**
         * 3. add app token to library
         * you just 1 time to add this token, on splash, login or where but you must not call evrytime*/
        verification.setTokenApp("XzRCK9ssmEhw4Pry04njWFSbVmDHLT9KTcUthsDCbGT7HeqVL1JND3HOcdxC")

        /*checking alredy token*/
        print(verification.myAppToken())

        Log.e("MY TOKEN", "token : ${verification.myAppToken()}")


//        Log.e("MY TOKEN", "apptoken : ${verification.myAppToken()}")


        action()
    }

    private fun action(){

        findViewById<MaterialButton>(R.id.btnCheckValidNumber).setOnClickListener {
            val number = findViewById<EditText>(R.id.edt_phone).text.toString()

            /**
             * œ. check phone valid / not at whatsapp
             * @param phone must without + */
            checkValidPhone.check(phone = number)
        }

        findViewById<MaterialButton>(R.id.btnRequest).setOnClickListener {
            val number = findViewById<EditText>(R.id.edt_phone).text.toString()

            /**
             * 4. request otp code
             * @param phone must without + */
            verification.requestOTP(phone = number)
        }

        findViewById<MaterialButton>(R.id.btnResend).setOnClickListener {
            /**
             * 5. resend otp code (you just have 1 time to resend, please make sure for use it)
             * @param token cant empty (you have token after reqest otp code ) */
            verification.resendCode(token = requestToken)
        }

        findViewById<MaterialButton>(R.id.btnVerification).setOnClickListener {
            val otpCode = findViewById<EditText>(R.id.edt_otp).text.toString()
            /**
             * 5. verificaiton otp code
             * @param token cant empty (you have token after reqest otp code )
             * @param otpCode have send to whatsapp number*/
            verification.verification(token = requestToken, otpCode = otpCode)
        }

    }

    override fun onLoading(value: Boolean) {
        if (value){
            Toast.makeText(this, "loading", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "selesai loading", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    override fun resultValidNumberCheck(response: WagooResponse) {
        /**
         * result for number valid check
         * @param response.data.code 1 = number ready on whatsapp
         * @param response.data.code 0 = number already at whatsapp*/

        if (response.data.code == 0){
            Toast.makeText(this, "nomor tidak tersedia di whatsapp", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "nomor tersedia di whatsaapp", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSuccessVerification(response: WagooResponse) {
        Toast.makeText(this, "verifikasi sukses", Toast.LENGTH_SHORT).show()
    }

    override fun onSuccessRequest(token: String) {
        Toast.makeText(this, "kode terkirim", Toast.LENGTH_SHORT).show()
        requestToken = token
    }

    override fun onSuccessResendCode() {
        Toast.makeText(this, "mengirimkan ulang kode", Toast.LENGTH_SHORT).show()

    }
}