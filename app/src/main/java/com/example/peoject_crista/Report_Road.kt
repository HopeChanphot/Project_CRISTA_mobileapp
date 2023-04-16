package com.example.peoject_crista

//import com.example.peoject_crista.databinding.ActivityReportRoadBinding
import android.Manifest
import android.app.Activity
import android.app.Person
import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.media.audiofx.Visualizer
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.peoject_crista.databinding.ActivityReportRoadBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*
import java.io.IOException
import com.google.firebase.storage.UploadTask
import kotlin.collections.HashMap
import com.google.firebase.firestore.FirebaseFirestore
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import android.widget.Toast
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.FileProvider
import androidx.core.location.LocationManagerCompat.getCurrentLocation
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.storage.ktx.storage
import com.google.protobuf.DescriptorProtos
import com.hbb20.CountryCodePicker
import java.io.File
import java.lang.Exception
import javax.security.auth.callback.Callback
import com.squareup.picasso.Picasso
import java.time.format.DateTimeFormatter
import java.lang.Override as Override1


class Report_Road : AppCompatActivity() {


    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private lateinit var auth: FirebaseAuth

    lateinit var binding: ActivityReportRoadBinding
    lateinit var ImageUri: Uri


    private lateinit var database: DatabaseReference

    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null


    private lateinit var Report_0: member

    private lateinit var RPD: TextView
    private lateinit var imagePreview: ImageView


    private lateinit var button_c: Button
    private lateinit var UploadB: Button

    private lateinit var uploadProgressBar: ProgressBar

    private lateinit var mainView: View

    private var newPhotoPath: String? = null
    private var visibleImagePath: String? = null

    private var imageFileName: String? = null
    private var photoUri: Uri? = null

    private val NEW_PHOTO_PATH_KEY = "new photo path key"
    private val VISIBLE_IMAGE_PATH_KEY = "visible image path key"

    private val storage = Firebase.storage

    private var latitude:String = ""
    private var longitude: String = ""
    private var remoteURL: String = ""
    private var CountryName: String = ""


    private lateinit var tvla:TextView
    private lateinit var tvlo:TextView
    private lateinit var urlt:TextView
    private lateinit var CountryT:TextView


    private val cameraActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            handleIamge(result)
        }


    private lateinit var rb_b_1: RadioButton
    private lateinit var rb_b_2: RadioButton
    private lateinit var rb_b_3: RadioButton
    private lateinit var rb_b_4: RadioButton
    private lateinit var rb_a_1: RadioButton
    private lateinit var rb_a_2: RadioButton
    private lateinit var rb_a_3: RadioButton
    private lateinit var rb_a_4: RadioButton
    private lateinit var rb_a_5: RadioButton
    private lateinit var rb_a_6: RadioButton
    private lateinit var rb_e_1: RadioButton
    private lateinit var rb_e_2: RadioButton
    private lateinit var rb_e_3: RadioButton
    private lateinit var bth_submit2: Button
    private lateinit var button_gg: Button


    private lateinit var srb_b_1: String
    private lateinit var srb_b_2: String
    private lateinit var srb_b_3: String
    private lateinit var srb_b_4: String
    private lateinit var srb_a_1: String
    private lateinit var srb_a_2: String
    private lateinit var srb_a_3: String
    private lateinit var srb_a_4: String
    private lateinit var srb_a_5: String
    private lateinit var srb_a_6: String
    private lateinit var srb_e_1: String
    private lateinit var srb_e_2: String
    private lateinit var srb_e_3: String

    private var stvla: String = latitude
    private var stvlo: String = longitude
    private var surlt: String = remoteURL
    private var sCountry: String = CountryName



    private lateinit var SRPD: String

    var i = 0

    private val PICK_IMAGE_REQUEST = 100
    private var filePath: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)



        binding = ActivityReportRoadBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_report_road)
        setContentView(binding.root)

        imagePreview = findViewById(R.id.FireBaseImage)


        bth_submit2 = findViewById(R.id.btn_submit2)
        button_gg = findViewById(R.id.button_gg)

        auth = Firebase.auth
        database = Firebase.database.reference

        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        rb_b_1 = findViewById(R.id.rb_b_1)
        rb_b_2 = findViewById(R.id.rb_b_2)
        rb_b_3 = findViewById(R.id.rb_b_3)
        rb_b_4 = findViewById(R.id.rb_b_4)
        rb_a_1 = findViewById(R.id.rb_a_1)
        rb_a_2 = findViewById(R.id.rb_a_2)
        rb_a_3 = findViewById(R.id.rb_a_3)
        rb_a_4 = findViewById(R.id.rb_a_4)
        rb_a_5 = findViewById(R.id.rb_a_5)
        rb_a_6 = findViewById(R.id.rb_a_6)
        rb_e_1 = findViewById(R.id.rb_e_1)
        rb_e_2 = findViewById(R.id.rb_e_2)
        rb_e_3 = findViewById(R.id.rb_e_3)
        RPD = findViewById(R.id.RPD)

        tvla= findViewById(R.id.tvla)

        tvlo= findViewById(R.id.tvlo)
        urlt= findViewById(R.id.urlt)
        CountryT = findViewById(R.id.CountryT)


        binding.buttonGg.setOnClickListener {
            // SelectImage()
            launchGallery()

        }

        newPhotoPath = savedInstanceState?.getString(NEW_PHOTO_PATH_KEY)
        visibleImagePath = savedInstanceState?.getString(VISIBLE_IMAGE_PATH_KEY)

        mainView = findViewById(R.id.content)

        uploadProgressBar = findViewById(R.id.upload_progress_bar)

        button_c = findViewById(R.id.button_c)
        button_c.setOnClickListener {
            takePicture()
            getCurrentLocation()
          ///  uploadImage1()


        }



        UploadB = findViewById(R.id.UploadB)
        UploadB.setOnClickListener {
            uploadImage1()
            uploadImage()
        }











        bth_submit2.setOnClickListener {

            val srb_b_1 = rb_b_1.text.toString().trim()
            val srb_b_2 = rb_b_2.text.toString().trim()
            val srb_b_3 = rb_b_3.text.toString().trim()
            val srb_b_4 = rb_b_4.text.toString().trim()
            val srb_a_1 = rb_a_1.text.toString().trim()
            val srb_a_2 = rb_a_2.text.toString().trim()
            val srb_a_3 = rb_a_3.text.toString().trim()
            val srb_a_4 = rb_a_4.text.toString().trim()
            val srb_a_5 = rb_a_5.text.toString().trim()
            val srb_a_6 = rb_a_6.text.toString().trim()
            val srb_e_1 = rb_e_1.text.toString().trim()
            val srb_e_2 = rb_e_2.text.toString().trim()
            val srb_e_3 = rb_e_3.text.toString().trim()
            val SRPD = RPD.text.toString().trim()













            database = FirebaseDatabase.getInstance().getReference("Report")
            database = Firebase.database.reference

            //   val user = Report_Info(srb_b_1)
            //  val userID = FirebaseAuth.getInstance().currentUser!!.uid

            //    database.child("Report").child(userID).setValue(user)


            val reportMap = hashMapOf(
                "Normal Operation" to srb_b_1,
                "Minor damages" to srb_b_2,
                "Partial Closure" to srb_b_3,
                "Full Closure" to srb_b_4,
                "Heavy Rainfall" to srb_a_1,
                "Landslide" to srb_a_2,
                "Flooding and Inundation" to srb_a_3,
                "Ground Failure" to srb_a_4,
                "Accident" to srb_a_5,
                "Blockage" to srb_a_6,
                "Yes" to srb_e_1,
                "No" to srb_e_2,
                "Do not Know" to srb_e_3
            )


            val userID = database.push().key!!

          ///  uploadImage1()
            //  UploadImage()
           // uploadImage()

            if (rb_b_1.isChecked) {
                //    writeReport5("","","", "","","","","",srb_b_1)

                //    val user = Report_Info(srb_b_1)
                //   val userID = FirebaseAuth.getInstance().currentUser!!.uid

                //        database.child("Report").child(userID).setValue(user)

                val srb_b_1 = rb_b_1.text.toString().trim()

                //  var user = ReportInfo.setReport_the_damage_condition_of_the_road_select_one(srb_b_1)


                saveData1()
                updateUI()


            } else {

            }

            if (rb_b_2.isChecked) {

                //  writeReport5("","","", "","","","", "",srb_b_2)


                val srb_b_2 = rb_b_2.text.toString().trim()









                saveData2()
                updateUI()

            } else {

            }
            if (rb_b_3.isChecked) {

                //    writeReport5("","","", "","","","", "",srb_b_3)


                val srb_b_3 = rb_b_3.text.toString().trim()





                saveData3()
                updateUI()
            } else {

            }
            if (rb_b_4.isChecked) {
                //  writeReport5("","","", "","","","", "",srb_b_4)


                val srb_b_4 = rb_b_4.text.toString().trim()






                saveData4()
                updateUI()
            } else {

            }
            if (rb_a_1.isChecked) {
                // writeReport6("","","", "","","","", "",srb_a_1)

                val srb_a_1 = rb_a_1.text.toString().trim()





                saveData5()
                updateUI()
            } else {

            }
            if (rb_a_2.isChecked) {


                val srb_a_2 = rb_a_2.text.toString().trim()


                // writeReport6("","","", "","","","", "",srb_a_2)
                saveData6()
                updateUI()
            } else {

            }
            if (rb_a_3.isChecked) {

                //   writeReport6("","","", "","","","", "",srb_a_3)


                val srb_a_3 = rb_a_3.text.toString().trim()







                saveData7()
                updateUI()
            } else {

            }
            if (rb_a_4.isChecked) {

                //  writeReport6("","","", "","","","", "",srb_a_4)


                val srb_a_4 = rb_a_4.text.toString().trim()






                saveData8()
                updateUI()
            } else {

            }
            if (rb_a_5.isChecked) {

                // writeReport6("","","", "","","","", "",srb_a_5)


                val srb_a_5 = rb_a_5.text.toString().trim()






                saveData9()
                updateUI()
            } else {

            }
            if (rb_a_6.isChecked) {

                // writeReport6("","","", "","","","", "",srb_a_6)


                val srb_a_6 = rb_a_6.text.toString().trim()



                saveData10()
                updateUI()
            } else {

            }
            if (rb_e_1.isChecked) {
                //  writeReport7("","","", "","","","", "",srb_e_1)


                val srb_e_1 = rb_e_1.text.toString().trim()





                saveData11()
                updateUI()
            } else {

            }
            if (rb_e_2.isChecked) {
                //writeReport7("","","", "","","","", "",srb_e_2)


                val srb_e_2 = rb_e_2.text.toString().trim()




                saveData12()
                updateUI()
            } else {

            }
            if (rb_e_3.isChecked) {
                // writeReport7("","","", "","","","", "",srb_b_3)


                val srb_e_3 = rb_e_3.text.toString().trim()


                saveData13()
                updateUI()
            }


            //   val user = Report_m(srb_b_1)


            //    database.child("Report Road Damages").child(userID).setValue(user)
            //     .addOnCompleteListener {
            //        Toast.makeText(this,"Report successfully",Toast.LENGTH_LONG).show()
            //        updateUI()
            //     }.addOnFailureListener { err ->
            //         Toast.makeText(this,"ERROR ${err.message}", Toast.LENGTH_LONG).show()
            //     }


        }


    }

    fun writeReport(
        username: String, email: String,
        url: String,
        uid: String,
        type: String,
        issue: String,
        time: String,
        location: String,
        report_damaged_power_infrastructure_select_all_that_apply: String,
        condition_of_the_electric_supply_select_one: String,
        what_hazard_condition_caused_the_damage: String,
        availability_of_alternative_electric_supply_in_the_area: String,
        report_the_damage_condition_of_the_road_select_one: String,
        what_hazard_condition_caused_the_damage_select_all_that_apply: String,
        are_alternate_routes_available_for_use_in_the_area_select_one: String
    ) {

        val user = Report_Info(
            username,
            url,
            uid,
            type,
            issue,
            time,
            location,
            report_damaged_power_infrastructure_select_all_that_apply,
            condition_of_the_electric_supply_select_one,
            what_hazard_condition_caused_the_damage,
            availability_of_alternative_electric_supply_in_the_area,
            report_the_damage_condition_of_the_road_select_one,
            what_hazard_condition_caused_the_damage_select_all_that_apply,
            are_alternate_routes_available_for_use_in_the_area_select_one
        )

        // database.child("Report").child(username).setValue(user)


    }


    fun writeReport1(
        username: String, email: String,
        url: String,
        uid: String,
        type: String,
        issue: String,
        time: String,
        location: String,
        report_damaged_power_infrastructure_select_all_that_apply: String
    ) {

        val user = Report_Info(
            username,
            url,
            uid,
            type,
            issue,
            time,
            location,
            report_damaged_power_infrastructure_select_all_that_apply
        )
        // database.child("Report").child(username).setValue(user)


    }

    fun writeReport2(
        username: String, email: String,
        url: String,
        uid: String,
        type: String,
        issue: String,
        time: String,
        location: String,
        what_hazard_condition_caused_the_damage: String
    ) {

        val user = Report_Info(
            username,
            url,
            uid,
            type,
            issue,
            time,
            location,
            what_hazard_condition_caused_the_damage
        )

        //  database.child("Report").child(username).setValue(user)


    }


    fun writeReport3(
        username: String, email: String,
        url: String,
        uid: String,
        type: String,
        issue: String,
        time: String,
        location: String,
        condition_of_the_electric_supply_select_one: String
    ) {

        val user = Report_Info(
            username,
            url,
            uid,
            type,
            issue,
            time,
            location,
            condition_of_the_electric_supply_select_one
        )

        // database.child("Report").child(username).setValue(user)


    }

    fun writeReport4(
        username: String, email: String,
        url: String,
        uid: String,
        type: String,
        issue: String,
        time: String,
        location: String,
        availability_of_alternative_electric_supply_in_the_area: String
    ) {

        val user = Report_Info(
            username,
            url,
            uid,
            type,
            issue,
            time,
            location,
            availability_of_alternative_electric_supply_in_the_area
        )

        // database.child("Report").child(username).setValue(user)


    }

    fun writeReport5(
        username: String, email: String,
        url: String,
        uid: String,
        type: String,
        issue: String,
        time: String,
        location: String,
        report_the_damage_condition_of_the_road_select_one: String
    ) {

        val user = Report_Info(
            username,
            url,
            uid,
            type,
            issue,
            time,
            location,
            report_the_damage_condition_of_the_road_select_one
        )

        // database.child("Report").child(username).setValue(user)


    }

    fun writeReport6(
        username: String, email: String,
        url: String,
        uid: String,
        type: String,
        issue: String,
        time: String,
        location: String,
        what_hazard_condition_caused_the_damage_select_all_that_apply: String
    ) {

        val user = Report_Info(
            username, url, uid, type, issue, time, location,
            what_hazard_condition_caused_the_damage_select_all_that_apply
        )

        //  database.child("Report").child(username).setValue(user)


    }

    fun writeReport7(
        username: String, email: String,
        url: String,
        uid: String,
        type: String,
        issue: String,
        time: String,
        location: String,
        are_alternate_routes_available_for_use_in_the_area_select_one: String
    ) {

        val user = Report_Info(
            username,
            url,
            uid,
            type,
            issue,
            time,
            location,
            are_alternate_routes_available_for_use_in_the_area_select_one
        )

        // database.child("Report").child(username).setValue(user)


    }

    private fun updateUI() {

        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }

    private fun saveData1() {

        srb_b_1 = rb_b_1.text.toString().trim()
        srb_b_2 = rb_b_2.text.toString().trim()
        srb_b_3 = rb_b_3.text.toString().trim()
        srb_b_4 = rb_b_4.text.toString().trim()
        srb_a_1 = rb_a_1.text.toString().trim()
        srb_a_2 = rb_a_2.text.toString().trim()
        srb_a_3 = rb_a_3.text.toString().trim()
        srb_a_4 = rb_a_4.text.toString().trim()
        srb_a_5 = rb_a_5.text.toString().trim()
        srb_a_6 = rb_a_6.text.toString().trim()
        srb_e_1 = rb_e_1.text.toString().trim()
        srb_e_2 = rb_e_2.text.toString().trim()
        srb_e_3 = rb_e_3.text.toString().trim()
        SRPD = RPD.text.toString().trim()

        stvla = tvla.text.toString().trim()

        stvlo = tvlo.text.toString().trim()
        surlt = urlt.text.toString().trim()
        sCountry = CountryT.text.toString().trim()


        val userIDd = FirebaseAuth.getInstance().currentUser!!.uid
        val userID = database.push().key!!
        val Image = ImageUri.toString()
        ///val Image1 = storageReference.push().key!!
        /// Val downloadURL = ImageUri.downloadUrl.toString()
        val imageStorageRootReference = storage.reference
        val imageCollectionReference = imageStorageRootReference.child("images")
        val imageFileReference = imageCollectionReference.child(imageFileName!!)
        val downloadURL = imageFileReference.downloadUrl
       /// val remoteURL = downloadURL.toString()

        val dateTime = SimpleDateFormat("yyyy/MM/dd_HH/mm/ss").format(Date())





        //   val  remoteURL = FirebaseStorage.getInstance().reference.child("images/$userID/")

        ///  val member.setReport_m(srb_b_1)
        //    val user1 = Report_m.report_damaged_power_infrastructure_select_all_that_apply(srb_b_1)

        var user = Report_r1(userID, SRPD, srb_b_1, ImageUri.toString(), surlt ,stvla,stvlo,dateTime,sCountry)
        val user1 = Report_m("", "", "", "", srb_b_1)

        //    database.child("imageUrl").setValue(ImageUri.toString())
        //  database.child("Report Road Damages").child("imageUrl").setValue(ImageUri.toString()).child(userID).child("Question1").setValue(user)
        //  database.child("Report Road Damages").child(userID).child("Question1").setValue(user)

        database.child("Report Road Damages").child(userID).child("Question1").setValue(user)

            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun saveData2() {

        srb_b_1 = rb_b_1.text.toString().trim()
        srb_b_2 = rb_b_2.text.toString().trim()
        srb_b_3 = rb_b_3.text.toString().trim()
        srb_b_4 = rb_b_4.text.toString().trim()
        srb_a_1 = rb_a_1.text.toString().trim()
        srb_a_2 = rb_a_2.text.toString().trim()
        srb_a_3 = rb_a_3.text.toString().trim()
        srb_a_4 = rb_a_4.text.toString().trim()
        srb_a_5 = rb_a_5.text.toString().trim()
        srb_a_6 = rb_a_6.text.toString().trim()
        srb_e_1 = rb_e_1.text.toString().trim()
        srb_e_2 = rb_e_2.text.toString().trim()
        srb_e_3 = rb_e_3.text.toString().trim()
        SRPD = RPD.text.toString().trim()

        stvla = tvla.text.toString().trim()

        stvlo = tvlo.text.toString().trim()
        surlt = urlt.text.toString().trim()
        sCountry = CountryT.text.toString().trim()


        val userIDd = FirebaseAuth.getInstance().currentUser!!.uid
        val userID = database.push().key!!
        val Image = ImageUri.toString()
        ///val Image1 = storageReference.push().key!!
        /// Val downloadURL = ImageUri.downloadUrl.toString()
        val imageStorageRootReference = storage.reference
        val imageCollectionReference = imageStorageRootReference.child("images")
        val imageFileReference = imageCollectionReference.child(imageFileName!!)
        val downloadURL = imageFileReference.downloadUrl
        /// val remoteURL = downloadURL.toString()

        val dateTime = SimpleDateFormat("yyyy/MM/dd_HH/mm/ss").format(Date())

        var user = Report_r1(userID, SRPD, srb_b_2, ImageUri.toString(), surlt ,stvla,stvlo,dateTime,sCountry)
        val user1 = Report_m("", "", "", "", srb_b_2)


        database.child("Report Road Damages").child(userID).child("Question1").setValue(user)
            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun saveData3() {

        srb_b_1 = rb_b_1.text.toString().trim()
        srb_b_2 = rb_b_2.text.toString().trim()
        srb_b_3 = rb_b_3.text.toString().trim()
        srb_b_4 = rb_b_4.text.toString().trim()
        srb_a_1 = rb_a_1.text.toString().trim()
        srb_a_2 = rb_a_2.text.toString().trim()
        srb_a_3 = rb_a_3.text.toString().trim()
        srb_a_4 = rb_a_4.text.toString().trim()
        srb_a_5 = rb_a_5.text.toString().trim()
        srb_a_6 = rb_a_6.text.toString().trim()
        srb_e_1 = rb_e_1.text.toString().trim()
        srb_e_2 = rb_e_2.text.toString().trim()
        srb_e_3 = rb_e_3.text.toString().trim()
        SRPD = RPD.text.toString().trim()

        stvla = tvla.text.toString().trim()

        stvlo = tvlo.text.toString().trim()
        surlt = urlt.text.toString().trim()
        sCountry = CountryT.text.toString().trim()


        val userIDd = FirebaseAuth.getInstance().currentUser!!.uid
        val userID = database.push().key!!
        val Image = ImageUri.toString()
        ///val Image1 = storageReference.push().key!!
        /// Val downloadURL = ImageUri.downloadUrl.toString()
        val imageStorageRootReference = storage.reference
        val imageCollectionReference = imageStorageRootReference.child("images")
        val imageFileReference = imageCollectionReference.child(imageFileName!!)
        val downloadURL = imageFileReference.downloadUrl
        /// val remoteURL = downloadURL.toString()

        val dateTime = SimpleDateFormat("yyyy/MM/dd_HH/mm/ss").format(Date())
        var user = Report_r1(userID, SRPD, srb_b_3, ImageUri.toString(), surlt ,stvla,stvlo,dateTime,sCountry)
        val user1 = Report_m("", "", "", "", srb_b_3)

        database.child("Report Road Damages").child(userID).child("Question1").setValue(user)
            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun saveData4() {

        srb_b_1 = rb_b_1.text.toString().trim()
        srb_b_2 = rb_b_2.text.toString().trim()
        srb_b_3 = rb_b_3.text.toString().trim()
        srb_b_4 = rb_b_4.text.toString().trim()
        srb_a_1 = rb_a_1.text.toString().trim()
        srb_a_2 = rb_a_2.text.toString().trim()
        srb_a_3 = rb_a_3.text.toString().trim()
        srb_a_4 = rb_a_4.text.toString().trim()
        srb_a_5 = rb_a_5.text.toString().trim()
        srb_a_6 = rb_a_6.text.toString().trim()
        srb_e_1 = rb_e_1.text.toString().trim()
        srb_e_2 = rb_e_2.text.toString().trim()
        srb_e_3 = rb_e_3.text.toString().trim()
        SRPD = RPD.text.toString().trim()

        stvla = tvla.text.toString().trim()

        stvlo = tvlo.text.toString().trim()
        surlt = urlt.text.toString().trim()
        sCountry = CountryT.text.toString().trim()


        val userIDd = FirebaseAuth.getInstance().currentUser!!.uid
        val userID = database.push().key!!
        val Image = ImageUri.toString()
        ///val Image1 = storageReference.push().key!!
        /// Val downloadURL = ImageUri.downloadUrl.toString()
        val imageStorageRootReference = storage.reference
        val imageCollectionReference = imageStorageRootReference.child("images")
        val imageFileReference = imageCollectionReference.child(imageFileName!!)
        val downloadURL = imageFileReference.downloadUrl
        /// val remoteURL = downloadURL.toString()

        val dateTime = SimpleDateFormat("yyyy/MM/dd_HH/mm/ss").format(Date())

        var user = Report_r1(userID, SRPD, srb_b_4, ImageUri.toString(), surlt ,stvla,stvlo,dateTime,sCountry)
        val user1 = Report_m("", "", "", "", srb_b_4)


        database.child("Report Road Damages").child(userID).child("Question1").setValue(user)
            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun saveData5() {

        srb_b_1 = rb_b_1.text.toString().trim()
        srb_b_2 = rb_b_2.text.toString().trim()
        srb_b_3 = rb_b_3.text.toString().trim()
        srb_b_4 = rb_b_4.text.toString().trim()
        srb_a_1 = rb_a_1.text.toString().trim()
        srb_a_2 = rb_a_2.text.toString().trim()
        srb_a_3 = rb_a_3.text.toString().trim()
        srb_a_4 = rb_a_4.text.toString().trim()
        srb_a_5 = rb_a_5.text.toString().trim()
        srb_a_6 = rb_a_6.text.toString().trim()
        srb_e_1 = rb_e_1.text.toString().trim()
        srb_e_2 = rb_e_2.text.toString().trim()
        srb_e_3 = rb_e_3.text.toString().trim()
        SRPD = RPD.text.toString().trim()

        stvla = tvla.text.toString().trim()

        stvlo = tvlo.text.toString().trim()
        surlt = urlt.text.toString().trim()
        sCountry = CountryT.text.toString().trim()


        val userIDd = FirebaseAuth.getInstance().currentUser!!.uid
        val userID = database.push().key!!
        val Image = ImageUri.toString()
        ///val Image1 = storageReference.push().key!!
        /// Val downloadURL = ImageUri.downloadUrl.toString()
        val imageStorageRootReference = storage.reference
        val imageCollectionReference = imageStorageRootReference.child("images")
        val imageFileReference = imageCollectionReference.child(imageFileName!!)
        val downloadURL = imageFileReference.downloadUrl
        /// val remoteURL = downloadURL.toString()

        val dateTime = SimpleDateFormat("yyyy/MM/dd_HH/mm/ss").format(Date())

        var user = Report_r2(userID, SRPD, srb_a_1, ImageUri.toString(), surlt ,stvla,stvlo,dateTime,sCountry)
        val user1 = Report_m("", "", "", "", "", srb_a_1)


        database.child("Report Road Damages").child(userID).child("Question2").child("1")
            .setValue(user)
            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun saveData6() {
        srb_b_1 = rb_b_1.text.toString().trim()
        srb_b_2 = rb_b_2.text.toString().trim()
        srb_b_3 = rb_b_3.text.toString().trim()
        srb_b_4 = rb_b_4.text.toString().trim()
        srb_a_1 = rb_a_1.text.toString().trim()
        srb_a_2 = rb_a_2.text.toString().trim()
        srb_a_3 = rb_a_3.text.toString().trim()
        srb_a_4 = rb_a_4.text.toString().trim()
        srb_a_5 = rb_a_5.text.toString().trim()
        srb_a_6 = rb_a_6.text.toString().trim()
        srb_e_1 = rb_e_1.text.toString().trim()
        srb_e_2 = rb_e_2.text.toString().trim()
        srb_e_3 = rb_e_3.text.toString().trim()
        SRPD = RPD.text.toString().trim()

        stvla = tvla.text.toString().trim()

        stvlo = tvlo.text.toString().trim()
        surlt = urlt.text.toString().trim()
        sCountry = CountryT.text.toString().trim()


        val userIDd = FirebaseAuth.getInstance().currentUser!!.uid
        val userID = database.push().key!!
        val Image = ImageUri.toString()
        ///val Image1 = storageReference.push().key!!
        /// Val downloadURL = ImageUri.downloadUrl.toString()
        val imageStorageRootReference = storage.reference
        val imageCollectionReference = imageStorageRootReference.child("images")
        val imageFileReference = imageCollectionReference.child(imageFileName!!)
        val downloadURL = imageFileReference.downloadUrl
        /// val remoteURL = downloadURL.toString()

        val dateTime = SimpleDateFormat("yyyy/MM/dd_HH/mm/ss").format(Date())

        var user = Report_r21(userID, SRPD, srb_a_2, ImageUri.toString(), surlt ,stvla,stvlo,dateTime,sCountry)
        val user1 = Report_m("", "", "", "", "", srb_a_2)


        database.child("Report Road Damages").child(userID).child("Question2").child("2")
            .setValue(user)
            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun saveData7() {

        srb_b_1 = rb_b_1.text.toString().trim()
        srb_b_2 = rb_b_2.text.toString().trim()
        srb_b_3 = rb_b_3.text.toString().trim()
        srb_b_4 = rb_b_4.text.toString().trim()
        srb_a_1 = rb_a_1.text.toString().trim()
        srb_a_2 = rb_a_2.text.toString().trim()
        srb_a_3 = rb_a_3.text.toString().trim()
        srb_a_4 = rb_a_4.text.toString().trim()
        srb_a_5 = rb_a_5.text.toString().trim()
        srb_a_6 = rb_a_6.text.toString().trim()
        srb_e_1 = rb_e_1.text.toString().trim()
        srb_e_2 = rb_e_2.text.toString().trim()
        srb_e_3 = rb_e_3.text.toString().trim()
        SRPD = RPD.text.toString().trim()

        stvla = tvla.text.toString().trim()

        stvlo = tvlo.text.toString().trim()
        surlt = urlt.text.toString().trim()
        sCountry = CountryT.text.toString().trim()


        val userIDd = FirebaseAuth.getInstance().currentUser!!.uid
        val userID = database.push().key!!
        val Image = ImageUri.toString()
        ///val Image1 = storageReference.push().key!!
        /// Val downloadURL = ImageUri.downloadUrl.toString()
        val imageStorageRootReference = storage.reference
        val imageCollectionReference = imageStorageRootReference.child("images")
        val imageFileReference = imageCollectionReference.child(imageFileName!!)
        val downloadURL = imageFileReference.downloadUrl
        /// val remoteURL = downloadURL.toString()

        val dateTime = SimpleDateFormat("yyyy/MM/dd_HH/mm/ss").format(Date())

        var user = Report_r22(userID, SRPD, srb_a_3, ImageUri.toString(), surlt ,stvla,stvlo,dateTime,sCountry)
        val user1 = Report_m("", "", "", "", "", srb_a_3)

        database.child("Report Road Damages").child(userID).child("Question2").child("3")
            .setValue(user)
            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun saveData8() {
        srb_b_1 = rb_b_1.text.toString().trim()
        srb_b_2 = rb_b_2.text.toString().trim()
        srb_b_3 = rb_b_3.text.toString().trim()
        srb_b_4 = rb_b_4.text.toString().trim()
        srb_a_1 = rb_a_1.text.toString().trim()
        srb_a_2 = rb_a_2.text.toString().trim()
        srb_a_3 = rb_a_3.text.toString().trim()
        srb_a_4 = rb_a_4.text.toString().trim()
        srb_a_5 = rb_a_5.text.toString().trim()
        srb_a_6 = rb_a_6.text.toString().trim()
        srb_e_1 = rb_e_1.text.toString().trim()
        srb_e_2 = rb_e_2.text.toString().trim()
        srb_e_3 = rb_e_3.text.toString().trim()
        SRPD = RPD.text.toString().trim()

        stvla = tvla.text.toString().trim()

        stvlo = tvlo.text.toString().trim()
        surlt = urlt.text.toString().trim()
        sCountry = CountryT.text.toString().trim()


        val userIDd = FirebaseAuth.getInstance().currentUser!!.uid
        val userID = database.push().key!!
        val Image = ImageUri.toString()
        ///val Image1 = storageReference.push().key!!
        /// Val downloadURL = ImageUri.downloadUrl.toString()
        val imageStorageRootReference = storage.reference
        val imageCollectionReference = imageStorageRootReference.child("images")
        val imageFileReference = imageCollectionReference.child(imageFileName!!)
        val downloadURL = imageFileReference.downloadUrl
        /// val remoteURL = downloadURL.toString()

        val dateTime = SimpleDateFormat("yyyy/MM/dd_HH/mm/ss").format(Date())

        var user = Report_r23(userID, SRPD, srb_a_4, ImageUri.toString(), surlt ,stvla,stvlo,dateTime,sCountry)
        val user1 = Report_m("", "", "", "", "", srb_a_4)


        database.child("Report Road Damages").child(userID).child("Question2").child("4")
            .setValue(user)
            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun saveData9() {

        srb_b_1 = rb_b_1.text.toString().trim()
        srb_b_2 = rb_b_2.text.toString().trim()
        srb_b_3 = rb_b_3.text.toString().trim()
        srb_b_4 = rb_b_4.text.toString().trim()
        srb_a_1 = rb_a_1.text.toString().trim()
        srb_a_2 = rb_a_2.text.toString().trim()
        srb_a_3 = rb_a_3.text.toString().trim()
        srb_a_4 = rb_a_4.text.toString().trim()
        srb_a_5 = rb_a_5.text.toString().trim()
        srb_a_6 = rb_a_6.text.toString().trim()
        srb_e_1 = rb_e_1.text.toString().trim()
        srb_e_2 = rb_e_2.text.toString().trim()
        srb_e_3 = rb_e_3.text.toString().trim()
        SRPD = RPD.text.toString().trim()

        stvla = tvla.text.toString().trim()

        stvlo = tvlo.text.toString().trim()
        surlt = urlt.text.toString().trim()
        sCountry = CountryT.text.toString().trim()


        val userIDd = FirebaseAuth.getInstance().currentUser!!.uid
        val userID = database.push().key!!
        val Image = ImageUri.toString()
        ///val Image1 = storageReference.push().key!!
        /// Val downloadURL = ImageUri.downloadUrl.toString()
        val imageStorageRootReference = storage.reference
        val imageCollectionReference = imageStorageRootReference.child("images")
        val imageFileReference = imageCollectionReference.child(imageFileName!!)
        val downloadURL = imageFileReference.downloadUrl
        /// val remoteURL = downloadURL.toString()

        val dateTime = SimpleDateFormat("yyyy/MM/dd_HH/mm/ss").format(Date())

        var user = Report_r24(userID, SRPD, srb_a_5, ImageUri.toString(), surlt ,stvla,stvlo,dateTime,sCountry)
        val user1 = Report_m("", "", "", "", "", srb_a_5)


        database.child("Report Road Damages").child(userID).child("Question2").child("5")
            .setValue(user)
            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun saveData10() {

        srb_b_1 = rb_b_1.text.toString().trim()
        srb_b_2 = rb_b_2.text.toString().trim()
        srb_b_3 = rb_b_3.text.toString().trim()
        srb_b_4 = rb_b_4.text.toString().trim()
        srb_a_1 = rb_a_1.text.toString().trim()
        srb_a_2 = rb_a_2.text.toString().trim()
        srb_a_3 = rb_a_3.text.toString().trim()
        srb_a_4 = rb_a_4.text.toString().trim()
        srb_a_5 = rb_a_5.text.toString().trim()
        srb_a_6 = rb_a_6.text.toString().trim()
        srb_e_1 = rb_e_1.text.toString().trim()
        srb_e_2 = rb_e_2.text.toString().trim()
        srb_e_3 = rb_e_3.text.toString().trim()
        SRPD = RPD.text.toString().trim()

        stvla = tvla.text.toString().trim()

        stvlo = tvlo.text.toString().trim()
        surlt = urlt.text.toString().trim()
        sCountry = CountryT.text.toString().trim()


        val userIDd = FirebaseAuth.getInstance().currentUser!!.uid
        val userID = database.push().key!!
        val Image = ImageUri.toString()
        ///val Image1 = storageReference.push().key!!
        /// Val downloadURL = ImageUri.downloadUrl.toString()
        val imageStorageRootReference = storage.reference
        val imageCollectionReference = imageStorageRootReference.child("images")
        val imageFileReference = imageCollectionReference.child(imageFileName!!)
        val downloadURL = imageFileReference.downloadUrl
        /// val remoteURL = downloadURL.toString()

        val dateTime = SimpleDateFormat("yyyy/MM/dd_HH/mm/ss").format(Date())


        var user = Report_r25(userID, SRPD, srb_a_6, ImageUri.toString(), surlt ,stvla,stvlo,dateTime,sCountry)
        val user1 = Report_m("", "", "", "", "", srb_a_6)

        database.child("Report Road Damages").child(userID).child("Question2").child("6")
            .setValue(user)
            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun saveData11() {


        srb_b_1 = rb_b_1.text.toString().trim()
        srb_b_2 = rb_b_2.text.toString().trim()
        srb_b_3 = rb_b_3.text.toString().trim()
        srb_b_4 = rb_b_4.text.toString().trim()
        srb_a_1 = rb_a_1.text.toString().trim()
        srb_a_2 = rb_a_2.text.toString().trim()
        srb_a_3 = rb_a_3.text.toString().trim()
        srb_a_4 = rb_a_4.text.toString().trim()
        srb_a_5 = rb_a_5.text.toString().trim()
        srb_a_6 = rb_a_6.text.toString().trim()
        srb_e_1 = rb_e_1.text.toString().trim()
        srb_e_2 = rb_e_2.text.toString().trim()
        srb_e_3 = rb_e_3.text.toString().trim()
        SRPD = RPD.text.toString().trim()

        stvla = tvla.text.toString().trim()

        stvlo = tvlo.text.toString().trim()
        surlt = urlt.text.toString().trim()
        sCountry = CountryT.text.toString().trim()


        val userIDd = FirebaseAuth.getInstance().currentUser!!.uid
        val userID = database.push().key!!
        val Image = ImageUri.toString()
        ///val Image1 = storageReference.push().key!!
        /// Val downloadURL = ImageUri.downloadUrl.toString()
        val imageStorageRootReference = storage.reference
        val imageCollectionReference = imageStorageRootReference.child("images")
        val imageFileReference = imageCollectionReference.child(imageFileName!!)
        val downloadURL = imageFileReference.downloadUrl
        /// val remoteURL = downloadURL.toString()

        val dateTime = SimpleDateFormat("yyyy/MM/dd_HH/mm/ss").format(Date())

        var user = Report_r3(userID, SRPD, srb_e_1, ImageUri.toString(), surlt ,stvla,stvlo,dateTime,sCountry)
        val user1 = Report_m("", "", "", "", "", "", srb_e_1)

        database.child("Report Road Damages").child(userID).child("Question3").setValue(user)
            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun saveData12() {


        srb_b_1 = rb_b_1.text.toString().trim()
        srb_b_2 = rb_b_2.text.toString().trim()
        srb_b_3 = rb_b_3.text.toString().trim()
        srb_b_4 = rb_b_4.text.toString().trim()
        srb_a_1 = rb_a_1.text.toString().trim()
        srb_a_2 = rb_a_2.text.toString().trim()
        srb_a_3 = rb_a_3.text.toString().trim()
        srb_a_4 = rb_a_4.text.toString().trim()
        srb_a_5 = rb_a_5.text.toString().trim()
        srb_a_6 = rb_a_6.text.toString().trim()
        srb_e_1 = rb_e_1.text.toString().trim()
        srb_e_2 = rb_e_2.text.toString().trim()
        srb_e_3 = rb_e_3.text.toString().trim()
        SRPD = RPD.text.toString().trim()

        stvla = tvla.text.toString().trim()

        stvlo = tvlo.text.toString().trim()
        surlt = urlt.text.toString().trim()
        sCountry = CountryT.text.toString().trim()


        val userIDd = FirebaseAuth.getInstance().currentUser!!.uid
        val userID = database.push().key!!
        val Image = ImageUri.toString()
        ///val Image1 = storageReference.push().key!!
        /// Val downloadURL = ImageUri.downloadUrl.toString()
        val imageStorageRootReference = storage.reference
        val imageCollectionReference = imageStorageRootReference.child("images")
        val imageFileReference = imageCollectionReference.child(imageFileName!!)
        val downloadURL = imageFileReference.downloadUrl
        /// val remoteURL = downloadURL.toString()

        val dateTime = SimpleDateFormat("yyyy/MM/dd_HH/mm/ss").format(Date())

        var user = Report_r3(userID, SRPD, srb_e_2, ImageUri.toString(), surlt ,stvla,stvlo,dateTime,sCountry)
        val user1 = Report_m("", "", "", "", "", "", srb_e_2)

        database.child("Report Road Damages").child(userID).child("Question3").setValue(user)
            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun saveData13() {

        srb_b_1 = rb_b_1.text.toString().trim()
        srb_b_2 = rb_b_2.text.toString().trim()
        srb_b_3 = rb_b_3.text.toString().trim()
        srb_b_4 = rb_b_4.text.toString().trim()
        srb_a_1 = rb_a_1.text.toString().trim()
        srb_a_2 = rb_a_2.text.toString().trim()
        srb_a_3 = rb_a_3.text.toString().trim()
        srb_a_4 = rb_a_4.text.toString().trim()
        srb_a_5 = rb_a_5.text.toString().trim()
        srb_a_6 = rb_a_6.text.toString().trim()
        srb_e_1 = rb_e_1.text.toString().trim()
        srb_e_2 = rb_e_2.text.toString().trim()
        srb_e_3 = rb_e_3.text.toString().trim()
        SRPD = RPD.text.toString().trim()

        stvla = tvla.text.toString().trim()

        stvlo = tvlo.text.toString().trim()
        surlt = urlt.text.toString().trim()
        sCountry = CountryT.text.toString().trim()


        val userIDd = FirebaseAuth.getInstance().currentUser!!.uid
        val userID = database.push().key!!
        val Image = ImageUri.toString()
        ///val Image1 = storageReference.push().key!!
        /// Val downloadURL = ImageUri.downloadUrl.toString()
        val imageStorageRootReference = storage.reference
        val imageCollectionReference = imageStorageRootReference.child("images")
        val imageFileReference = imageCollectionReference.child(imageFileName!!)
        val downloadURL = imageFileReference.downloadUrl
        /// val remoteURL = downloadURL.toString()

        val dateTime = SimpleDateFormat("yyyy/MM/dd_HH/mm/ss").format(Date())

        var user = Report_r3(userID, SRPD, srb_e_3, ImageUri.toString(), surlt ,stvla,stvlo,dateTime,sCountry)
        val user1 = Report_m("", "", "", "", "", "", srb_e_3)

        database.child("Report Road Damages").child(userID).child("Question3").setValue(user)
            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun SelectImage() {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, 100)


    }

    private fun UploadImage() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Uploading File ...")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val formatter = SimpleDateFormat("yyyy_MM_DD_mm_ss", Locale.getDefault())
        val now = Date()
        val fileName = formatter.format(now)
        val storageReference = FirebaseStorage.getInstance().getReference("images/$fileName")

        storageReference.putFile(ImageUri).addOnSuccessListener {

            binding.FireBaseImage.setImageURI(null)
            Toast.makeText(this@Report_Road, "Successfully uploaded", Toast.LENGTH_SHORT).show()
            if (progressDialog.isShowing) progressDialog.dismiss()

        }.addOnFailureListener {
            if (progressDialog.isShowing) progressDialog.dismiss()
            Toast.makeText(this@Report_Road, "Failed", Toast.LENGTH_SHORT).show()

        }


    }

    //override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    //   super.onActivityResult(requestCode, resultCode, data)
    //  if (requestCode == 100 && resultCode == RESULT_OK){

    //     ImageUri = data?.data!!
    //     binding.FireBaseImage.setImageURI(ImageUri)


    //  }
    //  }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }

            filePath = data.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filePath)
                imagePreview.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun uploadImage() {
        if (filePath != null) {
            val ref = storageReference?.child("myImages/" + UUID.randomUUID().toString())
            val uploadTask = ref?.putFile(filePath!!)

        } else {
            Toast.makeText(this, "Please Upload an Image", Toast.LENGTH_SHORT).show()
        }
    }


    private fun launchGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }


    private fun uploadImage1() {




        if (ImageUri != null && imageFileName != null) {
            uploadProgressBar.visibility = View.VISIBLE
            val imageStorageRootReference = storage.reference
            val imageCollectionReference = imageStorageRootReference.child("images")
            val imageFileReference = imageCollectionReference.child(imageFileName!!)
            val downloadURL = imageFileReference.downloadUrl
            val remoteURL = downloadURL.toString()



            imageFileReference.putFile(ImageUri!!).addOnCompleteListener {
                Snackbar.make(mainView, "Image uploaded!", Snackbar.LENGTH_LONG).show()
                uploadProgressBar.visibility = View.GONE
                Log.d(TAG, "Download URL = " + ImageUri.toString())

                imageFileReference.downloadUrl.addOnSuccessListener {

                     urlt.text = it.toString()


                    database.child("imageUrl").setValue(it.toString())


                }

                Log.d(TAG, "Download URL = " + ImageUri.toString());
                //Adding that URL to Realtime database



            //val downloadURL = imageFileReference.downloadUrl
            //val  remoteURL = downloadURL.toString()
            //Adding that URL to Realtime database
            //    database.child("imageUrl").setValue(downloadURL.toString())
            ///    database.child("Report Road Damages").child(ImageUri.toString())
        }
            .addOnFailureListener { error ->
                Snackbar.make(mainView, "Error uploading image", Snackbar.LENGTH_LONG).show()
                Log.e(TAG, "Error uploading image $imageFileName", error)
                uploadProgressBar.visibility = View.GONE
            }
    } else
    {
        Snackbar.make(mainView, "Take a picture first", Snackbar.LENGTH_LONG).show()
    }



}

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(NEW_PHOTO_PATH_KEY, newPhotoPath)
        outState.putString(VISIBLE_IMAGE_PATH_KEY, visibleImagePath)
    }

    private fun takePicture() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val (photoFile, photoFilePath) = createIamgeFile()
        if (photoFile != null) {
            newPhotoPath = photoFilePath
            ImageUri = FileProvider.getUriForFile(this, "com.example.peoject_crista.fileprovider", photoFile)
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, ImageUri)
            cameraActivityLauncher.launch(takePictureIntent)
        }

    }

    private fun createIamgeFile(): Pair<File?, String?> {
        try {
            val dateTime = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
            imageFileName = "REPORT_PHOTO_${dateTime}"
            val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
            val file = File.createTempFile(imageFileName, ".jpg", storageDir)
            val filePath = file.absolutePath
            return file to filePath
        } catch (ex: IOException) {
            return null to null
        }




    }

    private fun handleIamge(result: ActivityResult) {
        when (result.resultCode) {
            RESULT_OK -> {
                Log.d(TAG, "result ok, user took picture, image at $newPhotoPath")
                visibleImagePath = newPhotoPath
            }
            RESULT_CANCELED -> {
                Log.d(TAG, "Result canceled, no picture taken")
            }
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)

        Log.d(TAG, "on window focus changed $hasFocus visible image at $visibleImagePath")
        if (hasFocus) {
            visibleImagePath?.let { imagePath ->
                loadImage(imagePreview, imagePath)
            }
        }
    }

    private fun loadImage(imageView: ImageView, imagePath: String) {
        Picasso.get().load(File(imagePath)).error(android.R.drawable.stat_notify_error).fit().centerCrop().into(imageView, object:
            com.squareup.picasso.Callback {

            override fun onSuccess() {
                Log.d(TAG, "Loaded image $imagePath")
            }

            override fun onError(e: Exception?) {
                Log.e(TAG, "Error loading image $imagePath", e)
            }
        })
    }

   private fun getCurrentLocation()
   {
       val geocoder = Geocoder(this@Report_Road, Locale.getDefault())
       var address:List<Address>
       var adress:List<Address>



       if(checkPermission())
       {
           if (isLocationEnabled())
           {
               fusedLocationProviderClient.lastLocation.addOnCompleteListener(this) { task->
                   val location: Location?=task.result
                   if (location==null)
                   {
                       Toast.makeText(this, "Null Recieved",Toast.LENGTH_SHORT).show()

                   }
                   else
                   {

                       var countryName1 = ""

                       var geoCoder = Geocoder(this, Locale.US)


                       var latitude = location.latitude
                       var longitude = location.longitude


                       address = geocoder.getFromLocation(latitude,longitude, 1)
                       var address:String = address[0].getAddressLine(0)
                       adress = geoCoder.getFromLocation(latitude,longitude,3)


                       Toast.makeText(this, "Get Success",Toast.LENGTH_SHORT).show()
                   //  tvla.text = (latitude + location.latitude).toString()
                  //    tvlo.text = (longitude + location.longitude).toString()

                       tvla.text = latitude.toString()
                       tvlo.text = longitude.toString()
                      // CountryT.text = address
                       countryName1 = adress.get(0).countryName
                       CountryT.text = countryName1
                   }
               }


           }
           else
           {
Toast.makeText(this, "Turn on location",Toast.LENGTH_SHORT).show()
               val intent=Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
               startActivity(intent)

           }
       }
       else
       {
           requestPermission()
       }


   }


    private fun isLocationEnabled():Boolean{
val locationManager:LocationManager=getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)||locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )


    }






    private fun requestPermission() {
ActivityCompat.requestPermissions(
    this, arrayOf(android.Manifest.permission.ACCESS_COARSE_LOCATION,android.Manifest.permission.ACCESS_FINE_LOCATION),
    PERMISSION_REQUEST_ACCESS_LOCATION
)



    }



    companion object{
    private const val PERMISSION_REQUEST_ACCESS_LOCATION=100
}



    private fun checkPermission(): Boolean {

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
            == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults.isNotEmpty() && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
       Toast.makeText(applicationContext,"Granted",Toast.LENGTH_SHORT).show()
       getCurrentLocation()

        }
        else{
            Toast.makeText(applicationContext,"Denied",Toast.LENGTH_SHORT).show()

        }



    }

    private fun getCityName(lat: Double,long: Double):String{
        var cityName:String = ""
        var countryName1 = ""
        var geoCoder = Geocoder(this, Locale.getDefault())
        var Adress = geoCoder.getFromLocation(lat,long,3)

        cityName = Adress.get(0).locality
        countryName1 = Adress.get(0).countryName
        Log.d("Debug:","Your City: " + cityName + " ; your Country " + countryName1)
        return countryName1
        CountryT.text = countryName1

    }





}














