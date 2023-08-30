package com.mycompose.android.data.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDataResponse(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int?,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "emailId") val emailId: String?,
    @ColumnInfo(name = "gender") val gender: String?,
    @ColumnInfo(name = "status") val status: String?
)