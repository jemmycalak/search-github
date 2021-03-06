package com.jemmycalak.listrepository.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jemmycalak.common.base.BaseFragment
import com.jemmycalak.common.base.BaseViewModel
import com.jemmycalak.listrepository.adapater.ListRepositoryAdapter
import com.jemmycalak.listrepository.databinding.FragmentListRepositoryBinding
import com.jemmycalak.listrepository.viewmodel.ListRespositoryViewModel
import com.jemmycalak.repository.utils.Resource
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class ListRepositoryFragment : BaseFragment() {

    val vModel: ListRespositoryViewModel by viewModel()
    lateinit var binding: FragmentListRepositoryBinding
    var loadMore = false
    var page = 1
    var maxItem = 0

    override fun getViewModel(): BaseViewModel = vModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentListRepositoryBinding.inflate(inflater, container, false).apply {
            viewModel = vModel
            lifecycleOwner = viewLifecycleOwner
            binding = this
        }.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()
    }

    private fun initUI() {
        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = ListRepositoryAdapter()
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val manager = recyclerView.layoutManager as LinearLayoutManager
                    val visibleItemCount = manager.childCount
                    val totalItemCount = manager.itemCount
                    val firstVisibleItemPosition = manager.findFirstVisibleItemPosition()
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && !loadMore) {
                        if (totalItemCount < maxItem) {
                            loader(true)
                            page++
                            vModel.searchRepository(binding.searchview.text.toString(), page, 10, false)
                        }
                    }
                }
            })
        }
    }

    override fun setupObserver(mviewModel: BaseViewModel) {
        super.setupObserver(mviewModel)

        vModel.dataRepository.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (it.data?.items != null) {
                        (binding.recyclerView.adapter as ListRepositoryAdapter).addItem(it.data!!.items!!, loadMore)
                        binding.layoutEmpty.visibility = if(it.data?.items!!.size > 0)View.GONE else View.VISIBLE
                        maxItem = it!!.data!!.totalCount!!
                    }
                    loader(false)
                }
                Resource.Status.ERROR -> {
                    loader(false)
                    page--
                }
            }
        })

        var timer = Timer()
        vModel.keyword.observe(viewLifecycleOwner, Observer {
            timer.cancel()
            timer = Timer()
            timer.schedule(object : TimerTask() {
                override fun run() {
                    if (binding.searchview.text.toString().isNotEmpty()) {
                        //loader(false)
                        vModel.page = 1
                        vModel.loadMore = false
                        vModel.searchRepository(
                            binding.searchview.text.toString(),
                            vModel.page,
                            10,
                            false
                        )
                    }
                }
            }, 1000)
        })
    }

    fun loader(isLoadMore: Boolean) {
        loadMore= isLoadMore
        (binding.recyclerView.adapter as ListRepositoryAdapter).IS_AVAILABLE_PAGE = isLoadMore
    }
}
