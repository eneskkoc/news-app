package com.example.navigation.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("visibility")
fun setVisibility(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}

@BindingAdapter("imageUrl")
fun image(view: ImageView, imageUrl:String?){
    if(!imageUrl.isNullOrEmpty()){
        Glide.with(view.context).load(imageUrl).into(view)
    }
}

@BindingAdapter("imageUrlWithPath")
fun imageWithPath(view: ImageView,imageUrl:String?){
    if(!imageUrl.isNullOrEmpty()){
       image(view,"$imageUrl")
    }
}