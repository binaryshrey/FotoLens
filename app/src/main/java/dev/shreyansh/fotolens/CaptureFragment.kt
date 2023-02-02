package dev.shreyansh.fotolens

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.databinding.DataBindingUtil
import com.canhub.cropper.CropImageContract
import com.canhub.cropper.CropImageView
import com.canhub.cropper.options
import dev.shreyansh.fotolens.databinding.FragmentCaptureBinding

class CaptureFragment : Fragment() {

    private lateinit var binding: FragmentCaptureBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_capture, container, false)
        binding.CaptureButton.setOnClickListener {
            activityResultLauncher.launch(
                arrayOf(android.Manifest.permission.CAMERA,android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            )
        }

        return binding.root
    }

    private val activityResultLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions())
        { permissions ->
            // Handle Permission granted/rejected
            var allPermissionGranted : MutableList<Boolean> = mutableListOf()
            permissions.entries.forEach {
                if(!it.value){
                    Toast.makeText(context,"Enable Permission : ${it.key.toString()}",Toast.LENGTH_SHORT).show()
                }
                allPermissionGranted.add(it.value)
            }
            if(allPermissionGranted[0] && allPermissionGranted[1]){
                captureImage()
            }
        }

    private val cropImage = registerForActivityResult(CropImageContract()) { result ->
        if (result.isSuccessful) {
            val uriContent = result.uriContent
            val uriFilePath = result.getUriFilePath(requireActivity()) // optional usage
            binding.capturedImageView.setImageURI(uriContent)

        } else {
            Toast.makeText(context,"Error Occurred : ${result.error.toString()}",Toast.LENGTH_SHORT).show()
            Log.e("Capture Fragment","${result.error.toString()}")
        }
    }

    private fun captureImage() {
        cropImage.launch(
            options() {
                setGuidelines(CropImageView.Guidelines.ON)
                setOutputCompressFormat(Bitmap.CompressFormat.PNG)
            }
        )
    }
}