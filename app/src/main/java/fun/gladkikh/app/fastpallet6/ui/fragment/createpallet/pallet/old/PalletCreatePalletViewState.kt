package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.pallet.old

import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.pallet.old.BoxItemCreatePallet
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.pallet.old.TotalInfoPalletCreatePallet

data class PalletCreatePalletViewState(var list:List<BoxItemCreatePallet> = listOf())
data class TotalInfoCreatePalletViewState(var data: TotalInfoPalletCreatePallet?)