package com.seoyoon.safeproject

import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.NaverMap
import com.naver.maps.map.NaverMapOptions
import com.naver.maps.map.OnMapReadyCallback
import com.naver.maps.map.overlay.Marker
import com.seoyoon.safeproject.databinding.ActivityMainBinding

class MainActivity : FragmentActivity(), OnMapReadyCallback {

    lateinit var binding: ActivityMainBinding
    private var myLocation: LatLng? = LatLng(37.5667, 126.9783)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.mapFragment
    }

    override fun onMapReady(naverMap: NaverMap) {
        val options = NaverMapOptions()
            .camera(CameraPosition(LatLng(35.1798159, 129.0750222), 8.0))
            .mapType(NaverMap.MapType.Terrain)

        val marker = Marker()
        marker.position = LatLng(35.1798159, 129.0750222)
        marker.map = naverMap

        val myMarker = Marker()
        myMarker.position = myLocation!!
    }

    fun getMyLocation(location: Location) {
        myLocation = LatLng(location.latitude, location.longitude)
    }
}