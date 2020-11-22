package com.example.githubapi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.githubapi.R
import com.example.githubapi.model.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_second.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SecondActivity : AppCompatActivity() {
    private var repoInfos:List<RepoInfo>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val tv_username:TextView = findViewById(R.id.tv_name)
        val tv_email:TextView = findViewById(R.id.tv_email)
        val tv_location:TextView = findViewById(R.id.tv_location)
        val tv_join_date:TextView = findViewById(R.id.tv_join_date)
        val tv_follower:TextView = findViewById(R.id.tv_follower)
        val tv_following:TextView = findViewById(R.id.tv_following)
        val tv_bio:TextView = findViewById(R.id.tv_bio)
        val img_avatar:ImageView = findViewById(R.id.img_pic)

        if(intent.hasExtra("name")){
        tv_username.text= intent.getStringExtra("name")
        }

        Network.initRetrofit().getDetail(tv_username.text.toString()).enqueue(object : Callback<UserDetail>{
            override fun onResponse(call: Call<UserDetail>, response: Response<UserDetail>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        tv_email.text = it.email
                        if(it.email==null) tv_email.text ="No Email"
                        tv_location.text = it.location
                        if(it.location==null) tv_location.text ="Location Not Defined"
                        tv_join_date.text = it.created_at
                        tv_follower.text = it.followers.toString()+" Followers"
                        tv_following.text = "Following "+it.following.toString()
                        tv_bio.text = it.bio
                        if(it.bio==null) tv_bio.text ="No Bio"
                        Picasso.get().load(it.avatar_url).into(img_avatar)

                    }
                } else {
                    Toast.makeText(this@SecondActivity,"Connection Error",Toast.LENGTH_SHORT)
                }
            }

            override fun onFailure(call: Call<UserDetail>, t: Throwable) {

            }
        })
        Network.initRetrofit().getRepo(tv_username.text.toString()).enqueue(object : Callback<List<RepoInfo>>{
            override fun onResponse(call: Call<List<RepoInfo>>, response: Response<List<RepoInfo>>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        repoInfos = it
                        setupRecyclerView()

                    }
                } else {
                    Toast.makeText(this@SecondActivity,"Connection Error",Toast.LENGTH_SHORT)
                }
            }

            override fun onFailure(call: Call<List<RepoInfo>>, t: Throwable) {
                TODO("Not yet implemented")
            }


        })


    }
    private fun setupRecyclerView(){
        rv_repo_info.layoutManager = GridLayoutManager(
                this,
                1
        )

        rv_repo_info.adapter = UserAdapter(ListDataType.REPOTYPE(repoInfos!!))
    }
}