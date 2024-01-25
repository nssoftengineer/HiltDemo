package com.sraseo.studyNs.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.sraseo.demoappusinghilt.databinding.EachRowBinding

import com.sraseo.studyNs.Model.NsData

class NsAdapter(private var postList: List<NsData>)
    : RecyclerView.Adapter<NsAdapter.PostViewHolder>() {
    private lateinit var binding: EachRowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        binding = EachRowBinding.inflate(LayoutInflater.from(parent.context),
        parent,false)
        return PostViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        binding.tasks.text=postList[position].body
    }

    override fun getItemCount(): Int = postList.size

    class PostViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

    }

    fun setData(postList: List<NsData>)
    {
        this.postList=postList
        notifyDataSetChanged()
    }

}