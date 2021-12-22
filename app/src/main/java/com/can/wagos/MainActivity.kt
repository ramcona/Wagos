package com.can.wagos

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.can.wagolib.WagoVerification
import com.can.wagolib.callback.WagoCallback
import com.can.wagolib.network.response.WagooResponse
import com.google.android.material.button.MaterialButton

/**
 * 1. implement @constructor WagoCallback
 * MAKE SURE you was add network permission at manifest*/
class MainActivity : AppCompatActivity(), WagoCallback {

    private lateinit var verification: WagoVerification
    private var requestToken:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * 2. present WagoVerificaiton class */
        verification = WagoVerification(this, this)

        /**
         * 3. add app token to library
         * you just 1 time to add this token, on splash, login or where but you must not call evrytime*/
        verification.setTokenApp("c4wo84cwwcc4w0kcs0ww88kcco80cc404w0g884c")

        /*checking alredy token*/
        print(verification.myAppToken())


//        Log.e("MY TOKEN", "apptoken : ${verification.myAppToken()}")


        action()
    }

    private fun action(){
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