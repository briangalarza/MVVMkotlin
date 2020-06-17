package com.briangalarza.mvvmkotlin.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.briangalarza.mvvmkotlin.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

//Creamos un spinner
fun getProgressDrawable(context:Context):CircularProgressDrawable{
    return CircularProgressDrawable(context).apply{
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

//Cargamos la imagen con la libreria Glide
fun ImageView.loadImage(uri:String?,progressDrawable: CircularProgressDrawable){
    //Opciones
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)
    //Glide
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)

}