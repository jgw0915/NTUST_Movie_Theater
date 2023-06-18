package com.example.ntustmovietheater.adpter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.ntustmovietheater.R
import com.example.ntustmovietheater.model.MovieTicket
import com.example.ntustmovietheater.ui.orderdTickets.OrderedTicketsFragment
import com.example.ntustmovietheater.ui.orderdTickets.OrderedTicketsFragmentDirections

class OrderedTicketAdapter (orderMovieList:MutableList<MovieTicket>,
                            fragment:OrderedTicketsFragment
) :RecyclerView.Adapter<OrderedTicketAdapter.orderedTicketViewHolder>(){

    private val f=fragment
    private val list=orderMovieList

    class orderedTicketViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image=view.findViewById<ImageView>(R.id.order_ticket_image)
        val title=view.findViewById<TextView>(R.id.order_ticket_title)
        val people=view.findViewById<TextView>(R.id.order_ticket_people)
        val moreInfo=view.findViewById<TextView>(R.id.order_ticket_moreInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): orderedTicketViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.order_ticket_item, parent, false)
        return orderedTicketViewHolder(layout)
    }

    override fun getItemCount(): Int =list.size

    override fun onBindViewHolder(holder: orderedTicketViewHolder, position: Int) {
        val item=list[position]
        val imageNumber=item.imageNumber
        holder.image.setImageResource(putImage(imageNumber))
        holder.title.text=item.title
        holder.people.text=item.people.toString()+"人"
        holder.moreInfo.setOnClickListener {
            val action=OrderedTicketsFragmentDirections.actionNavOrderedTicketsToTicketDetailFragment(item.ticket_id)
            action.setTicketId(item.ticket_id)
            NavHostFragment.findNavController(f).navigate(action)
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