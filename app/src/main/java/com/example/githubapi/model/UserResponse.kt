package com.example.githubapi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserList(
    val items :List<UserInfo>
):Parcelable

@Parcelize
data class UserInfo(
    val login :String,
        val avatar_url:String
):Parcelable

@Parcelize
data class UserDetail(
    val email :String?,
    val location:String?,
    val avatar_url: String?,
    val created_at:String?,
    val followers:Int?,
    val following:Int?,
    val bio:String?
):Parcelable