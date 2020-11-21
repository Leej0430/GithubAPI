package com.example.githubapi.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.core.view.get
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubapi.R
import com.example.githubapi.model.Network
import com.example.githubapi.model.UserList
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.user_recycler)
        var searchview:SearchView  = findViewById(R.id.sv_search)
        searchview.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                search(query.toString())
                searchview.clearFocus()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }
   private fun search(name:String) {
        Network.initRetrofit().getUserInfo(name).enqueue(object : Callback<UserList> {
            override fun onResponse(
                call: Call<UserList>,
                response: Response<UserList>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        recyclerView.layoutManager = GridLayoutManager(
                            this@MainActivity,
                            1
                        )
                        recyclerView.adapter = UserAdapter(it)

                    }
                } else {
                    Toast.makeText(this@MainActivity,"Error",Toast.LENGTH_SHORT)
                }
            }

            override fun onFailure(call: Call<UserList>, t: Throwable) {

            }


        })
    }

    }


