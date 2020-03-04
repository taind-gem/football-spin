package com.tafi.footballspin.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tafi.footballspin.R
import com.tafi.footballspin.data.db.model.Player
import com.tafi.footballspin.ui.base.BaseFragment
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : BaseFragment(), IHomeView {

    @Inject
    lateinit var mPresenter: IHomePresenter<IHomeView>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        activityComponent?.let { component ->
            component.inject(this)
            mPresenter.onAttach(this)
        }

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPresenter.onViewInitialized()
    }

    override fun updateHomeView(players: List<Player>) {

    }
}
