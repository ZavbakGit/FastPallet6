package `fun`.gladkikh.app.fastpallet6.mapping.documents

import `fun`.gladkikh.app.fastpallet6.db.entity.ItemDocumentQueryDb
import `fun`.gladkikh.app.fastpallet6.domain.entity.ItemDocument
import `fun`.gladkikh.app.fastpallet6.domain.entity.Type
import java.util.*

fun ItemDocumentQueryDb.toObject():ItemDocument{
   return ItemDocument(
        guid = this.guid,
        number = this.number,
        date = this.date?.let {Date(it)},
        description = this.description,
        status = this.status,
        dataChange = this.dataChange?.let { Date(it) },
        type = Type.getTypeById(this.type)
    )
}