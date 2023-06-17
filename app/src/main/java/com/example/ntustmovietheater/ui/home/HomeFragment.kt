package com.example.ntustmovietheater.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.ntustmovietheater.MovieApplication
import com.example.ntustmovietheater.databinding.FragmentHomeBinding
import com.example.ntustmovietheater.viewModel.MovieViewModel
import com.example.ntustmovietheater.viewModel.MovieViewModelFactory

//import com.example.ntustmovietheater.viewModel.MovieViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!



    private val homeViewModel:HomeViewModel by viewModels()
    private val movieViewModel: MovieViewModel by activityViewModels {
        MovieViewModelFactory(
            (requireContext().applicationContext  as MovieApplication).database
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView: TextView = binding.homeTitle
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        movieViewModel.getMovie()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}