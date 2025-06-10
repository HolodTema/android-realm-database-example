package com.terabyte.realmdatabaseexample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.terabyte.realmdatabaseexample.databinding.BottomSheetAddOwnerBinding
import com.terabyte.realmdatabaseexample.databinding.BottomSheetAddPetBinding
import com.terabyte.realmdatabaseexample.databinding.FragmentOwnersBinding
import com.terabyte.realmdatabaseexample.realm.OwnerModel
import com.terabyte.realmdatabaseexample.realm.RealmHelper
import com.terabyte.realmdatabaseexample.ui.OwnersAdapter
import com.terabyte.realmdatabaseexample.viewmodel.MainViewModel
import io.realm.Realm
import io.realm.RealmChangeListener
import java.security.acl.Owner


class OwnersFragment : Fragment() {
    private lateinit var binding: FragmentOwnersBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: OwnersAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class]
        binding = FragmentOwnersBinding.inflate(inflater)

        configureRecyclerView()

        binding.buttonAddOwner.setOnClickListener {
            showBottomSheetAddOwner()
        }

        RealmHelper.setChangeListenerForAll(OwnerModel::class.java) {
            adapter.updateOwnersList(it)
            adapter.notifyDataSetChanged()
        }
        return binding.root
    }

    private fun showBottomSheetAddOwner() {
        val dialog = BottomSheetDialog(requireContext())
        val bindingBottomSheet = BottomSheetAddOwnerBinding.inflate(layoutInflater)

        bindingBottomSheet.buttonAddOwner.setOnClickListener {

            val ownerModel = OwnerModel()
            // TODO: make owner type available
            ownerModel.name = bindingBottomSheet.editOwnerName.text.toString()
            ownerModel.type = 1

            RealmHelper.createRealmObject(ownerModel) {
                dialog.dismiss()
            }
        }

        dialog.setCancelable(true)
        dialog.setContentView(bindingBottomSheet.root)
        dialog.show()
    }

    private fun configureRecyclerView() {
        adapter = OwnersAdapter(listOf(), viewModel, layoutInflater)
        binding.recyclerOwners.adapter = adapter

        RealmHelper.getAll(OwnerModel::class.java) {
            adapter.updateOwnersList(it)
        }
    }
}