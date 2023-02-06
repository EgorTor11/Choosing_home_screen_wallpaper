package com.taranovegor91.choosingahomescreenwallpaper.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.taranovegor91.choosingahomescreenwallpaper.R
import com.taranovegor91.choosingahomescreenwallpaper.databinding.ImageItemBinding
import com.taranovegor91.choosingahomescreenwallpaper.domain.models.Image


class ImageAdapter(
    private val listener: Listener,
) : ListAdapter<Image, ImageAdapter.ImageHolder>(ItemCallback), View.OnClickListener,
    View.OnLongClickListener {
    override fun onClick(v: View) {
        val image = v.tag as Image
        when (v.id) {
         R.id.imageView->listener.onImageClick(image.copy())
        }
    }

    override fun onLongClick(v: View?): Boolean {
      //  listener.onRootLongClick()
        return true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ImageItemBinding.inflate(inflater, parent, false)
        binding.imageView.setOnClickListener(this)
        return ImageHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageHolder, position: Int) {
        val image = getItem(position)
        with(holder.binding) {
            imageView.tag=image
            if (image.imageUrl!=""){
            Glide.with(root.context).load(image.imageUrl).into(imageView)
            }else{
                imageView.setImageResource(android.R.drawable.ic_menu_gallery)
            }
        }
    }

    interface Listener {
        fun onImageClick(image: Image)
    }

    class ImageHolder(
        val binding: ImageItemBinding,
    ) : RecyclerView.ViewHolder(binding.root)

    object ItemCallback : DiffUtil.ItemCallback<Image>() {
        override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
            return oldItem == newItem
        }
    }
}
