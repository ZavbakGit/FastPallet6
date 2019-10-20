package `fun`.gladkikh.app.fastpallet6.common

import java.text.SimpleDateFormat
import java.util.*

fun Date.toSimpleDateTime() : String {
    val format = SimpleDateFormat("dd.mm.yyyy hh:mm:ss")
    return format.format(this)
}

fun Date.toSimpleDate() : String {
    val format = SimpleDateFormat("dd.mm.yyyy")
    return format.format(this)
}