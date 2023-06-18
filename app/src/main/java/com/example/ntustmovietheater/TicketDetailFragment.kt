package com.example.ntustmovietheater

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.ntustmovietheater.databinding.FragmentTicketDetailBinding
import com.example.ntustmovietheater.viewModel.MovieViewModel
import com.example.ntustmovietheater.viewModel.MovieViewModelFactory


class TicketDetailFragment : Fragment() {

    private var _binding: FragmentTicketDetailBinding? = null

    private val binding get() = _binding!!

    private val movieViewModel: MovieViewModel by activityViewModels {
        MovieViewModelFactory(
            (requireContext().applicationContext as MovieApplication).database
        )
    }

    private var position:Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            position=it.getInt("position")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTicketDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Log.d("tag","ONCREATEViewSuccess")
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("tag","ONViewCreatedSuccess")
        val movie=movieViewModel.order_ticket_List[position]
        binding.ticketDetailImage.setImageResource(putImage(movie.imageNumber))

        binding.ticketDetailTitle.text=movie.title
        binding.ticketDetailLocation.text="地址:"+movie.location
        binding.ticketDetailLocationName.text="地名:"+movie.locationName
        binding.ticketDetailStartTime.text="開始時間:"+movie.startTime
        binding.ticketDetailEndTime.text="結束時間:"+movie.endTime
        binding.ticketDetailOnsale.text="優惠價格:"+movie.onSale
        binding.ticketDetailPrtice.text="票價:"+movie.price
        binding.ticketDetailPeople.text=movie.people.toString()+"人"
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
            else->R.drawable.yimen
        }
    }


}