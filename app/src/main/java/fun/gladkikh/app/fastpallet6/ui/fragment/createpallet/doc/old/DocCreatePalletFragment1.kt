package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.doc.old

import `fun`.gladkikh.app.fastpallet6.R
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.doc.old.ProductItemCreatePallet
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseFragment
import `fun`.gladkikh.app.fastpallet6.ui.base.MyBaseAdapter
import `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.product.ProductCreatePalletFragment
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.doc_create_pallet_frag.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DocCreatePalletFragment1 :BaseFragment(){
    override val layoutRes = R.layout.doc_create_pallet_frag
    override val viewModel: DocCreatePalletViewModel1 by viewModel()
    private lateinit var adapter: Adapter

    companion object {
        val EXTRA_GUID = this::class.java.name + "extra.GUID"
    }

    override fun initSubscription() {
        super.initSubscription()
        viewModel.setGuid(arguments?.get(EXTRA_GUID) as String)

        viewModel.getEntityViewSate().observe(viewLifecycleOwner, Observer {
            tvInfo.text = it.entity?.description?:""
        })

        viewModel.showProgressBarFrag.observe(viewLifecycleOwner, Observer {
            if (it){
                smoothProgressBar.visibility = View.VISIBLE
            }else{
                smoothProgressBar.visibility = View.GONE
            }
        })


        adapter =
            Adapter(
                activity as Context
            )
        listView.adapter = adapter

        viewModel.getListViewSate().observe(viewLifecycleOwner, Observer {
            adapter.list = it.list
        })

        listView.setOnItemClickListener { _, _, i, _ ->
            val bundle = Bundle()
            bundle.putString(ProductCreatePalletFragment.EXTRA_GUID, adapter.list[i].guid)
            mainActivity.navController
                .navigate(R.id.action_docCreatePalletFragment_to_productCreatePalletFragment, bundle)
        }

    }

    private class Adapter(mContext: Context) : MyBaseAdapter<ProductItemCreatePallet>(mContext) {
        override fun bindView(item: ProductItemCreatePallet, holder: Any) {
            holder as ViewHolder
            holder.tvInfo.text = item.name
            holder.tvLeft.text = "${item.palCount} п ${item.boxCount} м ${item.boxWeight} к"
            holder.tvRight.text = ""
        }

        override fun getLayout(): Int = R.layout.base_item
        override fun createViewHolder(view: View): Any =
            ViewHolder(
                view
            )
    }

    private class ViewHolder(view: View) {
        var tvInfo: TextView = view.findViewById(R.id.tvInfo)
        var tvLeft: TextView = view.findViewById(R.id.tvLeft)
        var tvRight: TextView = view.findViewById(R.id.tvRight)
    }
}