package com.example.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.Movie
import com.example.recyclerview.R
import com.example.recyclerview.Supplier
import com.example.recyclerview.databinding.AdapterLayoutBinding

class MovieAdapter(private val movie: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.MyViewHolder>() {

    var movieList: List<Movie> = ArrayList(movie)
    var onItemClick: ((String, Int) -> Unit)? = null
    var tracker: SelectionTracker<String>? = null

    init {
        setHasStableIds(true)
    }

    inner class MyViewHolder(private val binding: AdapterLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(movie: Movie?, isActive: Boolean) {
            binding.txvName.text = movie!!.title
            binding.txvgenre.text = movie.genre
            binding.txvyear.text = movie.year.toString()
            binding.root.setOnClickListener {
                onItemClick?.invoke(movie.title, movie.year)
            }
            if (isActive) {
                binding.root.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.root.context,
                        R.color.grey
                    )
                )
                binding.checkmarkIv.visibility = View.VISIBLE
                binding.txvyear.visibility = View.INVISIBLE
            } else {
                binding.root.setBackgroundColor(
                    ContextCompat.getColor(
                    binding.root.context,
                    R.color.white
                ))
                binding.checkmarkIv.visibility = View.INVISIBLE
                binding.txvyear.visibility = View.VISIBLE

            }
        }

        fun getItemDetails(): ItemDetailsLookup.ItemDetails<String> =
            object : ItemDetailsLookup.ItemDetails<String>() {
                override fun getPosition(): Int = bindingAdapterPosition
                override fun getSelectionKey(): String = movieList[bindingAdapterPosition].title
            }
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieAdapter.MyViewHolder, position: Int) {
        val movies = movieList[position]
        tracker?.let { holder.setData(movies, it.isSelected(movieList[position].title)) }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieAdapter.MyViewHolder {
        val binding =
            AdapterLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemId(position: Int): Long = position.toLong()


}