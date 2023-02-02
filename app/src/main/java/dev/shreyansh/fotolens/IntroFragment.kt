package dev.shreyansh.fotolens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import dev.shreyansh.fotolens.databinding.FragmentIntroBinding

class IntroFragment : Fragment() {

    private lateinit var binding: FragmentIntroBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_intro, container, false)
        binding.getStartedButton.setOnClickListener {
            findNavController().navigate(R.id.action_introFragment_to_captureFragment)
        }
        return binding.root
    }


}