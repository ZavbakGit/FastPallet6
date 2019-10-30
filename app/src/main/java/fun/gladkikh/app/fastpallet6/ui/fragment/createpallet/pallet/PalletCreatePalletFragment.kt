package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.pallet

import `fun`.gladkikh.app.fastpallet6.R
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseFragment
import `fun`.gladkikh.app.fastpallet6.ui.base.MyBaseAdapter
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

        adapter = Adapter(activity as Context)
        listView.adapter = adapter

        //Если добавляли, то возьмем из Из ViewModel нет из arguments
        val guid = viewModel.guid ?: arguments?.get(EXTRA_GUID) as String
        viewModel.setGuid(guid)

        viewModel.getViewSate().observe(viewLifecycleOwner, Observer {
            refreshScreen(it)
        })

        listView.setOnItemClickListener { _, _, i, _ ->
            val bundle = Bundle()
            bundle.putString(BoxCreatePalletFragment.EXTRA_GUID, adapter.list[i].boxGuid)
            mainActivity.navController
                .navigate(R.id.action_palletCreatePalletFragment_to_boxCreatePalletFragment, bundle)
        }

        btAdd.setOnClickListener {
            //viewModel.addBox("${(10..99).random()}123456789")
        }

        mainActivity.barcodeLiveData.observe(viewLifecycleOwner, Observer {
            //viewModel.addBox(it)
        })
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


        tvInfo.text = "Прогресс: ${viewState.progress}"

        adapter.list = viewState.list
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