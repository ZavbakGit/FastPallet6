package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.box

import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.box.BoxScreenCreatePalletData


data class BoxScreenCreatePalletViewState(val data: BoxScreenCreatePalletData? = null,
                                          val sizeBuffer:Int = 0,
                                          val progress:Boolean = false)