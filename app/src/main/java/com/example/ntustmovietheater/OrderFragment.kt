package com.example.ntustmovietheater

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import com.example.ntustmovietheater.databinding.FragmentOrderBinding
import com.example.ntustmovietheater.model.JoinTable
import com.example.ntustmovietheater.model.MovieTicket
import com.example.ntustmovietheater.viewModel.MovieViewModel
import com.example.ntustmovietheater.viewModel.MovieViewModelFactory

class OrderFragment : Fragment() {

    init {
        Log.d("tag","initSuccess")
    }

    private var uid:String=""
    private var imageNumber:Int=0

    private var currentPeople:Int=1
    private val MINIMUM_PEOPLE:Int=1
    private val MAXIMUM_PEOPLE:Int=4

    private var _binding: FragmentOrderBinding? = null

    private val binding get() = _binding!!

    private val movieViewModel: MovieViewModel by activityViewModels {
        MovieViewModelFactory(
            (requireContext().applicationContext as MovieApplication).database
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            uid=it.getString("uid").toString()
            imageNumber=it.getInt("imageNumber")
        }
        Log.d("tag","ONCREATESuccess")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val root: View = binding.root

        Log.d("tag","ONCREATEViewSuccess")
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("tag","ONViewCreatedSuccess")
        binding.orderImage.setImageResource(putImage(imageNumber))
        Log.d("tag","ImageSetSuccess")
        val movie=findMovie()
        Log.d("tag","findMovieSuccess")

        binding.orderTitle.text=movie.movie.title
        binding.orderLocation.text="地址:"+movie.showInfo.get(0).location
        binding.orderLocationName.text="地名:"+movie.showInfo.get(0).locationName
        binding.orderStartTime.text="開始時間:"+movie.showInfo.get(0).startTime
        binding.orderEndTime.text="結束時間:"+movie.showInfo.get(0).endTime
        binding.orderOnsale.text="優惠價格:"+movie.showInfo.get(0).onSale
        binding.orderPrtice.text="票價:"+movie.showInfo.get(0).price
        binding.minusButtom.setOnClickListener{
            decreasePeople()
        }
        binding.plusButtom.setOnClickListener{
            increasePeople()
        }
        binding.submitButtom.setOnClickListener{
            movieViewModel.addOrderTicket(MovieTicket(movie.movie.uid,movie.movie.title,movie.showInfo.get(0).startTime,movie.showInfo.get(0).locationName,movie.showInfo.get(0).location,movie.showInfo.get(0).endTime,movie.showInfo.get(0).onSale,movie.showInfo.get(0).price,imageNumber,currentPeople))
            for (i in movieViewModel.order_ticket_List){
                Log.d("TAG","movie:$i")
            }
            val action= OrderFragmentDirections.actionOrderFragmentToNavMovieList()
            NavHostFragment.findNavController(this).navigate(action)
        }
        Log.d("tag","ONViewCreatedSuccess")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun decreasePeople(){
        if (currentPeople>MINIMUM_PEOPLE){
            currentPeople--
            binding.orderPeople.text=currentPeople.toString()+"人"
        }else{
            Toast.makeText(requireContext(),"已達最小人數",Toast.LENGTH_SHORT).show()
        }
    }

    fun increasePeople(){
        if (currentPeople<MAXIMUM_PEOPLE){
            currentPeople++
            binding.orderPeople.text=currentPeople.toString()+"人"
        }else{
            Toast.makeText(requireContext(),"已達最大人數",Toast.LENGTH_SHORT).show()
        }
    }

    fun findMovie():JoinTable{
        val movie=movieViewModel.movie_List
        var wrong_movie:JoinTable=movieViewModel.movie_List.get(2)
        for (i in movie){
            if (i.movie.uid.equals(uid)){
                return i
            }
        }
        return wrong_movie
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