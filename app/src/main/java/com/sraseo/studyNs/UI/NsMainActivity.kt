package com.sraseo.studyNs.UI

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sraseo.demoappusinghilt.databinding.ActivityMainNewBinding
import com.sraseo.studyNs.Adapter.NsAdapter
import com.sraseo.studyNs.Util.NsApiState
import com.sraseo.studyNs.ViewModel.NsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class NsMainActivity:AppCompatActivity() {

    lateinit var binding: ActivityMainNewBinding
    var nsAdapter: NsAdapter = NsAdapter(emptyList())
    val nsViewModel:NsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainNewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intRecyclerView()

        nsViewModel.getData()

        lifecycleScope.launchWhenStarted {
            nsViewModel.nsApiState.collect {
                when(it){
                    is NsApiState.Loading->{
                        binding.progressBar.visibility=View.VISIBLE
                    }
                    is NsApiState.Success ->{
                        binding.progressBar.visibility=View.GONE
                        binding.recyclerview.visibility= View.VISIBLE
                        nsAdapter.setData(it.data)
                        nsAdapter.notifyDataSetChanged()
                    }
                    is NsApiState.Failure->{
                        Toast.makeText(this@NsMainActivity, it.throwable.message, Toast.LENGTH_SHORT).show()
                    }

                    else -> {}
                }
            }
        }

    }

    private fun intRecyclerView() {
        binding.recyclerview.apply {
            setHasFixedSize(true)
            layoutManager=LinearLayoutManager(this@NsMainActivity)
            adapter=nsAdapter
        }
    }
}