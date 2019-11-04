package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.pallet

import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.pallet.PalletScreenCreatePalletData
import java.util.*

data class PalletScreenCreatePalletViewState(
    var data: PalletScreenCreatePalletData? = null,
    var list:List<ItemBox> = listOf(),
    var progress: Boolean = false
)

data class ItemBox(
    val boxBarcode: String?,
    val boxWeight: Float?,
    val boxCountBox: Int?,
    val boxData: Date?,
    val boxGuid: String?
)