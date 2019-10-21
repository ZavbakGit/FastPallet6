package `fun`.gladkikh.app.fastpallet6.mapping.documents

import `fun`.gladkikh.app.fastpallet6.db.entity.DocumentItemQueryDb
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.documents.DocumentsItem
import `fun`.gladkikh.app.fastpallet6.domain.entity.Type
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