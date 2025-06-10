package com.terabyte.realmdatabaseexample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.terabyte.realmdatabaseexample.databinding.RecyclerItemOwnerBinding
import com.terabyte.realmdatabaseexample.realm.OwnerModel
import com.terabyte.realmdatabaseexample.realm.RealmHelper
import com.terabyte.realmdatabaseexample.viewmodel.MainViewModel

class OwnersAdapter(private var owners: List<OwnerModel>, private val viewModel: MainViewModel, private val inflater: LayoutInflater): RecyclerView.Adapter<OwnersAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(RecyclerItemOwnerBinding.inflate(inflater))
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val owner = owners[position]
        holder.binding.textOwnerName.text = owner.name
        holder.binding.imageDeleteOwner.setOnClickListener {
            if (owner.isValid) {
                RealmHelper.deleteRealmObject(owner.id, OwnerModel::class.java) {
                    viewModel.updateOwnersList()
                }
            }
        }
    }

    override fun getItemCount() = owners.size

    fun updateOwnersList(owners: List<OwnerModel>) {
        this.owners = owners
    }

    class Holder(val binding: RecyclerItemOwnerBinding): RecyclerView.ViewHolder(binding.root)
}