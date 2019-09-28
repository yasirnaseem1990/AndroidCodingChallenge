package com.android.coding.challenge.module.view.fragment


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.android.coding.challenge.R
import com.android.coding.challenge.module.adapter.MoviesAdapter
import com.android.coding.challenge.module.model.moviesListResponseModel.DataItem
import com.android.coding.challenge.module.viewmodel.MoviesListViewModel
import com.android.coding.challenge.util.MDToast
import kotlinx.android.synthetic.main.fragment_movies_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

/**
 * Created on 9/28/2019
 * Email ynaseem@an10.io
 * Organization AN10
 */

class MoviesListFragment : Fragment() {

    companion object {
        const val GRID_SPAN_COUNT = 3
    }

    private val viewModel: MoviesListViewModel by viewModel()
    private lateinit var moviesAdapter: MoviesAdapter
    lateinit var mdToast: MDToast

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Movies Data"
        initAdapter()
        initViewModel()

        etMovies.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                if (moviesAdapter != null) {
                    if (moviesAdapter.itemCount < 1) {
                        tvNoMoviesFound.visibility = View.VISIBLE
                    } else {
                        tvNoMoviesFound.visibility = View.GONE
                    }
                    if (editable.toString().isEmpty()) {
                        tvNoMoviesFound.visibility = View.GONE
                    }

                    moviesAdapter.filter.filter(editable.toString())
                    moviesAdapter.notifyDataSetChanged()
                }
            }
        })
    }


    /**
     * Set the LayoutManager to the RecyclerView
     */
    private fun initAdapter() {
        rvMoviesData.apply {
            val layoutManager = GridLayoutManager(requireContext(), GRID_SPAN_COUNT)
            rvMoviesData.layoutManager = layoutManager
            rvMoviesData.itemAnimator = DefaultItemAnimator()
        }

    }

    private fun initViewModel() {
        viewModel.moviesList.observe(this, Observer {
            moviesAdapter = MoviesAdapter(it as ArrayList<DataItem>, requireContext())
            rvMoviesData.adapter = moviesAdapter
        })
        viewModel.showLoading.observe(this, Observer {
            progress.visibility = if (it) View.VISIBLE else View.GONE
        })
        viewModel.showError.observe(this, Observer {
            mdToast =
                MDToast.makeText(requireContext(), it, MDToast.LENGTH_LONG, MDToast.TYPE_ERROR)
            mdToast.show()
        })

    }

}
