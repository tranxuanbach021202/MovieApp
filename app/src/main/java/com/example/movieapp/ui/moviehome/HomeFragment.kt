package com.example.movieapp.ui.moviehome

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentHomeBinding
import com.example.movieapp.domain.model.Movie
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


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
        MoviePopularAdapter {
            val directions = HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(it.movie.id)
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
        initView()
        initObservers()
        controller = findNavController()
    }

    private fun initView() {

        // setup adapter movie now showing
        binding.rvMovieNow.layoutManager =  LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvMovieNow.adapter = movieNowShowingAdapter


        // setup adapter movie popular
        binding.rvMoviePopular.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvMoviePopular.adapter = moviePopularAdapter

    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getMovieNowShowing().observe(viewLifecycleOwner, {
                movieNowShowingAdapter.submitData(lifecycle, it)
            })


            viewModel.getMoviePopular().observe(viewLifecycleOwner, {
                moviePopularAdapter.submitData(lifecycle, it)
            })
        }
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