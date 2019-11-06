package `fun`.gladkikh.app.fastpallet6.db.entity.documents.old



data class DocumentItemQueryDb(
    var guid: String,
    var status: Int?,
    var number: String?,
    var date: Long?,
    var description: String?,
    var type: Int,
    var dataChange:Long?
)
