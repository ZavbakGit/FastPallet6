package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.product

import `fun`.gladkikh.app.fastpallet6.R
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.PalletItemCreatePallet
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseFragment
import `fun`.gladkikh.app.fastpallet6.ui.base.MyBaseAdapter
import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.doc_create_pallet_frag.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductCreatePalletFragment :BaseFragment(){
    override val layoutRes = R.layout.doc_create_pallet_frag
    override val viewModel: ProductCreatePalletViewModel by viewModel()
    private lateinit var adapter: Adapter

    companion object {
        val EXTRA_GUID = this::class.java.name + "extra.GUID"
    }

    override fun initSubscription() {
        super.initSubscription()
        viewModel.setGuid(arguments?.get(EXTRA_GUID) as String)

        adapter =
            Adapter(activity as Context)
        listView.adapter = adapter

        viewModel.getViewSate().observe(viewLifecycleOwner, Observer {
            adapter.list = it.list
        })

    }

    private class Adapter(mContext: Context) : MyBaseAdapter<PalletItemCreatePallet>(mContext) {
        override fun bindView(item: PalletItemCreatePallet, holder: Any) {
            holder as ViewHolder
            holder.tvInfo.text = item.palNumber
            holder.tvLeft.text = "${item.boxCount}  / ${item.boxWeight}"
            holder.tvRight.text = ""
        }

        override fun getLayout(): Int = R.layout.base_item
        override fun createViewHolder(view: View): Any =
            ViewHolder(view)
    }

    private class ViewHolder(view: View) {
        var tvInfo: TextView = view.findViewById(R.id.tvInfo)
        var tvLeft: TextView = view.findViewById(R.id.tvLeft)
        var tvRight: TextView = view.findViewById(R.id.tvRight)
    }
}