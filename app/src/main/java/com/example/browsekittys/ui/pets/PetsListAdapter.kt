package com.example.browsekittys.ui.pets


import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.browsekittys.databinding.PetsImageListBinding
import com.example.browsekittys.network.dataClass.DataResultItem
import timber.log.Timber

class PetsListAdapter(private val interaction: Interaction? = null) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataResultItem>() {

        override fun areItemsTheSame(oldItem: DataResultItem, newItem: DataResultItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: DataResultItem, newItem: DataResultItem): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, DIFF_CALLBACK)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PetsViewHolder(
            PetsImageListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            interaction,
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PetsViewHolder -> {
                holder.bind(differ.currentList[position], position)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<DataResultItem>) {
        differ.submitList(list)
    }

    class PetsViewHolder
    constructor(
        private val binding: PetsImageListBinding,
        private val interaction: Interaction?,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DataResultItem, position: Int) = with(binding) {
            progressBar.visibility  = View.VISIBLE
            loadingText.visibility = View.VISIBLE
            Glide.with(catImage.context)
                .load(item.url)
//                .placeholder(R.drawable.progress_anim)
//                .error(R.drawable.try_later)
                .listener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                     Timber.i("on Load Failed")
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progressBar.visibility  = View.GONE
                        loadingText.visibility = View.GONE
                        return false
                    }
                })
                .into(catImage)
            root.setOnClickListener {
                interaction?.onItemSelected(adapterPosition, item)

            }
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: DataResultItem)
    }
}