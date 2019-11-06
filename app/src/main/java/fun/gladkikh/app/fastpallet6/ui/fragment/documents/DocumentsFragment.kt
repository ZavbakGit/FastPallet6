package `fun`.gladkikh.app.fastpallet6.ui.fragment.documents

import `fun`.gladkikh.app.fastpallet6.R
import `fun`.gladkikh.app.fastpallet6.domain.entity.Status
import `fun`.gladkikh.app.fastpallet6.domain.entity.Type.CREATE_PALLET
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.documents.ItemDocumentsScreenData
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseFragment
import `fun`.gladkikh.app.fastpallet6.ui.base.MyBaseAdapter
import `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.doc.DocCreatePalletFragment
import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.screen_fragment.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DocumentsFragment : BaseFragment() {
    override val layoutRes = R.layout.screen_fragment
    override val viewModel: DocumentsViewModel by viewModel()
    private lateinit var adapter: Adapter

    override fun initSubscription() {
        super.initSubscription()

        adapter = Adapter(activity as Context)
        listView.adapter = adapter

        viewModel.getViewSate().observe(viewLifecycleOwner, Observer {
            refreshScreen(it)
        })

        listView.setOnItemClickListener { _, _, i, _ ->
            val data = adapter.list[i]
            data.guid?.let {
                openDocument(guid = it, type = data.type)
            }
        }
    }




    @SuppressLint("SetTextI18n")
    fun refreshScreen(viewState: DocumentsScreenViewState) {
        tvTitle.text = "Документы"
        btTest.text = ""
        adapter.list = viewState.list

    }

    fun openDocument(guid: String, type: Int) {
        when (type) {
            CREATE_PALLET.id -> {
                val bundle = Bundle()
                bundle.putString(DocCreatePalletFragment.EXTRA_GUID, guid)
                mainActivity.navController
                    .navigate(R.id.action_documentsFragment_to_docCreatePalletFragment, bundle)
            }
        }
    }

    private class Adapter(mContext: Context) : MyBaseAdapter<ItemDocumentsScreenData>(mContext) {
        override fun bindView(item: ItemDocumentsScreenData, holder: Any) {
            holder as ViewHolder
            holder.tvInfo.text = "${item.description}"
            holder.tvInfo2.text = "Мест: ${Status.getStatusById(item.status!!)!!.fullName}"
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