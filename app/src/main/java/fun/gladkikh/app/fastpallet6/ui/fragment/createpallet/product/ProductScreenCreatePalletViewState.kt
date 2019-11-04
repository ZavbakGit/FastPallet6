package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.product

import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.product.ItemProductScreenCreatePalletData
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.product.ProductScreenCreatePalletData

data class ProductScreenCreatePalletViewState(
    var data: ProductScreenCreatePalletData? = null,
    var list:List<ItemProductScreenCreatePalletData> = listOf(),
    var progress: Boolean = false
)

