package com.terabyte.realmdatabaseexample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.terabyte.realmdatabaseexample.databinding.RecyclerItemOwnerBinding
import com.terabyte.realmdatabaseexample.realm.OwnerModel

class OwnersAdapter(var owners: List<OwnerModel>, private val inflater: LayoutInflater): RecyclerView.Adapter<OwnersAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(RecyclerItemOwnerBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val owner = owners[position]
        holder.binding.textOwnerName.text = owner.name
//        holder.binding.imageOwner.drawable
    }

    override fun getItemCount() = owners.size

    class Holder(val binding: RecyclerItemOwnerBinding): RecyclerView.ViewHolder(binding.root) {

    }
}