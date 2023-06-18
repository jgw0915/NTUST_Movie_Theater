package com.example.ntustmovietheater.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.ntustmovietheater.database.MovieRommDatabase
import com.example.ntustmovietheater.model.JoinTable
import com.example.ntustmovietheater.model.Movie
import com.example.ntustmovietheater.model.MovieTicket
import com.example.ntustmovietheater.model.ShowInfo
import com.example.ntustmovietheater.network.MovieNetwork
import com.example.ntustmovietheater.network.asDatabaseModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception

//db:MovieRommDatabase
class MovieViewModel(db:MovieRommDatabase):ViewModel() {

    //    private val repository = MovieRepository(get)
    val movieDao = db.movieDao()
    val showInfoDao = db.showInfoDao()
    val movie_showInfoDao = db.movie_showInfoDao()
    val movieTicketDao = db.movieTicketDao()

    private var _order_ticket_List:MutableList<MovieTicket> = mutableListOf()
    val order_ticket_List get()=_order_ticket_List

    private var _movie_List:MutableList<JoinTable> = getMovie()
    val movie_List get()=_movie_List


    fun cancelTicket(movie:MovieTicket){
        viewModelScope.launch {
            movieTicketDao.deleteTicket(movie.ticket_id)
            _order_ticket_List.remove(movie)
            delay(20)
        }

    }

    fun addOrderTicket(movie:MovieTicket){
        viewModelScope.launch {
            movieTicketDao.insert(movie)
            _order_ticket_List.add(movie)
            Log.d("TAG","insert movie:$movie")
        }
    }

    fun getOrderTicket()  {
        var movies: MutableList<MovieTicket> = mutableListOf()
        viewModelScope.launch {
            try{
                movies = movieTicketDao.getAllMovieTicket()
                Log.d("TAG", "getMovies: Success")
                for (i in movies){
                    Log.d("TAG","get order movie:$i")
                    _order_ticket_List.add(i)
                    Log.d("TAG","add success")
                }
            }catch (e:Exception){
                Log.d("TAG",e.toString())

            }
        }
    }


    /**
     * Event triggered for network error. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    /**
     * Event triggered for network error. Views should use this to get access
     * to the data.
     */
    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    /**
     * Flag to display the error message. This is private to avoid exposing a
     * way to set this value to observers.
     */
    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    /**
     * Flag to display the error message. Views should use this to get access
     * to the data.
     */
    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {

                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
                Log.d("TAG", "HIHIHI")

                val movie = MovieNetwork.movieService.getMovie("doFindTypeJ", "8")
                val movie_convert: Array<Pair<Movie, List<ShowInfo>>> = movie.asDatabaseModel()

                //m=>movie, s=>showInfo
                movie_convert.forEach { (m, s) ->
                    movieDao.insert(m)
                    showInfoDao.insertAll(*s.toTypedArray())
                }
                Log.d("TAG", "Done & " + movie.size.toString())
            } catch (networkError: IOException) {
                // Show a Toast error message and hide the progress bar.
//                if (movieList.value.isNullOrEmpty())
//                    _eventNetworkError.value = true
                Log.d("TAG", networkError.toString())
            }
        }
    }

    fun getMovie() :MutableList<JoinTable> {
        var join_movies:MutableList<JoinTable> = mutableListOf()
        viewModelScope.launch {
            try {
                val movies = movieDao.getAllMovie()
                for (i in movies){
                    val result: JoinTable = movie_showInfoDao.getMovie(i.uid)
                    join_movies.add(result)
                    Log.d("TAG", "getMovies: Success")
                    Log.d("TAG", result.showInfo.get(0).locationName.toString())
                }
            } catch (e: Exception) {
                Log.d("TAG", e.toString())
            }
        }
        return join_movies
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }


}

    /**
     * Factory for constructing DevByteViewModel with parameter
     */
    class MovieViewModelFactory(private val db: MovieRommDatabase) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MovieViewModel(db) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }