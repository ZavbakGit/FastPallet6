package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.box

import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.box.BoxScreenCreatePalletData


data class BoxScreenCreatePalletViewState(
    var data: BoxScreenCreatePalletData? = null,
    var sizeBuffer:Int = 0,
    var progress:Boolean = false)