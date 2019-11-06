package `fun`.gladkikh.app.fastpallet6.domain.entity.screens.documents

import java.util.*

data class ItemDocumentsScreenData(
    var guid: String,
    var status: Int?,
    var number: String?,
    var date: Date?,
    var description: String?,
    var type: Int,
    var dataChange: Date?
)