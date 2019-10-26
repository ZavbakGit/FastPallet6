package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.box

import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.box.BoxScreenCreatePallet


data class BoxScreenViewState(val data:BoxScreenCreatePallet? = null,
                              val sizeBuffer:Int = 0,
                              val progress:Boolean = false)