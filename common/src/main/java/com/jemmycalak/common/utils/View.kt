package com.jemmycalak.common.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.MemoryCategory
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar

fun ImageView.loadImage(context: Context, url: String, @DrawableRes placeHolder: Int) {
    setMemoryCategory(context)
    Glide.with(context).load(url)
        .apply(RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE))
        .apply(RequestOptions.placeholderOf(placeHolder))
        .into(this)
}

private fun setMemoryCategory(context: Context) {
    Glide.get(context).setMemoryCategory(MemoryCategory.NORMAL)
}


/**
 * Display simple snackbar
 */
fun Fragment.showSnackbar(snackbarText: String, timeLength: Int) {
    activity?.let { Snackbar.make(it.findViewById<View>(android.R.id.content), snackbarText, timeLength).show() }
}
