package `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.pallet.old

import `fun`.gladkikh.app.fastpallet6.R
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.createpallet.screen.pallet.old.BoxItemCreatePallet
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseFragment
import `fun`.gladkikh.app.fastpallet6.ui.base.MyBaseAdapter
import `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.box.BoxCreatePalletFragment
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.documents_frag.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class PalletCreatePalletFragment1 : BaseFragment() {
    override val layoutRes = R.layout.base_screen
    override val viewModel: PalletCreatePalletViewModel1 by viewModel()
    private lateinit var adapter: Adapter

    companion object {
        val EXTRA_GUID = this::class.java.name + "extra.GUID"
    }


    override fun initSubscription() {
        super.initSubscription()

        viewModel.setGuid(arguments?.get(EXTRA_GUID) as String)

        adapter =
            Adapter(
                activity as Context
            )
        listView.adapter = adapter

        viewModel.getViewSate().observe(viewLifecycleOwner, Observer {
            adapter.list = it.list
        })

        tvInfo.text = "Паллета ${arguments?.get(EXTRA_GUID)}"


        listView.setOnItemClickListener { _, _, i, _ ->
            val bundle = Bundle()
            bundle.putString(BoxCreatePalletFragment.EXTRA_GUID, adapter.list[i].boxGuid)
//            mainActivity.navController
//                .navigate(R.id.action_palletFragment_to_boxCreatePalletFragment, bundle)
        }

    }


    private class Adapter(mContext: Context) : MyBaseAdapter<BoxItemCreatePallet>(mContext) {
        override fun bindView(item: BoxItemCreatePallet, holder: Any) {
            holder as ViewHolder
            holder.tvInfo.text = item.boxGuid
            holder.tvLeft.text = "${item.palGuid}"
            holder.tvRight.text = "м ${item.boxCount} к ${item.boxWeight}"
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