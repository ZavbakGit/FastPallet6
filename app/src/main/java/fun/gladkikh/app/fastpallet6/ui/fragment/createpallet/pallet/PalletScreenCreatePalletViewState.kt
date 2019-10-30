package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.pallet

import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.pallet.PalletScreenCreatePalletData
import java.util.*

data class PalletScreenCreatePalletViewState(
    val data: PalletScreenCreatePalletData? = null,
    val list:List<ItemBox> = listOf(),
    val progress: Boolean = false
)

data class ItemBox(
    val boxBarcode: String?,
    val boxWeight: Float?,
    val boxCountBox: Int?,
    val boxData: Date?,
    val boxGuid: String?
)