package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.box

import `fun`.gladkikh.app.fastpallet6.Constants.KEY_1
import `fun`.gladkikh.app.fastpallet6.Constants.KEY_2
import `fun`.gladkikh.app.fastpallet6.Constants.KEY_3
import `fun`.gladkikh.app.fastpallet6.Constants.KEY_9
import `fun`.gladkikh.app.fastpallet6.R
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseFragment
import `fun`.gladkikh.app.fastpallet6.ui.base.Command.*
import `fun`.gladkikh.app.fastpallet6.ui.base.startConfirmDialog
import `fun`.gladkikh.app.fastpallet6.ui.base.startEditDialogDecimal
import `fun`.gladkikh.app.fastpallet6.ui.base.startEditDialogNumber
import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.screen_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BoxCreatePalletFragment : BaseFragment() {
    override val layoutRes = R.layout.screen_fragment
    override val viewModel: BoxCreatePalletViewModel by viewModel()


    companion object {
        val EXTRA_GUID = this::class.java.name + "extra.GUID"
    }

    override fun initSubscription() {
        super.initSubscription()

        //Если добавляли, то возьмем из Из ViewModel нет из arguments
        val guid = viewModel.guid ?: arguments?.get(EXTRA_GUID) as String


        viewModel.getViewSate().observe(viewLifecycleOwner, Observer {
            refreshScreen(it)
        })

        viewModel.setGuid(guid)

        btAdd.setOnClickListener {
            viewModel.scanBarcode("${(10..99).random()}123456789")
        }

        mainActivity.barcodeLiveData.observe(viewLifecycleOwner, Observer {
            viewModel.scanBarcode(it)
        })

        mainActivity.getKeyDownLiveData().observe(viewLifecycleOwner, Observer {
            when (it) {
                KEY_1 -> {
                    viewModel.startEditPlace()
                }
                KEY_2 -> {
                    viewModel.startEditWeight()
                }
                KEY_3 -> {
                    viewModel.startAddBox()
                }
                KEY_9 -> {
                    viewModel.executeDell()
                }

            }
        })


        viewModel.getCommand().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Close -> {
                    mainActivity.navController.popBackStack()
                }
                is StartConfirmDialog -> {
                    startConfirmDialog(activity!!, "Удалить запись?") {
                        viewModel.dell()
                    }
                }
                is StartEditNumberDialog -> {
                    startEditDialogNumber(
                        title = it.title,
                        supportFragmentManager = activity!!.supportFragmentManager,
                        text = (it.number?.toString()) ?: ""
                    ) { place ->
                        place.toIntOrNull()?.let { placeInt ->
                            viewModel.editPlace(placeInt)
                        }
                    }
                }
                is StartEditDecimalDialog -> {
                    startEditDialogDecimal(
                        title = it.title,
                        supportFragmentManager = activity!!.supportFragmentManager,
                        text = (it.number?.toString()) ?: ""
                    ) { weight ->
                        weight.toFloatOrNull()?.let { weightFloat ->
                            viewModel.editWeight(weightFloat)
                        }
                    }
                }

            }
        })

    }

    @SuppressLint("SetTextI18n")
    fun refreshScreen(viewState: BoxScreenCreatePalletViewState) {


        tvDoc.text = "Документ: " + viewState.data?.docDescription

        tvProduct.text = "Товар: ${viewState.data?.prodNameProduct} \n" +
                "Паллет: ${viewState.data?.totalProdCountPallet} " +
                "Коробок: ${viewState.data?.totalProdCountBox} " +
                "Строк: ${viewState.data?.totalProdRow} " +
                "Вес: ${viewState.data?.totalProdWeight} "


        tvPallet.text = "Паллета: ${viewState.data?.palNumber ?: ""} \n" +
                "Коробок: ${viewState.data?.totalPalCountBox} " +
                "Строк: ${viewState.data?.totalPalRow} " +
                "Вес: ${viewState.data?.totalPalWeight} "



        tvBox.text = "Коробка: ${viewState.data?.boxGuid} \n" +
                "Коробок: ${viewState.data?.boxCountBox} " +
                "Вес: ${viewState.data?.boxWeight} "

        tvBuffer.text = "Буфер: ${viewState.sizeBuffer.toString()}"
        tvInfo.text = "Прогресс: ${viewState.progress} \n" +
                " Мест (1) Вес (2) Добавить(3) Удалить (9)"

    }

}