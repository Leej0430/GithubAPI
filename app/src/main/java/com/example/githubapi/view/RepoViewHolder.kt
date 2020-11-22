package com.example.githubapi.view

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapi.R
import com.example.githubapi.model.RepoInfo
import com.example.githubapi.model.UserInfo
import com.squareup.picasso.Picasso

class RepoViewHolder(val repoView: View)
    : RecyclerView.ViewHolder(repoView) {
    private val repoName: TextView
            = repoView.findViewById(R.id.tv_repo_name)
    private val numStars: TextView
            =repoView.findViewById(R.id.tv_num_star)
    private val numFolks: TextView
            = repoView.findViewById(R.id.tv_num_folk)

    fun onBind(dataItem: RepoInfo) {
        repoName.text = dataItem.name
        numStars.text = "Folks"+dataItem.stargazers_count.toString()
        numFolks.text = "Stars"+dataItem.forks.toString()
    }
}