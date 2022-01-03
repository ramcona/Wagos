package com.can.wagolib.network.response

import com.can.wagolib.model.Data
import com.can.wagolib.model.Meta
import java.io.Serializable

class WagooResponse : Serializable {
    var meta:Meta = Meta()
    var data:Data = Data()
}