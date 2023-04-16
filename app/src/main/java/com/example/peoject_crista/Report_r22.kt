package com.example.peoject_crista

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties

data class Report_r22(val uid: String? = null,
                     val type: String? = null,
                     val condition_of_the_electric_supply_select_one: String? = null,
                      var ImageURL:String? = null, var remoteURL:String = "",  val latitude: String = "",
                      val longitude: String = "", val Time: String = "", val Address: String = ""){

}