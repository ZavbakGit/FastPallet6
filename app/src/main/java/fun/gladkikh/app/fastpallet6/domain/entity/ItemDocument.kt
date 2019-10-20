package `fun`.gladkikh.app.fastpallet6.domain.entity

import java.util.*

data class ItemDocument(
    val guid: String,
    val status: Int?,
    val number: String?,
    val date: Date?,
    val description: String?,
    val type: Type,
    val dataChange:Date?
)