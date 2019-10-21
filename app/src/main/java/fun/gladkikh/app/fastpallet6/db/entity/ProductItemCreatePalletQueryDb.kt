package `fun`.gladkikh.app.fastpallet6.db.entity

data class ProductItemCreatePalletQueryDb(
    val prodName: String?,
    val prodGuid: String,
    val palCount: Int?,
    val boxRow:Int?,
    val boxWeight: Float?,
    val boxCount: Int?
)