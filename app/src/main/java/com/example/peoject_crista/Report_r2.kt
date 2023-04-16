package com.example.peoject_crista

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties

data class Report_r2(val uid: String? = null,
                     val type: String? = null,
                     val what_hazard_condition_caused_the_damage_select_all_that_apply: String? = null,
                     var ImageURL:String? = null, var remoteURL:String = "",  val latitude: String = "",
                     val longitude: String = "", val Time: String = "", val Address: String = ""){

}
