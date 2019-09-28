package com.android.coding.challenge.module.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.android.coding.challenge.AppController
import com.android.coding.challenge.databinding.MovieListItemBinding
import com.android.coding.challenge.module.model.moviesListResponseModel.DataItem
import com.android.coding.challenge.module.viewmodel.ItemMoviesViewModel
import com.bumptech.glide.Glide
import java.util.ArrayList

import kotlin.properties.Delegates

class MoviesAdapter(private val movieList: ArrayList<DataItem>, private val context: Context) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>(), Filterable {

    private var filterMovieList = ArrayList<DataItem>()
    init {
        filterMovieList.addAll(movieList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = MovieListItemBinding.inflate(layoutInflater, parent, false)

        return MoviesViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = filterMovieList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        if (position != RecyclerView.NO_POSITION) {
            val data = filterMovieList[position]
            Glide.with(context)
                .load(data.poster)
                .thumbnail()
                .into(holder.binding.ivThumbnail)
            holder.bind(data)
        }
    }
    class MoviesViewHolder(val binding: MovieListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(movies: DataItem) {
            binding.vm = ItemMoviesViewModel(movies)
        }
    }
    /**
     * @return a filter used to constrain data
     */
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    filterMovieList.clear()
                    filterMovieList.addAll(movieList)
                } else {
                    filterMovieList.clear()
                    val filteredList = movieList.filter {

                        it.title!!.toLowerCase().trim().contains(charSequence.toString().toLowerCase().trim()) ||
                                it.genre!!.toLowerCase().trim().contains(charSequence.toString().toLowerCase().trim())
                    }
                    filterMovieList.addAll(filteredList)
                }
                val filterResults = FilterResults()
                filterResults.values = filterMovieList
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                notifyDataSetChanged()
            }
        }
    }
}