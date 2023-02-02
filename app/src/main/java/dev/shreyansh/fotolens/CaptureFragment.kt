package dev.shreyansh.fotolens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import dev.shreyansh.fotolens.databinding.FragmentCaptureBinding

class CaptureFragment : Fragment() {

    private lateinit var binding: FragmentCaptureBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_capture, container, false)
        return binding.root
    }


}