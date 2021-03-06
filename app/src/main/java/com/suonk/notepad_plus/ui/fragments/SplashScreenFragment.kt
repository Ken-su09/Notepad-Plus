package com.suonk.notepad_plus.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.suonk.notepad_plus.R
import com.suonk.notepad_plus.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {

    private var binding: FragmentSplashScreenBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        initializeUI()
        return binding!!.root
    }

    private fun initializeUI() {
        val animation = AnimationUtils.loadAnimation(context, R.anim.suddenly_appear)
        binding!!.appLogo.startAnimation(animation)
        binding!!.appName.startAnimation(animation)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}