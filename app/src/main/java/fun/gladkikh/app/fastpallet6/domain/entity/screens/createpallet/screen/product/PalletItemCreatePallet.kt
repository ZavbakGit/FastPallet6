package `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.product

data class PalletItemCreatePallet(
    val palNumber:String?,
    val prodName: String?,
    val guid: String,
    val boxRow:Int?,
    val boxWeight: Float?,
    val boxCount: Int?
)