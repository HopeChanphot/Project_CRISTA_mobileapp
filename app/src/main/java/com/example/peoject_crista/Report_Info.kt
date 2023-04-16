package com.example.peoject_crista

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties

data class Report_Info(var username: String? = null, val email: String? = null,
                       val url: String? = null,
                       val uid: String? = null,
                       val type: String? = null,
                       val issue: String? = null,
                       val time: String? = null,
                       val location: String? = null,
                       var report_damaged_power_infrastructure_select_all_that_apply: String? = null,
                       var condition_of_the_electric_supply_select_one: String? = null,
                       var what_hazard_condition_caused_the_damage: String? = null,
                       var availability_of_alternative_electric_supply_in_the_area: String? = null,
                       var report_the_damage_condition_of_the_road_select_one: String? = null,
                       var what_hazard_condition_caused_the_damage_select_all_that_apply: String? = null,
                       var are_alternate_routes_available_for_use_in_the_area_select_one: String? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}
