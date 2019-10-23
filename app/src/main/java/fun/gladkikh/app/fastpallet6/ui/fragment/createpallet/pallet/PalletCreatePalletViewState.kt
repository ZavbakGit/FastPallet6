package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.pallet

import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.pallet.BoxItemCreatePallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.pallet.TotalInfoPalletCreatePallet

data class PalletCreatePalletViewState(var list:List<BoxItemCreatePallet> = listOf())
data class TotalInfoCreatePalletViewState(var data:TotalInfoPalletCreatePallet?)