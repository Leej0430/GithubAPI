package com.example.githubapi.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapi.R
import com.example.githubapi.model.ListDataType
import com.example.githubapi.model.UserInfo
import com.example.githubapi.model.UserList
import com.squareup.picasso.Picasso

class UserAdapter(val dataSet: ListDataType):RecyclerView.Adapter<RecyclerView.ViewHolder>() {

  private enum class TYPE_VIEWS{
      UserType,RepoType
  }

    override fun getItemViewType(position: Int): Int {
        return when(dataSet){
           is ListDataType.USERTYPE ->TYPE_VIEWS.UserType.ordinal
            is ListDataType.REPOTYPE ->TYPE_VIEWS.RepoType.ordinal
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            TYPE_VIEWS.UserType.ordinal->{
                UserViewHolder(LayoutInflater.from(parent.context).inflate(
                        R.layout.user_detail,parent,false
                ))
            }
            TYPE_VIEWS.RepoType.ordinal->{
                RepoViewHolder(LayoutInflater.from(parent.context).inflate(
                        R.layout.repo_detail,parent,false
                ))
            }
            else->throw Exception("Undefined type")

        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is UserViewHolder ->{
                holder.onBind(
                        (dataSet as ListDataType.USERTYPE).data.items[position]
                )
            }
            is RepoViewHolder ->{
                holder.onBind(
                        (dataSet as ListDataType.REPOTYPE).data[position]
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return when(dataSet){
            is ListDataType.USERTYPE->dataSet.data.items.size
            is ListDataType.REPOTYPE->dataSet.data.size
            else->throw Exception("Undefined")
        }
    }



}





