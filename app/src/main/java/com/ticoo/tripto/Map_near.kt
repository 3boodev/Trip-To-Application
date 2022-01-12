package com.ticoo.tripto

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment

@Suppress("CAST_NEVER_SUCCEEDS", "UNREACHABLE_CODE")
class Map_near :FragmentActivity(),OnMapReadyCallback {

    // Google Map
    private var googleMap: GoogleMap? = null
    // Latitude & Longitude
    //private var Latitude = 0.00
   // private var Longitude = 0.00
    var location = ArrayList<LatLng>()
    val l1=LatLng(13.860633,100.612155)
    val l2=LatLng(13.858747,100.610996)
    val l3=LatLng(13.863903,100.614343)

    //var map: HashMap<String, String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map_near)

// *** Display Google Map
        val mapFragment=supportFragmentManager.findFragmentById(R.id.id_map)as SupportMapFragment
// *** Focus & Zoom
        mapFragment.getMapAsync(this)
        location.add(l1)
        location.add(l2)
        location.add(l3)
    }
    override fun onMapReady(p0: GoogleMap?) {
        googleMap=p0
        for (i in 0 until location.size) {
            googleMap!!.addMarker(MarkerOptions().position(location[i]).title("Res"))
// *** Focus & type
            googleMap!!.mapType = GoogleMap.MAP_TYPE_HYBRID
            googleMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(location[i], 17f))
        }

    }
}
