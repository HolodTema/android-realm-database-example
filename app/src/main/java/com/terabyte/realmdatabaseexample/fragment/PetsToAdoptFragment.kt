package com.terabyte.realmdatabaseexample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.terabyte.realmdatabaseexample.databinding.BottomSheetAddPetBinding
import com.terabyte.realmdatabaseexample.databinding.FragmentPetsToAdoptBinding
import com.terabyte.realmdatabaseexample.viewmodel.MainViewModel


class PetsToAdoptFragment : Fragment() {
    private lateinit var binding: FragmentPetsToAdoptBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class]
        binding = FragmentPetsToAdoptBinding.inflate(inflater)

        binding.buttonAddPetToAdopt.setOnClickListener {
            showBottomSheetAddPet()
        }

        return binding.root
    }

    private fun showBottomSheetAddPet() {
        val dialog = BottomSheetDialog(requireContext())
        val bindingBottomSheet = BottomSheetAddPetBinding.inflate(layoutInflater)

        bindingBottomSheet.buttonAddPet.setOnClickListener {
            // TODO: add pet to database
            dialog.dismiss()
        }

        dialog.setCancelable(true)
        dialog.setContentView(bindingBottomSheet.root)
        dialog.show()
    }
}