package com.jemmycalak.listrepository.utils

import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jemmycalak.common.utils.loadImage
import com.jemmycalak.listrepository.R
import com.jemmycalak.listrepository.adapater.ListRepositoryAdapter
import com.jemmycalak.listrepository.viewmodel.ListRespositoryViewModel
import com.jemmycalak.model.Item

object Bindings {

    @JvmStatic
    @BindingAdapter("app:title")
    fun setupTile(t:TextView, s:String?){
        t.apply {
            if(!s.isNullOrEmpty()) text =s
        }
    }

    @JvmStatic
    @BindingAdapter("app:image")
    fun setImage(i:ImageView, url:String?){
        i.apply {
            if(!url.isNullOrEmpty()) i.loadImage(context, url, R.drawable.ic_launcher_background)
        }
    }

    @JvmStatic
    @BindingAdapter("android:text")
    fun setText(e:EditText, s:String?){
        if(e.text.toString() != s) e.setText(s)
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "android:text")
    fun setTypeText(e:EditText):String{
        try {
            return e.text.toString()
        }catch (e:Exception){
            e.printStackTrace()
        }
        return ""
    }

    @JvmStatic
    @BindingAdapter("app:repository", "app:isLoadMore")
    fun setDataRepo(r:RecyclerView, data:ArrayList<Item>?, viewModel:ListRespositoryViewModel){
        var adapter = (r.adapter as? ListRepositoryAdapter)
        if(adapter == null){
            adapter = ListRepositoryAdapter()
            r.adapter = adapter
        }
        adapter.addItem(data ?: arrayListOf(), viewModel.loadMore).also {
            viewModel.loadMore = false
        }
    }

    @JvmStatic
    @InverseBindingAdapter(attribute = "app:isLoadMore", event = "")
    fun setLoadMore(r:RecyclerView):Boolean{
        return false
    }

    @JvmStatic
    @BindingAdapter("app:onScroll")
    fun setOnScroll(r:RecyclerView, viewModel:ListRespositoryViewModel){
        r.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val manager = recyclerView.layoutManager as LinearLayoutManager
                val visibleItemCount = manager.childCount
                val totalItemCount = manager.itemCount
                val firstVisibleItemPosition = manager.findFirstVisibleItemPosition()
                if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && !viewModel.loadMore) {
                    if (totalItemCount < (viewModel.dataRepository.value?.data?.totalCount ?:0)) {
                        //loader(true)
                        (r.adapter as ListRepositoryAdapter).IS_AVAILABLE_PAGE = true
                        viewModel.page++
                        viewModel.loadMore = true
                        viewModel.searchRepository(viewModel.keyword.value.toString(), viewModel.page, 10, false)
                    }
                }
            }
        })
    }

}