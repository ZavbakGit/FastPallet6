package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.doc

import `fun`.gladkikh.app.fastpallet6.R
import `fun`.gladkikh.app.fastpallet6.domain.entity.Status
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.doc.ItemDocScreenCreatePalletData
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseFragment
import `fun`.gladkikh.app.fastpallet6.ui.base.MyBaseAdapter
import `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.product.ProductCreatePalletFragment
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.screen_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DocCreatePalletFragment : BaseFragment() {
    override val layoutRes = R.layout.screen_fragment
    override val viewModel: DocCreatePalletViewModel by viewModel()
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
            adapter.list[i].prodGuid?.let { openProduct(it) }
        }

    }

    private fun openProduct(guidPallet: String) {
        val bundle = Bundle()
        bundle.putString(ProductCreatePalletFragment.EXTRA_GUID, guidPallet)
        mainActivity.navController
            .navigate(R.id.action_docCreatePalletFragment_to_productCreatePalletFragment, bundle)
    }


    @SuppressLint("SetTextI18n")
    fun refreshScreen(viewState: DocScreenCreatePalletViewState) {

        tvDoc.text = "Документ: " + viewState.data?.description

        tvProduct.text = "Статус: ${Status.getStatusById(viewState.data?.status!!)}"

        tvInfo.text = "Прогресс: ${viewState.progress} \n"

        adapter.list = viewState.list

        btTest.text = "Ничего"
    }

    private class Adapter(mContext: Context) :
        MyBaseAdapter<ItemDocScreenCreatePalletData>(mContext) {
        override fun bindView(item: ItemDocScreenCreatePalletData, holder: Any) {
            holder as ViewHolder
            holder.tvInfo.text = "${item.prodNameProduct}"
            holder.tvInfo2.text = "Мест: ${item.totalProdCountBox} Вес: ${item.totalProdWeight}"
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