package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.pallet

import `fun`.gladkikh.app.fastpallet6.Constants.KEY_3
import `fun`.gladkikh.app.fastpallet6.Constants.KEY_9
import `fun`.gladkikh.app.fastpallet6.R
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseFragment
import `fun`.gladkikh.app.fastpallet6.ui.base.Command
import `fun`.gladkikh.app.fastpallet6.ui.base.MyBaseAdapter
import `fun`.gladkikh.app.fastpallet6.ui.base.startConfirmDialog
import `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.box.BoxCreatePalletFragment
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.screen_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PalletCreatePalletFragment : BaseFragment() {
    override val layoutRes = R.layout.screen_fragment
    override val viewModel: PalletCreatePalletViewModel by viewModel()
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
            adapter.list[i].boxGuid?.let { openBox(it) }
        }

        btTest.setOnClickListener {
            val barcode = "${(10..99).random()}123456789"
            viewModel.scanBarcode(barcode)
            mainActivity.showMessage(barcode)
        }

        viewModel.getCommand().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Command.OpenForm -> {
                    openBox(it.data as String)
                }
                is Command.StartConfirmDialog -> {
                    startConfirmDialog(activity!!, "Удалить запись?") {
                        viewModel.dellBox((it.data as Int))
                    }
                }
            }
        })

        mainActivity.barcodeLiveData.observe(viewLifecycleOwner, Observer {
            viewModel.scanBarcode(it)
        })

        mainActivity.getKeyDownLiveData().observe(viewLifecycleOwner, Observer {
            when (it) {
                KEY_3 -> {
                    viewModel.startAddBox()
                }
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

    private fun openBox(guidBox: String) {
        val bundle = Bundle()
        bundle.putString(BoxCreatePalletFragment.EXTRA_GUID, guidBox)
        mainActivity.navController
            .navigate(R.id.action_palletCreatePalletFragment_to_boxCreatePalletFragment, bundle)
    }


    @SuppressLint("SetTextI18n")
    fun refreshScreen(viewState: PalletScreenCreatePalletViewState) {


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


        tvInfo.text = "Прогресс: ${viewState.progress} \n" +
                "Добавить(3) Удалить (9)"

        adapter.list = viewState.list

        btTest.text = "Генерирует штрихкод"
    }

    private class Adapter(mContext: Context) : MyBaseAdapter<ItemBox>(mContext) {
        override fun bindView(item: ItemBox, holder: Any) {
            holder as ViewHolder
            holder.tvInfo.text = "${item.boxGuid} ${item.boxData}"
            holder.tvInfo2.text = "Мест: ${item.boxCountBox} Вес: ${item.boxWeight}"
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