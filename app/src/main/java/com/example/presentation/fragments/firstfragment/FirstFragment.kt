package com.example.presentation.fragments.firstfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.data.SteamUserResponse
import com.example.steamuser.R

class FirstFragment : Fragment() {
    private lateinit var steamIdEditText: EditText
    private lateinit var getNameButton: Button
    private lateinit var userNameTextView: TextView
    private lateinit var viewModel: FirstViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        steamIdEditText = view.findViewById(R.id.steamIdEditText)
        getNameButton = view.findViewById(R.id.getNameButton)
        val buttonToPhoto = view.findViewById<Button>(R.id.buttonToPhoto)
        userNameTextView = view.findViewById(R.id.userNameTextView)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        viewModel = ViewModelProvider(this)[FirstViewModel::class.java]

        getNameButton.setOnClickListener {
            val steamId = steamIdEditText.text.toString()
            viewModel.getUsersInfo(steamId)
        }

        viewModel.userInfo.observe(viewLifecycleOwner) { userInfo ->
            displayUserInfo(userInfo)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) {
            progressBar.isVisible = it
        }

        buttonToPhoto.setOnClickListener {
            findNavController().navigate(R.id.firstToSecond)
        }
        return view
    }

    private fun displayUserInfo(user: SteamUserResponse) {
        userNameTextView.text = user.response.players[0].personaname
    }

}

