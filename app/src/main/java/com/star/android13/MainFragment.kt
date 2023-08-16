package com.star.android13

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.star.android13.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val launcher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        if (it) {
            postNotification()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button1.setOnClickListener {
            findNavController().navigate(R.id.action_to_MediaFragment)
        }
        binding.button2.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                launcher.launch(Manifest.permission.POST_NOTIFICATIONS)
            } else {
                postNotification()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun postNotification() {
        context?.let {
            val notificationManager: NotificationManager =
                it.getSystemService(Service.NOTIFICATION_SERVICE) as NotificationManager

            val notification = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel("channel_id", "channel_name", NotificationManager.IMPORTANCE_LOW)
                notificationManager.createNotificationChannel(channel)
                NotificationCompat.Builder(
                    it,
                    channel.id
                )
            } else {
                NotificationCompat.Builder(it)
            }
            notification.setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("通知标题")
                .setContentText("通知文本")

            notificationManager.notify(2, notification.build())


        }

    }
}