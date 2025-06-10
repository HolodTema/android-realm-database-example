package com.terabyte.realmdatabaseexample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.terabyte.realmdatabaseexample.databinding.BottomSheetAddOwnerBinding
import com.terabyte.realmdatabaseexample.databinding.FragmentOwnersBinding
import com.terabyte.realmdatabaseexample.realm.OwnerModel
import com.terabyte.realmdatabaseexample.realm.RealmHelper
import com.terabyte.realmdatabaseexample.ui.OwnersAdapter
import com.terabyte.realmdatabaseexample.viewmodel.MainViewModel


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

        viewModel.liveDataOwners.observe(requireActivity()) {
            adapter.updateOwnersList(it)
            adapter.notifyDataSetChanged()

            if (it.isEmpty()) {
                binding.textCaptionEmptyRecyclerView.visibility = View.VISIBLE
                binding.recyclerOwners.visibility = View.INVISIBLE
            }
            else {
                binding.textCaptionEmptyRecyclerView.visibility = View.INVISIBLE
                binding.recyclerOwners.visibility = View.VISIBLE
            }
        }

        binding.buttonAddOwner.setOnClickListener {
            showBottomSheetAddOwner()
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
                viewModel.updateOwnersList()
                dialog.dismiss()
            }
        }

        dialog.setCancelable(true)
        dialog.setContentView(bindingBottomSheet.root)
        dialog.show()
    }

    private fun configureRecyclerView() {
        adapter = OwnersAdapter(viewModel.liveDataOwners.value!!, viewModel, layoutInflater)
        binding.recyclerOwners.adapter = adapter
    }
}