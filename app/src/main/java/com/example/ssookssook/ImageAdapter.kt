package com.example.ssookssook

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ssookssook.databinding.ItemImageBinding
import java.net.URI
import java.net.URL

class ImageAdapter(private val context: Context, private var images: ArrayList<String>) : RecyclerView.Adapter<ImageAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false);
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageAdapter.ViewHolder, position: Int) {
        //val viewholder = holder as ViewHolder

        var img = images!!.get(position)
        holder.bind(img)

    }

    override fun getItemCount(): Int {
        return images.size
    }

    inner class ViewHolder(private val binding: ItemImageBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(image: String) {
            //bindrnng.imageview.setImageURI(Uri.parse(image)) // 이게맞나
            //Glide.with(this).load(it.image).into(fragmentBinding!!.imageviewImg);
            Glide.with(binding.root).load(image).into(binding.imageview)
        }

    }
}