package `fun`.gladkikh.app.fastpallet6.ui.base

import `fun`.gladkikh.app.fastpallet6.ui.activity.MainActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController

abstract class BaseFragment : Fragment() {
    protected abstract val layoutRes: Int

    protected lateinit var mainActivity: MainActivity
    protected lateinit var navController: NavController
    protected abstract val viewModel: BaseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutRes, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainActivity = activity as MainActivity
        navController = mainActivity.navController


    }

    override fun onResume() {
        super.onResume()
        initSubscription()
    }

    protected open fun initSubscription() {
        viewModel.message.observe(viewLifecycleOwner, Observer {
            mainActivity.showMessage(it)
        })

        viewModel.messageError.observe(viewLifecycleOwner, Observer {
            mainActivity.showErrorMessage(it)
        })

        viewModel.showProgress.observe(viewLifecycleOwner, Observer {
            if (it) {
                mainActivity.showProgress()
            } else {
                mainActivity.hideProgress()
            }
        })
    }
}