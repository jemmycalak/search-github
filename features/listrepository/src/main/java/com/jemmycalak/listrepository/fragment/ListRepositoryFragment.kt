package com.jemmycalak.listrepository.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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

    val viewModel: ListRespositoryViewModel by viewModel()
    lateinit var binding: FragmentListRepositoryBinding
    var loadMore = false
    var page = 1
    var maxItem = 0

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListRepositoryBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initUI()
    }

    private fun initUI() {
        var timer = Timer()
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
                            loadMore = true
                            page++
                            viewModel.searchRepository(binding.searchview.text.toString(), page, 10, false)
                        }
                    }
                }
            })
        }
        binding.searchview.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                timer.cancel()
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        if (binding.searchview.text.toString().isNotEmpty()) {
                            loader(false)
                            page = 1
                            loadMore = false
                            viewModel.searchRepository(
                                binding.searchview.text.toString(),
                                page,
                                10,
                                false
                            )
                        }
                    }
                }, 1000)
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    override fun setupObserver(mviewModel: BaseViewModel) {
        super.setupObserver(mviewModel)

        viewModel.dataRepository.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    if (it.data?.items != null) {
                        (binding.recyclerView.adapter as ListRepositoryAdapter).addItem(it.data!!.items!!, loadMore)
                        maxItem = it!!.data!!.totalCount!!
                    }
                    binding.layoutEmpty.visibility = if(it.data?.items!!.size > 0)View.GONE else View.VISIBLE
                    loadMore= false
                }
            }
            loader(false)
        })
    }

    fun loader(isLoadMore: Boolean) {
        (binding.recyclerView.adapter as ListRepositoryAdapter).IS_AVAILABLE_PAGE = isLoadMore
    }
}
