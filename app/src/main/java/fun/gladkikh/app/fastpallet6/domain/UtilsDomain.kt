package `fun`.gladkikh.app.fastpallet6.domain

import `fun`.gladkikh.app.fastpallet6.domain.entity.CreatePallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.Status
import io.reactivex.Flowable

fun isPallet(barcode: String): Boolean {
    return barcode.startsWith("<pal>", true) && barcode.endsWith("</pal>", true)
}

fun <T>getStatusDoc(doc: T): Status?{
    when (doc) {
        is CreatePallet -> {
            return Status.getStatusById(doc.status?:0)
        }
        else -> throw Throwable()
    }
}

fun <T>checkEditDoc(doc:T):Boolean{
    val status = getStatusDoc(doc)
    return status in listOf(Status.LOADED, Status.NEW)
}

fun getNumberDocByBarCode(barcode: String): String {
    //<pal>021400000007</pal>
    if (!isPallet(barcode)) {
        throw Throwable("Не паллета!")
    }

    val strCode = barcode.replace("<pal>", "").replace("</pal>", "")


    val finishPref = 2 + strCode.substring(0, 2).toInt()
    val strKir = strCode.substring(2, finishPref)

    val prefKir = Flowable.fromIterable(strKir.toCharArray().toList())
        .buffer(2)
        .map {
            getCyrillicLetterByNumber(it.joinToString(""))
        }
        .toList().blockingGet().joinToString("")


    return prefKir + strCode.takeLast(strCode.length - finishPref)
}