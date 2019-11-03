package `fun`.gladkikh.app.fastpallet6.db.entity.creatpallet.screen.product.old

data class PalletItemCreatePalletQueryDb(
    val palNumber:String?,
    val prodName: String?,
    val palGuid: String,
    val boxRow:Int?,
    val boxWeight: Float?,
    val boxCount: Int?
)