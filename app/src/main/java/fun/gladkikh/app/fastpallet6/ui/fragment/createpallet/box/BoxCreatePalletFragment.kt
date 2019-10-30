package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.box

import `fun`.gladkikh.app.fastpallet6.R
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseFragment
import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.box_screen_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class BoxCreatePalletFragment : BaseFragment() {
    override val layoutRes = R.layout.box_screen_fragment
    override val viewModel: BoxCreatePalletViewModel by viewModel()

    companion object {
        val EXTRA_GUID = this::class.java.name + "extra.GUID"
    }

    override fun initSubscription() {
        super.initSubscription()

        //Если добавляли, то возьмем из Из ViewModel нет из arguments
        val guid = viewModel.guid ?: arguments?.get(EXTRA_GUID) as String
        viewModel.setGuid(guid)

        viewModel.getViewSate().observe(viewLifecycleOwner, Observer {
            refreshScreen(it)
        })

        btAdd.setOnClickListener {
            viewModel.addBox("${(10..99).random()}123456789")
        }

        mainActivity.barcodeLiveData.observe(viewLifecycleOwner, Observer {
            viewModel.addBox(it)
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
        tvInfo.text = "Прогресс: ${viewState.progress}"
    }

}