package com.example.presentation.fragments.secondfragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.steamuser.R

class SecondFragment : Fragment() {
    private lateinit var steamIdEditText: EditText
    private lateinit var getPhotoButton: Button
    private lateinit var avatarImageView: ImageView
    private lateinit var viewModel: SecondViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        steamIdEditText = view.findViewById(R.id.steamIdEditText)
        getPhotoButton = view.findViewById(R.id.getPhotoButton)
        val buttonToName = view.findViewById<Button>(R.id.buttonToName)
        avatarImageView = view.findViewById(R.id.avatarImageView)

        viewModel = ViewModelProvider(this)[SecondViewModel::class.java]

        getPhotoButton.setOnClickListener {
            val steamId = steamIdEditText.text.toString()
            viewModel.getUserAvatar(steamId)
        }

        viewModel.avatarUrl.observe(viewLifecycleOwner) { avatarUrl ->
            displayAvatar(avatarUrl)
        }

        buttonToName.setOnClickListener {
            findNavController().navigate(R.id.secondToFirst)
        }

        return view
    }

    private fun displayAvatar(avatarUrl: String) {
        Glide.with(this)
            .load(avatarUrl)
            .into(avatarImageView)
    }

}


