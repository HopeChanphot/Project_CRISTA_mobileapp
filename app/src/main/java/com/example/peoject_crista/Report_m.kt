package com.example.peoject_crista
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties


data class Report_m(
    var report_damaged_power_infrastructure_select_all_that_apply: String? = null,
    var condition_of_the_electric_supply_select_one: String? = null,
    var what_hazard_condition_caused_the_damage: String? = null,
    var availability_of_alternative_electric_supply_in_the_area: String? = null,
    var report_the_damage_condition_of_the_road_select_one: String? = null,
    var what_hazard_condition_caused_the_damage_select_all_that_apply: String? = null,
    var are_alternate_routes_available_for_use_in_the_area_select_one: String? = null) {


    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.


    @JvmName("getReport_damaged_power_infrastructure_select_all_that_apply1")
    fun getReport_damaged_power_infrastructure_select_all_that_apply(): String? {
        return report_damaged_power_infrastructure_select_all_that_apply
    }

    @JvmName("setReport_damaged_power_infrastructure_select_all_that_apply1")
    fun setReport_damaged_power_infrastructure_select_all_that_apply(
        report_damaged_power_infrastructure_select_all_that_apply: String?
    ) {
        this.report_damaged_power_infrastructure_select_all_that_apply =
            report_damaged_power_infrastructure_select_all_that_apply
    }

    @JvmName("getCondition_of_the_electric_supply_select_one1")
    fun getCondition_of_the_electric_supply_select_one(): String? {
        return condition_of_the_electric_supply_select_one
    }

    @JvmName("setCondition_of_the_electric_supply_select_one1")
    fun setCondition_of_the_electric_supply_select_one(condition_of_the_electric_supply_select_one: String?) {
        this.condition_of_the_electric_supply_select_one =
            condition_of_the_electric_supply_select_one
    }

    @JvmName("getWhat_hazard_condition_caused_the_damage1")
    fun getWhat_hazard_condition_caused_the_damage(): String? {
        return what_hazard_condition_caused_the_damage
    }

    @JvmName("setWhat_hazard_condition_caused_the_damage1")
    fun setWhat_hazard_condition_caused_the_damage(what_hazard_condition_caused_the_damage: String?) {
        this.what_hazard_condition_caused_the_damage = what_hazard_condition_caused_the_damage
    }

    @JvmName("getAvailability_of_alternative_electric_supply_in_the_area1")
    fun getAvailability_of_alternative_electric_supply_in_the_area(): String? {
        return availability_of_alternative_electric_supply_in_the_area
    }

    @JvmName("setAvailability_of_alternative_electric_supply_in_the_area1")
    fun setAvailability_of_alternative_electric_supply_in_the_area(
        availability_of_alternative_electric_supply_in_the_area: String?
    ) {
        this.availability_of_alternative_electric_supply_in_the_area =
            availability_of_alternative_electric_supply_in_the_area
    }

    @JvmName("getReport_the_damage_condition_of_the_road_select_one1")
    fun getReport_the_damage_condition_of_the_road_select_one(): String? {
        return report_the_damage_condition_of_the_road_select_one
    }

    @JvmName("setReport_the_damage_condition_of_the_road_select_one1")
    fun setReport_the_damage_condition_of_the_road_select_one(
        report_the_damage_condition_of_the_road_select_one: String?
    ) {
        this.report_the_damage_condition_of_the_road_select_one =
            report_the_damage_condition_of_the_road_select_one
    }

    @JvmName("getWhat_hazard_condition_caused_the_damage_select_all_that_apply1")
    fun getWhat_hazard_condition_caused_the_damage_select_all_that_apply(): String? {
        return what_hazard_condition_caused_the_damage_select_all_that_apply
    }

    @JvmName("setWhat_hazard_condition_caused_the_damage_select_all_that_apply1")
    fun setWhat_hazard_condition_caused_the_damage_select_all_that_apply(
        what_hazard_condition_caused_the_damage_select_all_that_apply: String?
    ) {
        this.what_hazard_condition_caused_the_damage_select_all_that_apply =
            what_hazard_condition_caused_the_damage_select_all_that_apply
    }

    @JvmName("getAre_alternate_routes_available_for_use_in_the_area_select_one1")
    fun getAre_alternate_routes_available_for_use_in_the_area_select_one(): String? {
        return are_alternate_routes_available_for_use_in_the_area_select_one
    }

    @JvmName("setAre_alternate_routes_available_for_use_in_the_area_select_one1")
    fun setAre_alternate_routes_available_for_use_in_the_area_select_one(
        are_alternate_routes_available_for_use_in_the_area_select_one: String?
    ) {
        this.are_alternate_routes_available_for_use_in_the_area_select_one =
            are_alternate_routes_available_for_use_in_the_area_select_one
    }

}
