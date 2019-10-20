package `fun`.gladkikh.app.fastpallet6.db.entity



data class ItemDocumentQueryDb(
    val guid: String,
    val status: Int?,
    val number: String?,
    val date: Long?,
    val description: String?,
    val type: Int,
    val dataChange:Long?
)
