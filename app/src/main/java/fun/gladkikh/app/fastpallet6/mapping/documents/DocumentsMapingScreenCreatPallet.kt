package `fun`.gladkikh.app.fastpallet6.mapping.documents

import `fun`.gladkikh.app.fastpallet6.db.entity.documents.screen.ItemDocumentsScreenDataDb
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.documents.ItemDocumentsScreenData
import java.util.*

fun ItemDocumentsScreenDataDb.toObject(): ItemDocumentsScreenData {
    return ItemDocumentsScreenData(
        guid = guid,
        number = number,
        type = type,
        dataChange = Date(dataChange ?: 0L),
        status = status,
        description = description,
        date = Date(date ?: 0L)
    )
}

