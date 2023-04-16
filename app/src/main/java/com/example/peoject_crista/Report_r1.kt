package com.example.peoject_crista
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties

data class Report_r1(val uid: String? = null,
                     val type: String? = null,
                     val report_the_damage_condition_of_the_road_select_one: String? = null,
var ImageURL:String? = null, var remoteURL:String = "",  val latitude: String = "",
                     val longitude: String = "", val Time: String = "", val Address: String = "" ){

}
