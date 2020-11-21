package com.example.githubapi.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapi.R
import com.example.githubapi.model.UserInfo
import com.example.githubapi.model.UserList
import com.squareup.picasso.Picasso

class UserAdapter(val dataSet: UserList): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(val userView:View)
        :RecyclerView.ViewHolder(userView) {
        private val userName: TextView
                = userView.findViewById(R.id.tv_username)
        private val image:ImageView
        =userView.findViewById(R.id.img_user)

        fun onBind(dataItem: UserInfo) {
            Picasso.get().load(dataItem.avatar_url).into(image)
            userName.text = dataItem.login

            userView.setOnClickListener {
                val intent = Intent(userView.context,SecondActivity::class.java)
                intent.putExtra("name",userName.text.toString())
                userView.context.startActivity(intent)

            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.user_detail,
                        parent,
                        false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
    holder.onBind(dataSet.items[position])

    }



    override fun getItemCount(): Int {
        return dataSet.items.size
    }

}