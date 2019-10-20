package `fun`.gladkikh.app.fastpallet6.domain.entity

import java.util.*

data class Box(
    var guid: String = "",
    var guidPallet:String = "",
    var barcode: String? = null,
    var weight: Float? = null,
    var countBox: Int? = null,
    var data: Date? = null

)