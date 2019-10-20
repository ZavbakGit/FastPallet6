package `fun`.gladkikh.app.fastpallet6.ui.fragment

import `fun`.gladkikh.app.fastpallet6.R
import `fun`.gladkikh.app.fastpallet6.domain.entity.ItemDocument
import `fun`.gladkikh.app.fastpallet6.domain.entity.Status
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseFragment
import `fun`.gladkikh.app.fastpallet6.ui.base.MyBaseAdapter
import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.documents_frag.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DocumentsFragment :BaseFragment(){
    override val layoutRes = R.layout.documents_frag
    override val viewModel: DocumentsViewModel by viewModel()

    private lateinit var adapter: Adapter


    override fun initSubscription() {
        super.initSubscription()
        adapter = Adapter(activity as Context)
        listView.adapter = adapter

        viewModel.getViewSate().observe(viewLifecycleOwner, Observer {
            adapter.list = it.list
        })

        tvMenu.setOnClickListener {
            viewModel.saveTestData()
        }
    }

    private class Adapter(mContext: Context) : MyBaseAdapter<ItemDocument>(mContext) {
        override fun bindView(item: ItemDocument, holder: Any) {
            holder as ViewHolder
            holder.tvInfo.text = item.description
            holder.tvLeft.text = Status.getStatusById(item.status ?: 0)?.fullName
            holder.tvRight.text = ""
        }

        override fun getLayout(): Int = R.layout.fr_list_doc_item
        override fun createViewHolder(view: View): Any =
            ViewHolder(view)
    }

    private class ViewHolder(view: View) {
        var tvInfo: TextView = view.findViewById(R.id.tv_item_info)
        var tvLeft: TextView = view.findViewById(R.id.tv_info_doc_left)
        var tvRight: TextView = view.findViewById(R.id.tv_info_doc_right)
    }
}