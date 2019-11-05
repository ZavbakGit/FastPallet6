package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.doc.old

import `fun`.gladkikh.app.fastpallet6.domain.entity.CreatePallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.doc.old.ProductItemCreatePallet

data class DocListCreatePalletViewState(var list:List<ProductItemCreatePallet> = listOf())
data class DocEntityCreatePalletViewState(var entity:CreatePallet? = null)