package `fun`.gladkikh.app.fastpallet6.domain

import `fun`.gladkikh.app.fastpallet6.domain.entity.CreatePallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.Status



fun <T>getStatusDoc(doc: T): Status?{
    when (doc) {
        is CreatePallet -> {
            return Status.getStatusById(doc.status?:0)
        }
        else -> throw Throwable()
    }
}

fun checkEditDocByStatus(status:Status?):Boolean{
    return status in listOf(Status.LOADED, Status.NEW)
}

