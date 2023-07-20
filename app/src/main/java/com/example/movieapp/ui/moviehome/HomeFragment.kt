package com.example.movieapp.ui.moviehome

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.data.models.Movie
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {


    val viewModel : MovieHomeViewModel by  viewModels()

    private lateinit var controller: NavController

    private val navController : NavController by lazy { findNavController()  }
    private val movieNowShowingAdapter : MovieNowShowingAdapter by lazy {
        MovieNowShowingAdapter{ movie ->
            val directions = HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movie.id)
            navController.navigate(directions)
        }
    }
    private val moviePopularAdapter : MoviePopularAdapter by lazy {
        MoviePopularAdapter{movie ->
            val directions = HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movie.id)
            navController.navigate(directions)
        }
    }
    lateinit var binding: FragmentHomeBinding


    private val onClickItem: (Movie) -> Unit = {}



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller = findNavController()
        setupMovieNowShowing()
        setupMoviePopular()
        setupMovieSearch()


    }

    // Call dữ liệu về cho phần Movie Nowshowing
    private fun setupMovieNowShowing() {




        // setup cho list movie nowshowing
        binding.rvMovieNow.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvMovieNow.adapter = movieNowShowingAdapter

        // call dữ liệu
        viewModel.moviesNowShowing.observe(viewLifecycleOwner) {
            movieNowShowingAdapter.submitData(lifecycle, it)
        }
    }


    // Call dữ liệu về cho Movie Popular
    private fun setupMoviePopular() {
        // setup list popular
        binding.rvMoviePopular.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvMoviePopular.adapter = moviePopularAdapter


        // call dữ liệu
        viewModel.moviesPopular.observe(viewLifecycleOwner) {
            Log.d("DatarPop", it.toString())
            moviePopularAdapter.submitData(lifecycle, it)
        }

        // call thể loại về để hiện thị cho các item của popular
        viewModel.genresLiveData.observe(viewLifecycleOwner) {
            Log.d("Genres", it.toString())
        }

        viewModel.getGenresMovie()
    }


    private fun setupMovieSearch() {
        binding.edSearchMovie.setOnFocusChangeListener{ view, hasFocus ->
            if (hasFocus) {
                binding.movieList.visibility = View.GONE
            } else {
                binding.movieList.visibility = View.VISIBLE
            }
        }
    }
}