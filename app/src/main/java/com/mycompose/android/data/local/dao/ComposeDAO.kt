package com.mycompose.android.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.mycompose.android.data.model.UserInfoModel

@Dao
interface ComposeDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUserInfo(userInfo : UserInfoModel)



}