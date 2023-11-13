package com.seoyoon.safeproject

import android.content.Intent
import android.location.Location
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.MapFragment
import com.naver.maps.map.NaverMap
import com.naver.maps.map.NaverMapOptions
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.MarkerIcons
import com.seoyoon.safeproject.databinding.ActivityMainBinding

class MainActivity : FragmentActivity(), OnMapReadyCallback {

    lateinit var binding: ActivityMainBinding
    private var myLocation: LatLng? = LatLng(37.5667, 126.9783)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fm = supportFragmentManager
        val mapFragment = fm.findFragmentById(R.id.map_fragment) as MapFragment?
            ?: MapFragment.newInstance().also {
                fm.beginTransaction().add(R.id.map_fragment, it).commit()
            }
        mapFragment.getMapAsync(this)

        binding.userButton.setOnClickListener {
            val intent = Intent(this, MyPageActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.settingButton.setOnClickListener {
            val intent = Intent(this, SettingActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
    }

    override fun onMapReady(naverMap: NaverMap) {
        val options = NaverMapOptions()
            .camera(CameraPosition(LatLng(37.5670135, 126.9783740), 8.0))
            .mapType(NaverMap.MapType.Terrain)

        val marker = Marker()
        marker.position = LatLng(37.5670135, 126.9783740)
        marker.icon = MarkerIcons.RED
        marker.map = naverMap

        val myMarker = Marker()
        myMarker.position = myLocation!!
    }

    fun getMyLocation(location: Location) {
        myLocation = LatLng(location.latitude, location.longitude)
    }
}