package com.example.peoject_crista

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties

data class Report_r3( val uid: String? = null,
                      val type: String? = null,
                      val are_alternate_routes_available_for_use_in_the_area_select_one: String? = null,
                      var ImageURL:String? = null, var remoteURL:String = "",  val latitude: String = "",
                      val longitude: String = "", val Time: String = "", val Address: String = ""){

}
