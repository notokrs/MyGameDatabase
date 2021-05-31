package com.rusnoto.core.utils

import android.annotation.SuppressLint
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.rusnoto.core.data.source.remote.response.DevelopersItem
import com.rusnoto.core.data.source.remote.response.GenresItem
import com.rusnoto.core.data.source.remote.response.PlatformsItem
import java.text.SimpleDateFormat
import java.util.*

object Helper {
    @SuppressLint("SimpleDateFormat")
    fun getDate(): String{
        val calendar = Calendar.getInstance()
        val formatter = SimpleDateFormat("yyyy-MM-dd")

        calendar.apply {
            get(Calendar.YEAR)
            get(Calendar.MONTH) + 1
            set(Calendar.DAY_OF_MONTH, 1)
        }

        val firstDate = formatter.format(calendar.time)
        val secondDate = formatter.format(Calendar.getInstance().time)

        return  "$firstDate,$secondDate"
    }

    fun ImageView.setListImage(url: String){
        Glide.with(this.context).clear(this)
        Glide.with(this.context)
            .setDefaultRequestOptions(RequestOptions())
            .load(url)
            .apply(RequestOptions().override(250, 200))
            .into(this)
    }

    fun ImageView.setImage(url: String?){
        Glide.with(this.context).clear(this)
        Glide.with(this.context)
                .setDefaultRequestOptions(RequestOptions())
                .load(url)
                .into(this)
    }

    fun showProgressBar(progressBar: ProgressBar?, state: Boolean = true){
        if(state){
            progressBar?.visibility = View.VISIBLE
        }else{
            progressBar?.visibility = View.GONE
        }
    }

    fun platformExtractor(input: List<PlatformsItem>): String {
        val result = mutableListOf<String>()
        input.forEach { item ->
            result.add(item.platform.name)
        }
        return result.joinToString(", ")
    }

    fun genreExtractor(input: List<GenresItem>): String {
        val result = mutableListOf<String>()
        input.forEach { item ->
            result.add(item.name)
        }
        return result.joinToString(", ")
    }

    fun developerExtractor(input: List<DevelopersItem>): String {
        val result = mutableListOf<String>()
        input.forEach { item ->
            result.add(item.name)
        }
        return result.joinToString(", ")
    }
}