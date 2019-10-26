package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.box

import `fun`.gladkikh.app.fastpallet6.R
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseFragment
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
        val guid = viewModel.guid?:arguments?.get(EXTRA_GUID) as String
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

    fun refreshScreen(viewState:BoxScreenViewState){
        tvDoc.text = "Документ: " + viewState.data?.docDescription?:""
        tvProduct.text = "Товар: " + viewState.data?.prodName
        tvPallet.text ="Паллет: " + (viewState.data?.palNumber?:" ") + " Мест ${viewState.data?.palTotalCountBox?:""} Кол ${viewState.data?.palTotalWeight?:""}"
        tvBox.text ="${viewState.data?.boxGuid} \n ${viewState.data?.boxDate}" +
                " \n Коробка: " + "Мест ${viewState.data?.boxCountBox?:""} Кол ${viewState.data?.boxWeight?:""}"

        tvBuffer.text ="Буфер: " + viewState.sizeBuffer.toString()
        tvInfo.text = "Прогресс: ${viewState.progress}"
    }

}