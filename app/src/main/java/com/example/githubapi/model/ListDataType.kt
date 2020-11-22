package com.example.githubapi.model

sealed class ListDataType{
    data class USERTYPE(val data :UserList):ListDataType()

    data class REPOTYPE(val data :List<RepoInfo>):ListDataType()
}