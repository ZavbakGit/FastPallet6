package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.box

import `fun`.gladkikh.app.fastpallet6.R
import `fun`.gladkikh.app.fastpallet6.common.toSimpleDateTime
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseFragment
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.box_scr.*
import kotlinx.android.synthetic.main.documents_frag.tvInfo
import org.koin.androidx.viewmodel.ext.android.viewModel

class BoxCreatePalletFragment1 : BaseFragment() {
    override val layoutRes = R.layout.box_scr
    override val viewModel: BoxCreatePalletViewModel by viewModel()


    companion object {
        val EXTRA_GUID = this::class.java.name + "extra.GUID"
    }

    override fun initSubscription() {
        super.initSubscription()

//        if (!viewModel.changedGuid){
//            viewModel.setGuid(arguments?.get(EXTRA_GUID) as String)
//        }

        viewModel.getViewSate().observe(viewLifecycleOwner, Observer {
            tvInfo.text = "Коробка " + it.data?.boxGuid + " Паллета " + it.data?.palNumber

            tv_info_doc_right.text = "М${it.data?.palTotalCountBox.toString()} К${it.data?.palTotalWeight.toString()}"

            tvbarcode.text = it.data?.boxBarcode
            tvDate.text = it.data?.boxDate?.toSimpleDateTime()

            edPlace.setText(it.data?.boxCountBox.toString())
            edWeight.setText(it.data?.boxWeight.toString())

            tvBuffer.text = it.sizeBuffer.toString()
        })



//        mainActivity.barcodeLiveData.observe(viewLifecycleOwner, Observer {
//            viewModel.add(it)
//        })
//
//        tvDate.setOnClickListener {
//            viewModel.add("${(10..99).random()}123456789")
//        }


    }
}