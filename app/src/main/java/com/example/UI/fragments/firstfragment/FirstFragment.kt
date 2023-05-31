package com.example.UI.fragments.firstfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.steamuser.R
import com.example.data.SteamUser

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

        viewModel = ViewModelProvider(this).get(FirstViewModel::class.java)

        getNameButton.setOnClickListener {
            val steamId = steamIdEditText.text.toString()
            viewModel.getUserInfo(steamId)
        }

        viewModel.userInfo.observe(viewLifecycleOwner, Observer { userInfo ->
            displayUserInfo(userInfo)
        })

        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            displayError(error)
        })

        buttonToPhoto.setOnClickListener {
            findNavController().navigate(R.id.firstToSecond)
        }

        return view
    }

    private fun displayUserInfo(user: SteamUser) {
        userNameTextView.text = user.personaname
    }

    private fun displayError(error: String) {
        userNameTextView.text = error
    }
}

