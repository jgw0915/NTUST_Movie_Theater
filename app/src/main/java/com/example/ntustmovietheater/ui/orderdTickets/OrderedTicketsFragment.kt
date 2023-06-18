package com.example.ntustmovietheater.ui.orderdTickets

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.ntustmovietheater.MovieApplication
import com.example.ntustmovietheater.R
import com.example.ntustmovietheater.adpter.OrderedTicketAdapter
import com.example.ntustmovietheater.databinding.FragmentOrderedTicketsBinding
import com.example.ntustmovietheater.databinding.MovieItemBinding
import com.example.ntustmovietheater.viewModel.MovieViewModel
import com.example.ntustmovietheater.viewModel.MovieViewModelFactory

class OrderedTicketsFragment : Fragment() {

    private var _binding: FragmentOrderedTicketsBinding? = null

    private lateinit var recyclerView: RecyclerView

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

        _binding = FragmentOrderedTicketsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        for (i in movieViewModel.order_ticket_List){
            Log.d("TAG","movie:$i")
        }



        recyclerView=binding.orderedTicketRecyclerview
        recyclerView.adapter=OrderedTicketAdapter(movieViewModel.order_ticket_List,this)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun putImage(imageNumber:Int?):Int{
        return when(imageNumber){
            1-> R.drawable.aquaman
            2-> R.drawable.avengers
            3-> R.drawable.cartoon
            4-> R.drawable.harryporter
            5-> R.drawable.love
            6-> R.drawable.madagasga
            7-> R.drawable.withgod
            else-> R.drawable.yimen
        }
    }
}