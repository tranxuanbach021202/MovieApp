package com.example.movieapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.model.Movie
import com.example.movieapp.ui.adapter.MovieNowShowingAdapter
import com.example.movieapp.ui.adapter.MoviePopularAdapter
import com.example.movieapp.ui.viewmodel.MovieViewModel
import com.example.movieapp.ultis.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {


    val viewModel : MovieViewModel by  viewModels()


    private val movieNowShowingAdapter = MovieNowShowingAdapter()


    private val moviePopularAdapter = MoviePopularAdapter()


    lateinit var binding: FragmentHomeBinding




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.rvMovieNow.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvMovieNow.adapter = movieNowShowingAdapter


        binding.rvMoviePopular.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvMoviePopular.adapter = moviePopularAdapter

        refreshData()
    }

    private fun refreshData() {
        Log.d("RefrechData", "refrech")
        viewModel.moviesNowShowing.observe(viewLifecycleOwner) {
            Log.d("Datarapp", it.toString())
            movieNowShowingAdapter.submitData(lifecycle, it)
        }

        viewModel.moviesPopular.observe(viewLifecycleOwner) {
            Log.d("DatarPop", it.toString())
            moviePopularAdapter.submitData(lifecycle, it)
        }
        viewModel.genresLiveData.observe(viewLifecycleOwner) {
            Log.d("Genres", it.toString())
        }

        viewModel.getGenresMovie()
    }
}