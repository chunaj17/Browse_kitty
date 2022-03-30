package com.example.browsekittys.ui.pets

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.browsekittys.R
import com.example.browsekittys.SharedViewModel
import com.example.browsekittys.databinding.DialogeImageBinding
import com.example.browsekittys.databinding.PetsFragmentBinding
import com.example.browsekittys.network.dataClass.DataResultItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class Pets : Fragment(), PetsListAdapter.Interaction {
    //    private val args: PetsArgs by navArgs()
    //    private lateinit var viewModelFactory: PetsViewModelFactory
    private lateinit var viewModel: PetsViewModel
    private var _binding: PetsFragmentBinding? = null

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!
    private lateinit var petsListAdapter: PetsListAdapter
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private var limit: Int? = 0
    private var categoryId: Int? = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        viewModelFactory = PetsViewModelFactory(args)
        _binding = PetsFragmentBinding.inflate(layoutInflater)
        limit = sharedViewModel.limit.value
        categoryId = sharedViewModel.categoryId.value
        viewModel = ViewModelProvider(this/* viewModelFactory*/)[PetsViewModel::class.java]
        binding.progressBar.visibility = View.VISIBLE
        binding.loadingText.visibility = View.VISIBLE

        initRecyclerView()
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.getImages(limit, categoryId)
        }
        viewModel.getResult().observe(viewLifecycleOwner) {
            if (it == null) {
                println(null)
            } else {
                binding.catsImageList.visibility = View.VISIBLE
                binding.progressBar.visibility = View.GONE
                binding.loadingText.visibility = View.GONE
                petsListAdapter.submitList(it)
                binding.catsImageList.scrollState
            }
        }
        return binding.root
    }
    private fun initRecyclerView() {
        binding.catsImageList.apply {
            layoutManager = LinearLayoutManager(activity)
            petsListAdapter = PetsListAdapter(this@Pets)
            adapter = petsListAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemSelected(position: Int, item: DataResultItem) {
        Glide.with(this)
            .asBitmap()
            .load(item.url)
            .fitCenter()
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    val size = 50
                    val dialog = MaterialDialog(context!!)
                        .customView(R.layout.dialoge_image)
                    dialog.cornerRadius(size.toFloat())
                    dialog.findViewById<ImageView>(R.id.dialog_cat_img).setImageBitmap(resource)
                    dialog.show()
                    dialog.findViewById<ImageView>(R.id.dialog_cat_img).setOnClickListener {
                        println(item.url)
                    }
//                    val imageView: ImageView = ImageView(activity)
//                    imageView.setImageBitmap(resource)
//                    AlertDialog.Builder(activity)
//                        .setView(imageView)
//                        .create()
//                        .show()
//                    dialogLayout.setConte
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                    // this is called when imageView is cleared on lifecycle call or for
                    // some other reason.
                    // if you are referencing the bitmap somewhere else too other than this imageView
                    // clear it here as you can no longer have the bitmap
                }
            })

    }

}