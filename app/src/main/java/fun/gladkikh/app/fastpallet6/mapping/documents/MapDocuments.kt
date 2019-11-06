package `fun`.gladkikh.app.fastpallet6.mapping.documents

import `fun`.gladkikh.app.fastpallet6.db.entity.documents.old.DocumentItemQueryDb
import `fun`.gladkikh.app.fastpallet6.domain.entity.Document
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.documents.old.DocumentsItem
import `fun`.gladkikh.app.fastpallet6.domain.entity.Type
import `fun`.gladkikh.app.fastpallet6.mapping.createpallet.toCreatePallet
import `fun`.gladkikh.app.fastpallet6.network.intity.DocResponse
import java.util.*

fun DocumentItemQueryDb.toObject(): DocumentsItem {
   return DocumentsItem(
       guid = this.guid,
       number = this.number,
       date = this.date?.let { Date(it) },
       description = this.description,
       status = this.status,
       dataChange = this.dataChange?.let { Date(it) },
       type = Type.getTypeById(this.type)
   )
}

fun DocResponse.toDocument(): Document {
    return when (this.type) {
        Type.CREATE_PALLET.nameServer -> {
            this.toCreatePallet()
        }
        else -> throw kotlin.Throwable("Прищел неизвестный тип документа!")
    }
}
