package com.example.githubapi.view

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapi.R
import com.example.githubapi.model.UserInfo
import com.squareup.picasso.Picasso

class UserViewHolder(val userView: View)
    : RecyclerView.ViewHolder(userView) {
    private val userName: TextView
            = userView.findViewById(R.id.tv_username)
    private val image: ImageView
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