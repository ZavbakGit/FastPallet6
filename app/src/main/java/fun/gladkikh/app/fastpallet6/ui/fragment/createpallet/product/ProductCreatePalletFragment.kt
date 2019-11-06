package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.product

import `fun`.gladkikh.app.fastpallet6.Constants.KEY_9
import `fun`.gladkikh.app.fastpallet6.R
import `fun`.gladkikh.app.fastpallet6.common.toSimpleDateTime
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.product.ItemProductScreenCreatePalletData
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseFragment
import `fun`.gladkikh.app.fastpallet6.ui.base.Command
import `fun`.gladkikh.app.fastpallet6.ui.base.MyBaseAdapter
import `fun`.gladkikh.app.fastpallet6.ui.base.startConfirmDialog
import `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.pallet.PalletCreatePalletFragment
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.screen_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductCreatePalletFragment : BaseFragment() {
    override val layoutRes = R.layout.screen_fragment
    override val viewModel: ProductCreatePalletViewModel by viewModel()
    private lateinit var adapter: Adapter

    companion object {
        val EXTRA_GUID = this::class.java.name + "extra.GUID"
    }

    override fun initSubscription() {
        super.initSubscription()

        //Если добавляли, то возьмем из Из ViewModel нет из arguments
        val guid = viewModel.guid ?: arguments?.get(EXTRA_GUID) as String

        adapter = Adapter(activity as Context)
        listView.adapter = adapter

        viewModel.getViewSate().observe(viewLifecycleOwner, Observer {
            refreshScreen(it)
        })

        viewModel.setGuid(guid)

        listView.setOnItemClickListener { _, _, i, _ ->
            adapter.list[i].palGuid?.let { openPallet(it) }
        }

        btTest.setOnClickListener {
            val barcode = "<pal>0214000000${(10..99).random()}</pal>"
            viewModel.scanBarcode(barcode)
            mainActivity.showMessage(barcode)
        }

        viewModel.getCommand().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Command.StartConfirmDialog -> {
                    startConfirmDialog(activity!!, "Удалить запись?") {
                        viewModel.dellPallet((it.data as Int))
                    }
                }
            }
        })

        mainActivity.barcodeLiveData.observe(viewLifecycleOwner, Observer {
            viewModel.scanBarcode(it)
        })

        mainActivity.getKeyDownLiveData().observe(viewLifecycleOwner, Observer {
            when (it) {
                KEY_9 -> {
                    listView.selectedItemPosition.takeUnless { position ->
                        position == -1
                    }?.run {
                        viewModel.startDell(this)
                    }

                }

            }
        })
    }

    private fun openPallet(guidPallet: String) {
        val bundle = Bundle()
        bundle.putString(PalletCreatePalletFragment.EXTRA_GUID, guidPallet)
        mainActivity.navController
            .navigate(R.id.action_productCreatePalletFragment_to_palletCreatePalletFragment, bundle)
    }


    @SuppressLint("SetTextI18n")
    fun refreshScreen(viewState: ProductScreenCreatePalletViewState) {


        tvDoc.text = "Документ: " + viewState.data?.docDescription

        tvProduct.text = "Товар: ${viewState.data?.prodNameProduct} \n" +
                "Паллет: ${viewState.data?.totalProdCountPallet} " +
                "Коробок: ${viewState.data?.totalProdCountBox} " +
                "Строк: ${viewState.data?.totalProdRow} " +
                "Вес: ${viewState.data?.totalProdWeight} "




        tvInfo.text = "Прогресс: ${viewState.progress} \n" +
                "Удалить (9)"

        adapter.list = viewState.list
    }

    private class Adapter(mContext: Context) : MyBaseAdapter<ItemProductScreenCreatePalletData>(mContext) {
        override fun bindView(item: ItemProductScreenCreatePalletData, holder: Any) {
            holder as ViewHolder
            holder.tvInfo.text = "Паллета: ${item.palNumber}   Дата: ${item.palDataChanged?.toSimpleDateTime()}"
            holder.tvInfo2.text = "Мест: ${item.totalPalCountBox} Вес: ${item.totalPalWeight}"
        }

        override fun getLayout(): Int = R.layout.item_screen
        override fun createViewHolder(view: View): Any =
            ViewHolder(
                view
            )
    }

    private class ViewHolder(view: View) {
        var tvInfo: TextView = view.findViewById(R.id.tvInfo)
        var tvInfo2: TextView = view.findViewById(R.id.tvInfo2)
    }

}