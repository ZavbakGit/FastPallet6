package `fun`.gladkikh.app.fastpallet6.ui.fragment.documents

import `fun`.gladkikh.app.fastpallet6.R
import `fun`.gladkikh.app.fastpallet6.domain.entity.Status
import `fun`.gladkikh.app.fastpallet6.domain.entity.screens.documents.DocumentsItem
import `fun`.gladkikh.app.fastpallet6.ui.base.BaseFragment
import `fun`.gladkikh.app.fastpallet6.ui.base.MyBaseAdapter
import `fun`.gladkikh.app.fastpallet6.ui.fragment.createpallet.doc.DocCreatePalletFragment
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.TextView
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.documents_frag.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DocumentsFragment : BaseFragment() {
    override val layoutRes = R.layout.base_screen
    override val viewModel: DocumentsViewModel by viewModel()
    private lateinit var adapter: Adapter


    override fun initSubscription() {
        super.initSubscription()
        adapter =
            Adapter(activity as Context)
        listView.adapter = adapter

        viewModel.getViewSate().observe(viewLifecycleOwner, Observer {
            adapter.list = it.list
        })

        tvMenu.setOnClickListener {
            //viewModel.saveTestData()
            showMenu()
        }

        listView.setOnItemClickListener { _, _, i, _ ->
            val bundle = Bundle()
            bundle.putString(DocCreatePalletFragment.EXTRA_GUID, adapter.list[i].guid)
            mainActivity.navController
                .navigate(R.id.action_documentsFragment_to_docCreatePalletFragment, bundle)
        }

    }



    private fun showMenu() {
        PopupMenu(activity, tvMenu).run {
            inflate(R.menu.documents_menu)
            setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.settings -> {
                        mainActivity.navController
                            .navigate(R.id.action_documentsFragment_to_settingsFragment)
                        return@setOnMenuItemClickListener true
                    }
                    R.id.download -> {
                        viewModel.loadDocs()
                        return@setOnMenuItemClickListener true
                    }
                    else -> false
                }
            }
            show()
        }
    }

    private class Adapter(mContext: Context) : MyBaseAdapter<DocumentsItem>(mContext) {
        override fun bindView(item: DocumentsItem, holder: Any) {
            holder as ViewHolder
            holder.tvInfo.text = item.description
            holder.tvLeft.text = Status.getStatusById(item.status ?: 0)?.fullName
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