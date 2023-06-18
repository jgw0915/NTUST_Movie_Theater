package com.example.ntustmovietheater.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.ntustmovietheater.R
import com.example.ntustmovietheater.model.JoinTable
import com.example.ntustmovietheater.model.Movie
import com.example.ntustmovietheater.network.NetWorkMovie
import com.example.ntustmovietheater.ui.movieList.MovieListFragment
import com.example.ntustmovietheater.ui.movieList.MovieListFragmentDirections

class MovieAdapter (
    netWorkMovie:List<JoinTable>,
    fragment:MovieListFragment
) :RecyclerView.Adapter<MovieAdapter.movieViewHolder>(){

    private val f=fragment
    private val list=netWorkMovie
    class movieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image=view.findViewById<ImageView>(R.id.movie_image)
        val title=view.findViewById<TextView>(R.id.movie_title)
        val order=view.findViewById<TextView>(R.id.order_movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): movieViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.movie_item, parent, false)
        return movieViewHolder(layout)
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: movieViewHolder, position: Int) {
        val item=list[position]
//        holder.image.setImageResource(putImage(item.imageNumber))
        holder.title.text=item.movie.title
        val imageNumber=(position%8)+1
        holder.image.setImageResource(putImage(imageNumber ))
        holder.order.setOnClickListener {
            val action= MovieListFragmentDirections.actionNavMovieListToOrderFragment(item.movie.uid,imageNumber)
            action.setUid(item.movie.uid)
            action.setImageNumber(imageNumber)
            findNavController(f).navigate(action)
        }
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