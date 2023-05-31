package com.example.UI.fragments.secondfragment

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
import com.example.data.SteamApiService
import com.example.data.SteamUser
import com.example.data.SteamUserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SecondFragment : Fragment() {
    private lateinit var steamIdEditText: EditText
    private lateinit var getPhotoButton: Button
    private lateinit var avatarImageView: ImageView
    private lateinit var viewModel: SecondViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_second, container, false)
        steamIdEditText = view.findViewById(R.id.steamIdEditText)
        getPhotoButton = view.findViewById(R.id.getPhotoButton)
        val buttonToName = view.findViewById<Button>(R.id.buttonToName)
        avatarImageView = view.findViewById(R.id.avatarImageView)

        viewModel = ViewModelProvider(this).get(SecondViewModel::class.java)

        getPhotoButton.setOnClickListener {
            val steamId = steamIdEditText.text.toString()
            viewModel.getUserInfo(steamId)
        }

        viewModel.avatarUrl.observe(viewLifecycleOwner, Observer { avatarUrl ->
            displayAvatar(avatarUrl)
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            displayError(error)
        })

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

    private fun displayError(error: String) {
        avatarImageView.setImageResource(R.drawable.default_avatar)
    }
}


