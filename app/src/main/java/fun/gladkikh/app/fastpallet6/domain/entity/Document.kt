package `fun`.gladkikh.app.fastpallet6.domain.entity


import java.util.*

sealed class Document
data class CreatePallet(
    var guid: String = "",
    var guidServer: String? = null,
    var status: Int? = null,
    var number: String? = null,

    var date: Date? = null,

    var dataChanged: Date? = null,

    var isWasLoadedLastTime: Boolean? = null,
    var description: String? = null,
    var barcode: String? = null,

    var listProduct: List<Product> = listOf()
) : Document()

