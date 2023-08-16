package com.star.android13

import android.Manifest.permission
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.star.android13.databinding.FragmentMediaBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MediaFragment : Fragment() {

    private var _binding: FragmentMediaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private val launcherPhoto = registerForActivityResult(ActivityResultContracts.RequestPermission()) {

    }
    private val launcherVideo = registerForActivityResult(ActivityResultContracts.RequestPermission()) {

    }
    private val launcherAudio = registerForActivityResult(ActivityResultContracts.RequestPermission()) {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMediaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonPhoto.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                launcherPhoto.launch(permission.READ_MEDIA_IMAGES)
            } else {
                launcherPhoto.launch(permission.READ_EXTERNAL_STORAGE)
            }
        }
        binding.buttonVideo.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                launcherVideo.launch(permission.READ_MEDIA_VIDEO)
            } else {
                launcherVideo.launch(permission.READ_EXTERNAL_STORAGE)
            }
        }
        binding.buttonAudio.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                launcherAudio.launch(permission.READ_MEDIA_AUDIO)
            } else {
                launcherAudio.launch(permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}