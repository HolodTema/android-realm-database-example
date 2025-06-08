package com.terabyte.realmdatabaseexample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.terabyte.realmdatabaseexample.databinding.BottomSheetAddOwnerBinding
import com.terabyte.realmdatabaseexample.databinding.BottomSheetAddPetBinding
import com.terabyte.realmdatabaseexample.databinding.FragmentOwnersBinding
import com.terabyte.realmdatabaseexample.viewmodel.MainViewModel


class OwnersFragment : Fragment() {
    private lateinit var binding: FragmentOwnersBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class]
        binding = FragmentOwnersBinding.inflate(inflater)

        binding.buttonAddOwner.setOnClickListener {
            showBottomSheetAddOwner()
        }
        return binding.root
    }

    private fun showBottomSheetAddOwner() {
        val dialog = BottomSheetDialog(requireContext())
        val bindingBottomSheet = BottomSheetAddOwnerBinding.inflate(layoutInflater)

        bindingBottomSheet.buttonAddOwner.setOnClickListener {
            // TODO: add owner to database
            dialog.dismiss()
        }

        dialog.setCancelable(true)
        dialog.setContentView(bindingBottomSheet.root)
        dialog.show()
    }
}