package com.example.ntustmovietheater.ui.movieList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.ntustmovietheater.MovieApplication
import com.example.ntustmovietheater.adpter.MovieAdapter
import com.example.ntustmovietheater.databinding.FragmentMovieListBinding
import com.example.ntustmovietheater.viewModel.MovieViewModel
import com.example.ntustmovietheater.viewModel.MovieViewModelFactory
import com.example.ntustmovietheater.network.NetWorkMovie

class MovieListFragment : Fragment() {

    private var _binding: FragmentMovieListBinding? = null
    private lateinit var recyclerView:RecyclerView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val movieViewModel: MovieViewModel by activityViewModels {
        MovieViewModelFactory(
            (requireContext().applicationContext as MovieApplication).database
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieViewModel.refreshDataFromRepository()

        recyclerView=binding.movieRecyclerView
        recyclerView.adapter=MovieAdapter(movieViewModel.movie_List,this)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}