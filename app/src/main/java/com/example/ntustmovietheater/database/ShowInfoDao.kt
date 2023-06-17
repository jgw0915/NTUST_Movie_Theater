package com.example.ntustmovietheater.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.ntustmovietheater.model.ShowInfo

@Dao()
interface ShowInfoDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(vararg showInfos: ShowInfo)

}