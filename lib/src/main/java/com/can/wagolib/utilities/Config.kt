package com.can.wagolib.utilities

import android.os.Environment

object Config {

    private const val public_base_url = "https://wago.idaman.co.id/index.php/"
    private const val sandbox_base_url = "https://wago.idaman.co.id/index.php/"


    private var BASE_URL = if (Validasi().isRelease()){
        public_base_url
    }else{
        if (Validasi().isPublicTest()){
            public_base_url
        }else{
            sandbox_base_url
        }

    }

    val BASE_API = BASE_URL


    const val API = "X-API-KEY: 9UfyJBB^tGSB\$XM%4Qs2Jk?sH\$P!TQ9LTV1KLW7kYusxhcgr07J6i0mnxJwa6gV6ZY0mHGKBQ+@*r"

    val PUSH_NOTIFICATION: String = "PUSH_NOTIFICATION"

    val extra_model: String = "extraModel"
    val extra_type: String = "extraType"
    val extra_other:String = "extraOther"
    val extra_url: String = "extraUrl"
    val extra_list: String = "extraList"
    val extra_id: String = "extraID"

    var DIRECTORY_IMAGE: String =
        Environment.getExternalStorageDirectory().toString() + "/AVESMA/images/"

    fun shareArtikel(id:String):String{
        return "${BASE_URL}api/v1/share?id=${id}&type=article"
    }
    fun sharePembelajaran(id:String):String{
        return "${BASE_URL}api/v1/share?id=${id}&type=article-procedure"
    }

    fun shareResep(id:String):String{
        return "${BASE_URL}api/v1/share?id=${id}&type=article-recipe"
    }


}