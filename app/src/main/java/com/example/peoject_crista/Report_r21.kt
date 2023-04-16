package com.example.peoject_crista

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties

data class Report_r21(val uid: String? = null,
                     val type: String? = null,
                     val report_damaged_power_infrastructure_select_all_that_apply: String? = null,
                      var ImageURL:String? = null, var remoteURL:String = "",  val latitude: String = "",
                      val longitude: String = "", val Time: String = "", val Address: String = ""){

}