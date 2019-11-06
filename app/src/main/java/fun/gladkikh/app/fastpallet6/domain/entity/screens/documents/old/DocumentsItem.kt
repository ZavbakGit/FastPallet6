package `fun`.gladkikh.app.fastpallet6.domain.entity.screens.documents.old

import `fun`.gladkikh.app.fastpallet6.domain.entity.Type
import java.util.*

data class DocumentsItem(
    val guid: String,
    val status: Int?,
    val number: String?,
    val date: Date?,
    val description: String?,
    val type: Type,
    val dataChange:Date?
)