package com.sraseo.study

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager

import com.sraseo.demoappusinghilt.databinding.ActivityMainNewBinding
import com.sraseo.study.module.PostNs
import com.sraseo.study.util.StateNs
import com.sraseo.study.viewmodel.MainViewModelNs
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivityNs : AppCompatActivity(){
    lateinit var binding: ActivityMainNewBinding
    var postAdapter:  PostAdapter = PostAdapter(emptyList())
    private val mainViewModel: MainViewModelNs by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainNewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRecyclerView()
        mainViewModel.getData()
        lifecycleScope.launchWhenStarted {
            mainViewModel.apiState.collect {
                when(it){
                    is StateNs.Loading->{

                    }
                    is StateNs.Success->{
                        postAdapter.setData(it.data)
                        binding.recyclerview.visibility=View.VISIBLE
                        postAdapter.notifyDataSetChanged()
                    }
                    is StateNs.Failure->{
                        Toast.makeText(this@MainActivityNs, it.msg.message, Toast.LENGTH_SHORT).show()
                    }
                    is StateNs.Empty->{

                    }
                }
            }
        }

    }

    private fun setRecyclerView() {
        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(this@MainActivityNs)
            adapter=postAdapter
        }
    }
}