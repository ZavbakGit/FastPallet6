package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.doc

import `fun`.gladkikh.app.fastpallet6.domain.entity.CreatePallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.doc.ItemDocScreenCreatePalletData

data class DocScreenCreatePalletViewState(
    var data: CreatePallet? = null,
    var list:List<ItemDocScreenCreatePalletData> = listOf(),
    var progress: Boolean = false
)

