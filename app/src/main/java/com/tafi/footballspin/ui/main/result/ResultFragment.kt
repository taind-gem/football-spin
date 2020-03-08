package com.tafi.footballspin.ui.main.result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tafi.footballspin.R
import com.tafi.footballspin.data.db.model.Match
import com.tafi.footballspin.recyclerview.MatchAdapter
import com.tafi.footballspin.recyclerview.devider.VerticalSpaceDecoration
import com.tafi.footballspin.ui.base.BaseFragment
import com.tafi.footballspin.utils.CommonUtils
import kotlinx.android.synthetic.main.fragment_result.*
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class ResultFragment : BaseFragment(), IResultView {

    @Inject
    lateinit var presenter: ResultPresenter<IResultView>

    private lateinit var mMatchAdapter: MatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        activityComponent?.let { component ->
            component.inject(this)
            presenter.onAttach(this)
        }

        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mMatchAdapter = MatchAdapter(activity!!)
        val mLayoutManager = LinearLayoutManager(activity!!)

        rc_match.apply {
            layoutManager = mLayoutManager
            adapter = mMatchAdapter
            addItemDecoration(VerticalSpaceDecoration(CommonUtils.dpToPx(activity!!, 12f)))
        }

        presenter.onViewInitialized()
    }

    override fun updateMatchList(matches: List<Match>) {
        mMatchAdapter.mMatchList = matches
    }

}
