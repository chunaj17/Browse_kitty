package com.example.browsekittys.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.browsekittys.R
import com.example.browsekittys.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    private lateinit var binding: MainFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(this.layoutInflater)
        binding.nextBtn.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_choose)
        }
        return binding.root
    }

}