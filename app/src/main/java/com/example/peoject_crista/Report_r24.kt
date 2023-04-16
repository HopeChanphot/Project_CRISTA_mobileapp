package com.example.peoject_crista

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties

data class Report_r24(val uid: String? = null,
                     val type: String? = null,
                     val availability_of_alternative_electric_supply_in_the_area: String? = null,
                      var ImageURL:String? = null, var remoteURL:String = "",  val latitude: String = "",
                      val longitude: String = "", val Time: String = "", val Address: String = ""){

}