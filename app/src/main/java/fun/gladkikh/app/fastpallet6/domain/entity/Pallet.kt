package `fun`.gladkikh.app.fastpallet6.domain.entity
import java.util.*

data class Pallet(
    var guid: String = "",
    var guidProduct:String ="",
    var number: String? = null,
    var barcode: String? = null,


    var dataChanged: Date? = null,
    var count: Float? = null,
    var countBox: Int? = null,

    var nameProduct: String? = null,
    var state: String? = null,
    var sclad: String? = null,

    var boxes: List<Box> = listOf()
)