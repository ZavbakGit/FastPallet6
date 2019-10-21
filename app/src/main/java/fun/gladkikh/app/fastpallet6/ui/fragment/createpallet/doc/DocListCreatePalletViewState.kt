package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.doc

import `fun`.gladkikh.app.fastpallet6.domain.entity.CreatePallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.ProductItemCreatePallet

data class DocListCreatePalletViewState(var list:List<ProductItemCreatePallet> = listOf())
data class DocEntityCreatePalletViewState(var entity:CreatePallet? = null)