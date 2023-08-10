package com.mycompose.android.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mycompose.android.data.model.UserInfoModel.Companion.USER_TABLE_NAME


@Entity(tableName = USER_TABLE_NAME)
class UserInfoModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val authorName: String,
    val description: String
) {
    companion object {
        const val USER_TABLE_NAME = "UserInfo"
    }
}