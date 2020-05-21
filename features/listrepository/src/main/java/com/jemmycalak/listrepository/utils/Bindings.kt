package com.jemmycalak.listrepository.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.jemmycalak.common.utils.loadImage
import com.jemmycalak.listrepository.R

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
}