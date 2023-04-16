package com.example.peoject_crista

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
import com.example.peoject_crista.databinding.ActivityReportPowerBinding
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

class Report_Power : AppCompatActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    private lateinit var auth: FirebaseAuth

    lateinit var binding: ActivityReportPowerBinding
    lateinit var ImageUri: Uri


    private lateinit var database: DatabaseReference

    private var firebaseStore: FirebaseStorage? = null
    private var storageReference: StorageReference? = null


    private lateinit var Report_0: member

    private lateinit var RPD2: TextView
    private lateinit var imagePreview: ImageView


    private lateinit var button_c2: Button
    private lateinit var UploadB1: Button

    private lateinit var uploadProgressBar2: ProgressBar

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


    private lateinit var tvla2:TextView
    private lateinit var tvlo2:TextView
    private lateinit var urlt2:TextView
    private lateinit var countryT1:TextView


    private val cameraActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            handleIamge(result)
        }


    private lateinit var ra_a_1: RadioButton
    private lateinit var ra_a_2: RadioButton
    private lateinit var ra_a_3: RadioButton
    private lateinit var ra_a_4: RadioButton
    private lateinit var ra_b_1: RadioButton
    private lateinit var ra_b_2: RadioButton
    private lateinit var ra_b_3: RadioButton
    private lateinit var ra_b_4: RadioButton
    private lateinit var ra_b_5: RadioButton
    private lateinit var ra_c_e: RadioButton
    private lateinit var ra_c_v: RadioButton
    private lateinit var ra_c_g: RadioButton
    private lateinit var ra_c_p: RadioButton
    private lateinit var ra_c_h: RadioButton
    private lateinit var rb_c_j: RadioButton
    private lateinit var ra_e_1: RadioButton
    private lateinit var ra_e_2: RadioButton
    private lateinit var ra_e_3: RadioButton





    private lateinit var bth_submit1: Button
    private lateinit var button_g: Button



    private lateinit var sra_a_1: String
    private lateinit var sra_a_2: String
    private lateinit var sra_a_3: String
    private lateinit var sra_a_4: String
    private lateinit var sra_b_1: String
    private lateinit var sra_b_2: String
    private lateinit var sra_b_3: String
    private lateinit var sra_b_4: String
    private lateinit var sra_b_5: String
    private lateinit var sra_c_e: String
    private lateinit var sra_c_v: String
    private lateinit var sra_c_g: String
    private lateinit var sra_c_p: String
    private lateinit var sra_c_h: String
    private lateinit var srb_c_j: String
    private lateinit var sra_e_1: String
    private lateinit var sra_e_2: String
    private lateinit var sra_e_3: String
    private var stvla2: String = latitude
    private var stvlo2: String = longitude
    private var surlt2: String = remoteURL
    private var sCountry1: String = CountryName



    private lateinit var SRPD2: String

    var i = 0

    private val PICK_IMAGE_REQUEST = 100
    private var filePath: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report_power)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)



        binding = ActivityReportPowerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imagePreview = findViewById(R.id.fireBaseImage1)


        bth_submit1 = findViewById(R.id.btn_submit1)
        button_g = findViewById(R.id.button_g)

        auth = Firebase.auth
        database = Firebase.database.reference

        firebaseStore = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        ra_a_1 = findViewById(R.id.ra_a_1)
        ra_a_2 = findViewById(R.id.ra_a_2)
        ra_a_3 = findViewById(R.id.ra_a_3)
        ra_a_4 = findViewById(R.id.ra_a_4)
        ra_b_1 = findViewById(R.id.ra_b_1)
        ra_b_2 = findViewById(R.id.ra_b_2)
        ra_b_3 = findViewById(R.id.ra_b_3)
        ra_b_4 = findViewById(R.id.ra_b_4)
        ra_b_5 = findViewById(R.id.ra_b_5)
        ra_c_e = findViewById(R.id.ra_c_e)
        ra_c_v = findViewById(R.id.ra_c_v)
        ra_c_g = findViewById(R.id.ra_c_g)
        ra_c_p = findViewById(R.id.ra_c_p)
        ra_c_h = findViewById(R.id.ra_c_h)
        rb_c_j = findViewById(R.id.rb_c_j)
        ra_e_1 = findViewById(R.id.ra_e_1)
        ra_e_2 = findViewById(R.id.ra_e_2)
        ra_e_3 = findViewById(R.id.ra_e_3)



        RPD2 = findViewById(R.id.RPD2)

        tvla2= findViewById(R.id.tvla2)

        tvlo2= findViewById(R.id.tvlo2)
        urlt2= findViewById(R.id.urlt2)
        countryT1 = findViewById(R.id.countryT1)


        button_g.setOnClickListener {
            // SelectImage()
            launchGallery()

        }

        newPhotoPath = savedInstanceState?.getString(NEW_PHOTO_PATH_KEY)
        visibleImagePath = savedInstanceState?.getString(VISIBLE_IMAGE_PATH_KEY)

        mainView = findViewById(R.id.content2)

        uploadProgressBar2= findViewById(R.id.upload_progress_bar2)

        button_c2 = findViewById(R.id.button_c2)
        button_c2.setOnClickListener {
            takePicture()
            getCurrentLocation()
            ///  uploadImage1()


        }



        UploadB1 = findViewById(R.id.uploadB1)
        UploadB1.setOnClickListener {
            uploadImage1()
            uploadImage()
        }











        bth_submit1.setOnClickListener {





            val sra_a_1 = ra_a_1.text.toString().trim()
            val sra_a_2 = ra_a_2.text.toString().trim()
            val sra_a_3 = ra_a_3.text.toString().trim()
            val sra_a_4 = ra_a_4.text.toString().trim()
            val sra_b_1 = ra_b_1.text.toString().trim()
            val sra_b_2 = ra_b_2.text.toString().trim()
            val sra_b_3 = ra_b_3.text.toString().trim()
            val sra_b_4 = ra_b_4.text.toString().trim()
            val sra_b_5 = ra_b_5.text.toString().trim()
            val sra_c_e = ra_c_e.text.toString().trim()
            val sra_c_v = ra_c_v.text.toString().trim()
            val sra_c_g = ra_c_g.text.toString().trim()
            val sra_c_p = ra_c_p.text.toString().trim()
            val sra_c_h = ra_c_h.text.toString().trim()
            val srb_c_j = rb_c_j.text.toString().trim()
            val sra_e_1 = ra_e_1.text.toString().trim()
            val sra_e_2 = ra_e_2.text.toString().trim()
            val sra_e_3 = ra_e_3.text.toString().trim()




            val SRPD2 = RPD2.text.toString().trim()













            database = FirebaseDatabase.getInstance().getReference("Report")
            database = Firebase.database.reference

            //   val user = Report_Info(srb_b_1)
            //  val userID = FirebaseAuth.getInstance().currentUser!!.uid

            //    database.child("Report").child(userID).setValue(user)




            val userID = database.push().key!!

            ///  uploadImage1()
            //  UploadImage()
            // uploadImage()

            if (ra_a_1.isChecked) {
                //    writeReport5("","","", "","","","","",srb_b_1)

                //    val user = Report_Info(srb_b_1)
                //   val userID = FirebaseAuth.getInstance().currentUser!!.uid

                //        database.child("Report").child(userID).setValue(user)

                val sra_a_1 = ra_a_1.text.toString().trim()

                //  var user = ReportInfo.setReport_the_damage_condition_of_the_road_select_one(srb_b_1)


                saveData1()
                updateUI()


            } else {

            }

            if (ra_a_2.isChecked) {

                //  writeReport5("","","", "","","","", "",srb_b_2)


                val sra_a_2 = ra_a_2.text.toString().trim()









                saveData2()
                updateUI()

            } else {

            }
            if (ra_a_3.isChecked) {

                //    writeReport5("","","", "","","","", "",srb_b_3)


                val sra_a_3 = ra_a_3.text.toString().trim()





                saveData3()
                updateUI()
            } else {

            }
            if (ra_a_4.isChecked) {
                //  writeReport5("","","", "","","","", "",srb_b_4)


                val sra_a_4 = ra_a_4.text.toString().trim()






                saveData4()
                updateUI()
            } else {

            }
            if (ra_b_1.isChecked) {
                // writeReport6("","","", "","","","", "",srb_a_1)

                val sra_b_1 = ra_b_1.text.toString().trim()





                saveData5()
                updateUI()
            } else {

            }
            if (ra_b_2.isChecked) {


                val sra_b_2 = ra_b_2.text.toString().trim()


                // writeReport6("","","", "","","","", "",srb_a_2)
                saveData6()
                updateUI()
            } else {

            }
            if (ra_b_3.isChecked) {

                //   writeReport6("","","", "","","","", "",srb_a_3)


                val sra_b_3 = ra_b_3.text.toString().trim()







                saveData7()
                updateUI()
            } else {

            }
            if (ra_b_4.isChecked) {

                //  writeReport6("","","", "","","","", "",srb_a_4)


                val sra_b_4 = ra_b_4.text.toString().trim()






                saveData8()
                updateUI()
            } else {

            }
            if (ra_b_5.isChecked) {

                // writeReport6("","","", "","","","", "",srb_a_5)


                val sra_b_5 = ra_b_5.text.toString().trim()






                saveData9()
                updateUI()
            } else {

            }
            if (ra_c_e.isChecked) {

                // writeReport6("","","", "","","","", "",srb_a_6)


                val sra_c_e = ra_c_e.text.toString().trim()



                saveData10()
                updateUI()
            } else {

            }
            if (ra_c_v.isChecked) {
                //  writeReport7("","","", "","","","", "",srb_e_1)


                val sra_c_v = ra_c_v.text.toString().trim()





                saveData11()
                updateUI()
            } else {

            }
            if (ra_c_g.isChecked) {
                //writeReport7("","","", "","","","", "",srb_e_2)


                val sra_c_g = ra_c_g.text.toString().trim()




                saveData12()
                updateUI()
            } else {

            }
            if (ra_c_p.isChecked) {
                // writeReport7("","","", "","","","", "",srb_b_3)


                val sra_c_p = ra_c_p.text.toString().trim()


                saveData13()
                updateUI()
            }else {

            }
            if (ra_c_h.isChecked) {
                // writeReport7("","","", "","","","", "",srb_b_3)


                val sra_c_h = ra_c_h.text.toString().trim()


                saveData14()
                updateUI()
            }else {

            }

            if (rb_c_j.isChecked) {
                // writeReport7("","","", "","","","", "",srb_b_3)


                val srb_c_j = rb_c_j.text.toString().trim()


                saveData15()
                updateUI()
            }else {

            }

            if (ra_e_1.isChecked) {
                // writeReport7("","","", "","","","", "",srb_b_3)


                val sra_e_1 = ra_e_1.text.toString().trim()


                saveData16()
                updateUI()
            }else {

            }

            if (ra_e_2.isChecked) {
                // writeReport7("","","", "","","","", "",srb_b_3)


                val sra_e_2 = ra_e_2.text.toString().trim()


                saveData17()
                updateUI()
            }else {

            }

            if (ra_e_3.isChecked) {
                // writeReport7("","","", "","","","", "",srb_b_3)


                val sra_e_3 = ra_e_3.text.toString().trim()


                saveData18()
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


    private fun updateUI() {

        val intent = Intent(this, MainActivity2::class.java)
        startActivity(intent)
    }

    private fun saveData1() {

         sra_a_1 = ra_a_1.text.toString().trim()
         sra_a_2 = ra_a_2.text.toString().trim()
         sra_a_3 = ra_a_3.text.toString().trim()
         sra_a_4 = ra_a_4.text.toString().trim()
         sra_b_1 = ra_b_1.text.toString().trim()
         sra_b_2 = ra_b_2.text.toString().trim()
         sra_b_3 = ra_b_3.text.toString().trim()
         sra_b_4 = ra_b_4.text.toString().trim()
         sra_b_5 = ra_b_5.text.toString().trim()
         sra_c_e = ra_c_e.text.toString().trim()
         sra_c_v = ra_c_v.text.toString().trim()
         sra_c_g = ra_c_g.text.toString().trim()
         sra_c_p = ra_c_p.text.toString().trim()
         sra_c_h = ra_c_h.text.toString().trim()
         srb_c_j = rb_c_j.text.toString().trim()
         sra_e_1 = ra_e_1.text.toString().trim()
         sra_e_2 = ra_e_2.text.toString().trim()
         sra_e_3 = ra_e_3.text.toString().trim()

        SRPD2 = RPD2.text.toString().trim()

        stvla2 = tvla2.text.toString().trim()

        stvlo2 = tvlo2.text.toString().trim()
        surlt2 = urlt2.text.toString().trim()
        sCountry1 = countryT1.text.toString().trim()


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

        var user = Report_r21(userID, SRPD2, sra_a_1, ImageUri.toString(), surlt2 ,stvla2,stvlo2,dateTime,sCountry1)

        //    database.child("imageUrl").setValue(ImageUri.toString())
        //  database.child("Report Road Damages").child("imageUrl").setValue(ImageUri.toString()).child(userID).child("Question1").setValue(user)
        //  database.child("Report Road Damages").child(userID).child("Question1").setValue(user)

        database.child("REPORT POWER NETWORK DAMAGES").child(userID).child("Question1").child("1").setValue(user)

            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun saveData2() {

        sra_a_1 = ra_a_1.text.toString().trim()
        sra_a_2 = ra_a_2.text.toString().trim()
        sra_a_3 = ra_a_3.text.toString().trim()
        sra_a_4 = ra_a_4.text.toString().trim()
        sra_b_1 = ra_b_1.text.toString().trim()
        sra_b_2 = ra_b_2.text.toString().trim()
        sra_b_3 = ra_b_3.text.toString().trim()
        sra_b_4 = ra_b_4.text.toString().trim()
        sra_b_5 = ra_b_5.text.toString().trim()
        sra_c_e = ra_c_e.text.toString().trim()
        sra_c_v = ra_c_v.text.toString().trim()
        sra_c_g = ra_c_g.text.toString().trim()
        sra_c_p = ra_c_p.text.toString().trim()
        sra_c_h = ra_c_h.text.toString().trim()
        srb_c_j = rb_c_j.text.toString().trim()
        sra_e_1 = ra_e_1.text.toString().trim()
        sra_e_2 = ra_e_2.text.toString().trim()
        sra_e_3 = ra_e_3.text.toString().trim()

        SRPD2 = RPD2.text.toString().trim()

        stvla2 = tvla2.text.toString().trim()

        stvlo2 = tvlo2.text.toString().trim()
        surlt2 = urlt2.text.toString().trim()
        sCountry1 = countryT1.text.toString().trim()


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

        var user = Report_r21(userID, SRPD2, sra_a_2, ImageUri.toString(), surlt2 ,stvla2,stvlo2,dateTime,sCountry1)

        //    database.child("imageUrl").setValue(ImageUri.toString())
        //  database.child("Report Road Damages").child("imageUrl").setValue(ImageUri.toString()).child(userID).child("Question1").setValue(user)
        //  database.child("Report Road Damages").child(userID).child("Question1").setValue(user)

        database.child("REPORT POWER NETWORK DAMAGES").child(userID).child("Question1").child("2").setValue(user)

            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun saveData3() {
        sra_a_1 = ra_a_1.text.toString().trim()
        sra_a_2 = ra_a_2.text.toString().trim()
        sra_a_3 = ra_a_3.text.toString().trim()
        sra_a_4 = ra_a_4.text.toString().trim()
        sra_b_1 = ra_b_1.text.toString().trim()
        sra_b_2 = ra_b_2.text.toString().trim()
        sra_b_3 = ra_b_3.text.toString().trim()
        sra_b_4 = ra_b_4.text.toString().trim()
        sra_b_5 = ra_b_5.text.toString().trim()
        sra_c_e = ra_c_e.text.toString().trim()
        sra_c_v = ra_c_v.text.toString().trim()
        sra_c_g = ra_c_g.text.toString().trim()
        sra_c_p = ra_c_p.text.toString().trim()
        sra_c_h = ra_c_h.text.toString().trim()
        srb_c_j = rb_c_j.text.toString().trim()
        sra_e_1 = ra_e_1.text.toString().trim()
        sra_e_2 = ra_e_2.text.toString().trim()
        sra_e_3 = ra_e_3.text.toString().trim()

        SRPD2 = RPD2.text.toString().trim()

        stvla2 = tvla2.text.toString().trim()

        stvlo2 = tvlo2.text.toString().trim()
        surlt2 = urlt2.text.toString().trim()
        sCountry1 = countryT1.text.toString().trim()


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

        var user = Report_r21(userID, SRPD2, sra_a_3, ImageUri.toString(), surlt2 ,stvla2,stvlo2,dateTime,sCountry1)

        //    database.child("imageUrl").setValue(ImageUri.toString())
        //  database.child("Report Road Damages").child("imageUrl").setValue(ImageUri.toString()).child(userID).child("Question1").setValue(user)
        //  database.child("Report Road Damages").child(userID).child("Question1").setValue(user)

        database.child("REPORT POWER NETWORK DAMAGES").child(userID).child("Question1").child("3").setValue(user)

            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun saveData4() {

        sra_a_1 = ra_a_1.text.toString().trim()
        sra_a_2 = ra_a_2.text.toString().trim()
        sra_a_3 = ra_a_3.text.toString().trim()
        sra_a_4 = ra_a_4.text.toString().trim()
        sra_b_1 = ra_b_1.text.toString().trim()
        sra_b_2 = ra_b_2.text.toString().trim()
        sra_b_3 = ra_b_3.text.toString().trim()
        sra_b_4 = ra_b_4.text.toString().trim()
        sra_b_5 = ra_b_5.text.toString().trim()
        sra_c_e = ra_c_e.text.toString().trim()
        sra_c_v = ra_c_v.text.toString().trim()
        sra_c_g = ra_c_g.text.toString().trim()
        sra_c_p = ra_c_p.text.toString().trim()
        sra_c_h = ra_c_h.text.toString().trim()
        srb_c_j = rb_c_j.text.toString().trim()
        sra_e_1 = ra_e_1.text.toString().trim()
        sra_e_2 = ra_e_2.text.toString().trim()
        sra_e_3 = ra_e_3.text.toString().trim()

        SRPD2 = RPD2.text.toString().trim()

        stvla2 = tvla2.text.toString().trim()

        stvlo2 = tvlo2.text.toString().trim()
        surlt2 = urlt2.text.toString().trim()
        sCountry1 = countryT1.text.toString().trim()


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

        var user = Report_r21(userID, SRPD2, sra_a_4, ImageUri.toString(), surlt2 ,stvla2,stvlo2,dateTime,sCountry1)

        //    database.child("imageUrl").setValue(ImageUri.toString())
        //  database.child("Report Road Damages").child("imageUrl").setValue(ImageUri.toString()).child(userID).child("Question1").setValue(user)
        //  database.child("Report Road Damages").child(userID).child("Question1").setValue(user)

        database.child("REPORT POWER NETWORK DAMAGES").child(userID).child("Question1").child("4").setValue(user)

            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun saveData5() {

        sra_a_1 = ra_a_1.text.toString().trim()
        sra_a_2 = ra_a_2.text.toString().trim()
        sra_a_3 = ra_a_3.text.toString().trim()
        sra_a_4 = ra_a_4.text.toString().trim()
        sra_b_1 = ra_b_1.text.toString().trim()
        sra_b_2 = ra_b_2.text.toString().trim()
        sra_b_3 = ra_b_3.text.toString().trim()
        sra_b_4 = ra_b_4.text.toString().trim()
        sra_b_5 = ra_b_5.text.toString().trim()
        sra_c_e = ra_c_e.text.toString().trim()
        sra_c_v = ra_c_v.text.toString().trim()
        sra_c_g = ra_c_g.text.toString().trim()
        sra_c_p = ra_c_p.text.toString().trim()
        sra_c_h = ra_c_h.text.toString().trim()
        srb_c_j = rb_c_j.text.toString().trim()
        sra_e_1 = ra_e_1.text.toString().trim()
        sra_e_2 = ra_e_2.text.toString().trim()
        sra_e_3 = ra_e_3.text.toString().trim()

        SRPD2 = RPD2.text.toString().trim()

        stvla2 = tvla2.text.toString().trim()

        stvlo2 = tvlo2.text.toString().trim()
        surlt2 = urlt2.text.toString().trim()
        sCountry1 = countryT1.text.toString().trim()


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

        var user = Report_r22(userID, SRPD2, sra_b_1, ImageUri.toString(), surlt2 ,stvla2,stvlo2,dateTime,sCountry1)

        //    database.child("imageUrl").setValue(ImageUri.toString())
        //  database.child("Report Road Damages").child("imageUrl").setValue(ImageUri.toString()).child(userID).child("Question1").setValue(user)
        //  database.child("Report Road Damages").child(userID).child("Question1").setValue(user)

        database.child("REPORT POWER NETWORK DAMAGES").child(userID).child("Question2").setValue(user)

            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun saveData6() {
        sra_a_1 = ra_a_1.text.toString().trim()
        sra_a_2 = ra_a_2.text.toString().trim()
        sra_a_3 = ra_a_3.text.toString().trim()
        sra_a_4 = ra_a_4.text.toString().trim()
        sra_b_1 = ra_b_1.text.toString().trim()
        sra_b_2 = ra_b_2.text.toString().trim()
        sra_b_3 = ra_b_3.text.toString().trim()
        sra_b_4 = ra_b_4.text.toString().trim()
        sra_b_5 = ra_b_5.text.toString().trim()
        sra_c_e = ra_c_e.text.toString().trim()
        sra_c_v = ra_c_v.text.toString().trim()
        sra_c_g = ra_c_g.text.toString().trim()
        sra_c_p = ra_c_p.text.toString().trim()
        sra_c_h = ra_c_h.text.toString().trim()
        srb_c_j = rb_c_j.text.toString().trim()
        sra_e_1 = ra_e_1.text.toString().trim()
        sra_e_2 = ra_e_2.text.toString().trim()
        sra_e_3 = ra_e_3.text.toString().trim()

        SRPD2 = RPD2.text.toString().trim()

        stvla2 = tvla2.text.toString().trim()

        stvlo2 = tvlo2.text.toString().trim()
        surlt2 = urlt2.text.toString().trim()
        sCountry1 = countryT1.text.toString().trim()


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

        var user = Report_r22(userID, SRPD2, sra_b_2, ImageUri.toString(), surlt2 ,stvla2,stvlo2,dateTime,sCountry1)

        //    database.child("imageUrl").setValue(ImageUri.toString())
        //  database.child("Report Road Damages").child("imageUrl").setValue(ImageUri.toString()).child(userID).child("Question1").setValue(user)
        //  database.child("Report Road Damages").child(userID).child("Question1").setValue(user)

        database.child("REPORT POWER NETWORK DAMAGES").child(userID).child("Question2").setValue(user)

            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun saveData7() {

        sra_a_1 = ra_a_1.text.toString().trim()
        sra_a_2 = ra_a_2.text.toString().trim()
        sra_a_3 = ra_a_3.text.toString().trim()
        sra_a_4 = ra_a_4.text.toString().trim()
        sra_b_1 = ra_b_1.text.toString().trim()
        sra_b_2 = ra_b_2.text.toString().trim()
        sra_b_3 = ra_b_3.text.toString().trim()
        sra_b_4 = ra_b_4.text.toString().trim()
        sra_b_5 = ra_b_5.text.toString().trim()
        sra_c_e = ra_c_e.text.toString().trim()
        sra_c_v = ra_c_v.text.toString().trim()
        sra_c_g = ra_c_g.text.toString().trim()
        sra_c_p = ra_c_p.text.toString().trim()
        sra_c_h = ra_c_h.text.toString().trim()
        srb_c_j = rb_c_j.text.toString().trim()
        sra_e_1 = ra_e_1.text.toString().trim()
        sra_e_2 = ra_e_2.text.toString().trim()
        sra_e_3 = ra_e_3.text.toString().trim()

        SRPD2 = RPD2.text.toString().trim()

        stvla2 = tvla2.text.toString().trim()

        stvlo2 = tvlo2.text.toString().trim()
        surlt2 = urlt2.text.toString().trim()
        sCountry1 = countryT1.text.toString().trim()


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

        var user = Report_r22(userID, SRPD2, sra_b_3, ImageUri.toString(), surlt2 ,stvla2,stvlo2,dateTime,sCountry1)

        //    database.child("imageUrl").setValue(ImageUri.toString())
        //  database.child("Report Road Damages").child("imageUrl").setValue(ImageUri.toString()).child(userID).child("Question1").setValue(user)
        //  database.child("Report Road Damages").child(userID).child("Question1").setValue(user)

        database.child("REPORT POWER NETWORK DAMAGES").child(userID).child("Question2").setValue(user)

            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun saveData8() {
        sra_a_1 = ra_a_1.text.toString().trim()
        sra_a_2 = ra_a_2.text.toString().trim()
        sra_a_3 = ra_a_3.text.toString().trim()
        sra_a_4 = ra_a_4.text.toString().trim()
        sra_b_1 = ra_b_1.text.toString().trim()
        sra_b_2 = ra_b_2.text.toString().trim()
        sra_b_3 = ra_b_3.text.toString().trim()
        sra_b_4 = ra_b_4.text.toString().trim()
        sra_b_5 = ra_b_5.text.toString().trim()
        sra_c_e = ra_c_e.text.toString().trim()
        sra_c_v = ra_c_v.text.toString().trim()
        sra_c_g = ra_c_g.text.toString().trim()
        sra_c_p = ra_c_p.text.toString().trim()
        sra_c_h = ra_c_h.text.toString().trim()
        srb_c_j = rb_c_j.text.toString().trim()
        sra_e_1 = ra_e_1.text.toString().trim()
        sra_e_2 = ra_e_2.text.toString().trim()
        sra_e_3 = ra_e_3.text.toString().trim()

        SRPD2 = RPD2.text.toString().trim()

        stvla2 = tvla2.text.toString().trim()

        stvlo2 = tvlo2.text.toString().trim()
        surlt2 = urlt2.text.toString().trim()
        sCountry1 = countryT1.text.toString().trim()


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

        var user = Report_r22(userID, SRPD2, sra_b_4, ImageUri.toString(), surlt2 ,stvla2,stvlo2,dateTime,sCountry1)

        //    database.child("imageUrl").setValue(ImageUri.toString())
        //  database.child("Report Road Damages").child("imageUrl").setValue(ImageUri.toString()).child(userID).child("Question1").setValue(user)
        //  database.child("Report Road Damages").child(userID).child("Question1").setValue(user)

        database.child("REPORT POWER NETWORK DAMAGES").child(userID).child("Question2").setValue(user)

            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun saveData9() {

        sra_a_1 = ra_a_1.text.toString().trim()
        sra_a_2 = ra_a_2.text.toString().trim()
        sra_a_3 = ra_a_3.text.toString().trim()
        sra_a_4 = ra_a_4.text.toString().trim()
        sra_b_1 = ra_b_1.text.toString().trim()
        sra_b_2 = ra_b_2.text.toString().trim()
        sra_b_3 = ra_b_3.text.toString().trim()
        sra_b_4 = ra_b_4.text.toString().trim()
        sra_b_5 = ra_b_5.text.toString().trim()
        sra_c_e = ra_c_e.text.toString().trim()
        sra_c_v = ra_c_v.text.toString().trim()
        sra_c_g = ra_c_g.text.toString().trim()
        sra_c_p = ra_c_p.text.toString().trim()
        sra_c_h = ra_c_h.text.toString().trim()
        srb_c_j = rb_c_j.text.toString().trim()
        sra_e_1 = ra_e_1.text.toString().trim()
        sra_e_2 = ra_e_2.text.toString().trim()
        sra_e_3 = ra_e_3.text.toString().trim()

        SRPD2 = RPD2.text.toString().trim()

        stvla2 = tvla2.text.toString().trim()

        stvlo2 = tvlo2.text.toString().trim()
        surlt2 = urlt2.text.toString().trim()
        sCountry1 = countryT1.text.toString().trim()


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

        var user = Report_r22(userID, SRPD2, sra_b_5, ImageUri.toString(), surlt2 ,stvla2,stvlo2,dateTime,sCountry1)

        //    database.child("imageUrl").setValue(ImageUri.toString())
        //  database.child("Report Road Damages").child("imageUrl").setValue(ImageUri.toString()).child(userID).child("Question1").setValue(user)
        //  database.child("Report Road Damages").child(userID).child("Question1").setValue(user)

        database.child("REPORT POWER NETWORK DAMAGES").child(userID).child("Question2").setValue(user)

            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun saveData10() {

        sra_a_1 = ra_a_1.text.toString().trim()
        sra_a_2 = ra_a_2.text.toString().trim()
        sra_a_3 = ra_a_3.text.toString().trim()
        sra_a_4 = ra_a_4.text.toString().trim()
        sra_b_1 = ra_b_1.text.toString().trim()
        sra_b_2 = ra_b_2.text.toString().trim()
        sra_b_3 = ra_b_3.text.toString().trim()
        sra_b_4 = ra_b_4.text.toString().trim()
        sra_b_5 = ra_b_5.text.toString().trim()
        sra_c_e = ra_c_e.text.toString().trim()
        sra_c_v = ra_c_v.text.toString().trim()
        sra_c_g = ra_c_g.text.toString().trim()
        sra_c_p = ra_c_p.text.toString().trim()
        sra_c_h = ra_c_h.text.toString().trim()
        srb_c_j = rb_c_j.text.toString().trim()
        sra_e_1 = ra_e_1.text.toString().trim()
        sra_e_2 = ra_e_2.text.toString().trim()
        sra_e_3 = ra_e_3.text.toString().trim()

        SRPD2 = RPD2.text.toString().trim()

        stvla2 = tvla2.text.toString().trim()

        stvlo2 = tvlo2.text.toString().trim()
        surlt2 = urlt2.text.toString().trim()
        sCountry1 = countryT1.text.toString().trim()


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

        var user = Report_r23(userID, SRPD2, sra_c_e, ImageUri.toString(), surlt2 ,stvla2,stvlo2,dateTime,sCountry1)

        //    database.child("imageUrl").setValue(ImageUri.toString())
        //  database.child("Report Road Damages").child("imageUrl").setValue(ImageUri.toString()).child(userID).child("Question1").setValue(user)
        //  database.child("Report Road Damages").child(userID).child("Question1").setValue(user)

        database.child("REPORT POWER NETWORK DAMAGES").child(userID).child("Question3").setValue(user)

            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun saveData11() {


        sra_a_1 = ra_a_1.text.toString().trim()
        sra_a_2 = ra_a_2.text.toString().trim()
        sra_a_3 = ra_a_3.text.toString().trim()
        sra_a_4 = ra_a_4.text.toString().trim()
        sra_b_1 = ra_b_1.text.toString().trim()
        sra_b_2 = ra_b_2.text.toString().trim()
        sra_b_3 = ra_b_3.text.toString().trim()
        sra_b_4 = ra_b_4.text.toString().trim()
        sra_b_5 = ra_b_5.text.toString().trim()
        sra_c_e = ra_c_e.text.toString().trim()
        sra_c_v = ra_c_v.text.toString().trim()
        sra_c_g = ra_c_g.text.toString().trim()
        sra_c_p = ra_c_p.text.toString().trim()
        sra_c_h = ra_c_h.text.toString().trim()
        srb_c_j = rb_c_j.text.toString().trim()
        sra_e_1 = ra_e_1.text.toString().trim()
        sra_e_2 = ra_e_2.text.toString().trim()
        sra_e_3 = ra_e_3.text.toString().trim()

        SRPD2 = RPD2.text.toString().trim()

        stvla2 = tvla2.text.toString().trim()

        stvlo2 = tvlo2.text.toString().trim()
        surlt2 = urlt2.text.toString().trim()
        sCountry1 = countryT1.text.toString().trim()


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

        var user = Report_r23(userID, SRPD2, sra_c_v, ImageUri.toString(), surlt2 ,stvla2,stvlo2,dateTime,sCountry1)

        //    database.child("imageUrl").setValue(ImageUri.toString())
        //  database.child("Report Road Damages").child("imageUrl").setValue(ImageUri.toString()).child(userID).child("Question1").setValue(user)
        //  database.child("Report Road Damages").child(userID).child("Question1").setValue(user)

        database.child("REPORT POWER NETWORK DAMAGES").child(userID).child("Question3").setValue(user)

            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun saveData12() {


        sra_a_1 = ra_a_1.text.toString().trim()
        sra_a_2 = ra_a_2.text.toString().trim()
        sra_a_3 = ra_a_3.text.toString().trim()
        sra_a_4 = ra_a_4.text.toString().trim()
        sra_b_1 = ra_b_1.text.toString().trim()
        sra_b_2 = ra_b_2.text.toString().trim()
        sra_b_3 = ra_b_3.text.toString().trim()
        sra_b_4 = ra_b_4.text.toString().trim()
        sra_b_5 = ra_b_5.text.toString().trim()
        sra_c_e = ra_c_e.text.toString().trim()
        sra_c_v = ra_c_v.text.toString().trim()
        sra_c_g = ra_c_g.text.toString().trim()
        sra_c_p = ra_c_p.text.toString().trim()
        sra_c_h = ra_c_h.text.toString().trim()
        srb_c_j = rb_c_j.text.toString().trim()
        sra_e_1 = ra_e_1.text.toString().trim()
        sra_e_2 = ra_e_2.text.toString().trim()
        sra_e_3 = ra_e_3.text.toString().trim()

        SRPD2 = RPD2.text.toString().trim()

        stvla2 = tvla2.text.toString().trim()

        stvlo2 = tvlo2.text.toString().trim()
        surlt2 = urlt2.text.toString().trim()
        sCountry1 = countryT1.text.toString().trim()


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

        var user = Report_r23(userID, SRPD2, sra_c_g, ImageUri.toString(), surlt2 ,stvla2,stvlo2,dateTime,sCountry1)

        //    database.child("imageUrl").setValue(ImageUri.toString())
        //  database.child("Report Road Damages").child("imageUrl").setValue(ImageUri.toString()).child(userID).child("Question1").setValue(user)
        //  database.child("Report Road Damages").child(userID).child("Question1").setValue(user)

        database.child("REPORT POWER NETWORK DAMAGES").child(userID).child("Question3").setValue(user)

            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun saveData13() {

        sra_a_1 = ra_a_1.text.toString().trim()
        sra_a_2 = ra_a_2.text.toString().trim()
        sra_a_3 = ra_a_3.text.toString().trim()
        sra_a_4 = ra_a_4.text.toString().trim()
        sra_b_1 = ra_b_1.text.toString().trim()
        sra_b_2 = ra_b_2.text.toString().trim()
        sra_b_3 = ra_b_3.text.toString().trim()
        sra_b_4 = ra_b_4.text.toString().trim()
        sra_b_5 = ra_b_5.text.toString().trim()
        sra_c_e = ra_c_e.text.toString().trim()
        sra_c_v = ra_c_v.text.toString().trim()
        sra_c_g = ra_c_g.text.toString().trim()
        sra_c_p = ra_c_p.text.toString().trim()
        sra_c_h = ra_c_h.text.toString().trim()
        srb_c_j = rb_c_j.text.toString().trim()
        sra_e_1 = ra_e_1.text.toString().trim()
        sra_e_2 = ra_e_2.text.toString().trim()
        sra_e_3 = ra_e_3.text.toString().trim()

        SRPD2 = RPD2.text.toString().trim()

        stvla2 = tvla2.text.toString().trim()

        stvlo2 = tvlo2.text.toString().trim()
        surlt2 = urlt2.text.toString().trim()
        sCountry1 = countryT1.text.toString().trim()


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

        var user = Report_r23(userID, SRPD2, sra_c_p, ImageUri.toString(), surlt2 ,stvla2,stvlo2,dateTime,sCountry1)

        //    database.child("imageUrl").setValue(ImageUri.toString())
        //  database.child("Report Road Damages").child("imageUrl").setValue(ImageUri.toString()).child(userID).child("Question1").setValue(user)
        //  database.child("Report Road Damages").child(userID).child("Question1").setValue(user)

        database.child("REPORT POWER NETWORK DAMAGES").child(userID).child("Question3").setValue(user)

            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }

    private fun saveData14() {

        sra_a_1 = ra_a_1.text.toString().trim()
        sra_a_2 = ra_a_2.text.toString().trim()
        sra_a_3 = ra_a_3.text.toString().trim()
        sra_a_4 = ra_a_4.text.toString().trim()
        sra_b_1 = ra_b_1.text.toString().trim()
        sra_b_2 = ra_b_2.text.toString().trim()
        sra_b_3 = ra_b_3.text.toString().trim()
        sra_b_4 = ra_b_4.text.toString().trim()
        sra_b_5 = ra_b_5.text.toString().trim()
        sra_c_e = ra_c_e.text.toString().trim()
        sra_c_v = ra_c_v.text.toString().trim()
        sra_c_g = ra_c_g.text.toString().trim()
        sra_c_p = ra_c_p.text.toString().trim()
        sra_c_h = ra_c_h.text.toString().trim()
        srb_c_j = rb_c_j.text.toString().trim()
        sra_e_1 = ra_e_1.text.toString().trim()
        sra_e_2 = ra_e_2.text.toString().trim()
        sra_e_3 = ra_e_3.text.toString().trim()

        SRPD2 = RPD2.text.toString().trim()

        stvla2 = tvla2.text.toString().trim()

        stvlo2 = tvlo2.text.toString().trim()
        surlt2 = urlt2.text.toString().trim()
        sCountry1 = countryT1.text.toString().trim()


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

        var user = Report_r23(userID, SRPD2, sra_c_h, ImageUri.toString(), surlt2 ,stvla2,stvlo2,dateTime,sCountry1)

        //    database.child("imageUrl").setValue(ImageUri.toString())
        //  database.child("Report Road Damages").child("imageUrl").setValue(ImageUri.toString()).child(userID).child("Question1").setValue(user)
        //  database.child("Report Road Damages").child(userID).child("Question1").setValue(user)

        database.child("REPORT POWER NETWORK DAMAGES").child(userID).child("Question3").setValue(user)

            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }


    }

    private fun saveData15() {
        sra_a_1 = ra_a_1.text.toString().trim()
        sra_a_2 = ra_a_2.text.toString().trim()
        sra_a_3 = ra_a_3.text.toString().trim()
        sra_a_4 = ra_a_4.text.toString().trim()
        sra_b_1 = ra_b_1.text.toString().trim()
        sra_b_2 = ra_b_2.text.toString().trim()
        sra_b_3 = ra_b_3.text.toString().trim()
        sra_b_4 = ra_b_4.text.toString().trim()
        sra_b_5 = ra_b_5.text.toString().trim()
        sra_c_e = ra_c_e.text.toString().trim()
        sra_c_v = ra_c_v.text.toString().trim()
        sra_c_g = ra_c_g.text.toString().trim()
        sra_c_p = ra_c_p.text.toString().trim()
        sra_c_h = ra_c_h.text.toString().trim()
        srb_c_j = rb_c_j.text.toString().trim()
        sra_e_1 = ra_e_1.text.toString().trim()
        sra_e_2 = ra_e_2.text.toString().trim()
        sra_e_3 = ra_e_3.text.toString().trim()

        SRPD2 = RPD2.text.toString().trim()

        stvla2 = tvla2.text.toString().trim()

        stvlo2 = tvlo2.text.toString().trim()
        surlt2 = urlt2.text.toString().trim()
        sCountry1 = countryT1.text.toString().trim()


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

        var user = Report_r23(userID, SRPD2, srb_c_j, ImageUri.toString(), surlt2 ,stvla2,stvlo2,dateTime,sCountry1)

        //    database.child("imageUrl").setValue(ImageUri.toString())
        //  database.child("Report Road Damages").child("imageUrl").setValue(ImageUri.toString()).child(userID).child("Question1").setValue(user)
        //  database.child("Report Road Damages").child(userID).child("Question1").setValue(user)

        database.child("REPORT POWER NETWORK DAMAGES").child(userID).child("Question3").setValue(user)

            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }


    }
    private fun saveData16() {
        sra_a_1 = ra_a_1.text.toString().trim()
        sra_a_2 = ra_a_2.text.toString().trim()
        sra_a_3 = ra_a_3.text.toString().trim()
        sra_a_4 = ra_a_4.text.toString().trim()
        sra_b_1 = ra_b_1.text.toString().trim()
        sra_b_2 = ra_b_2.text.toString().trim()
        sra_b_3 = ra_b_3.text.toString().trim()
        sra_b_4 = ra_b_4.text.toString().trim()
        sra_b_5 = ra_b_5.text.toString().trim()
        sra_c_e = ra_c_e.text.toString().trim()
        sra_c_v = ra_c_v.text.toString().trim()
        sra_c_g = ra_c_g.text.toString().trim()
        sra_c_p = ra_c_p.text.toString().trim()
        sra_c_h = ra_c_h.text.toString().trim()
        srb_c_j = rb_c_j.text.toString().trim()
        sra_e_1 = ra_e_1.text.toString().trim()
        sra_e_2 = ra_e_2.text.toString().trim()
        sra_e_3 = ra_e_3.text.toString().trim()

        SRPD2 = RPD2.text.toString().trim()

        stvla2 = tvla2.text.toString().trim()

        stvlo2 = tvlo2.text.toString().trim()
        surlt2 = urlt2.text.toString().trim()
        sCountry1 = countryT1.text.toString().trim()


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

        var user = Report_r24(userID, SRPD2, sra_e_1, ImageUri.toString(), surlt2 ,stvla2,stvlo2,dateTime,sCountry1)

        //    database.child("imageUrl").setValue(ImageUri.toString())
        //  database.child("Report Road Damages").child("imageUrl").setValue(ImageUri.toString()).child(userID).child("Question1").setValue(user)
        //  database.child("Report Road Damages").child(userID).child("Question1").setValue(user)

        database.child("REPORT POWER NETWORK DAMAGES").child(userID).child("Question3").setValue(user)

            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }



    }
    private fun saveData17() {

        sra_a_1 = ra_a_1.text.toString().trim()
        sra_a_2 = ra_a_2.text.toString().trim()
        sra_a_3 = ra_a_3.text.toString().trim()
        sra_a_4 = ra_a_4.text.toString().trim()
        sra_b_1 = ra_b_1.text.toString().trim()
        sra_b_2 = ra_b_2.text.toString().trim()
        sra_b_3 = ra_b_3.text.toString().trim()
        sra_b_4 = ra_b_4.text.toString().trim()
        sra_b_5 = ra_b_5.text.toString().trim()
        sra_c_e = ra_c_e.text.toString().trim()
        sra_c_v = ra_c_v.text.toString().trim()
        sra_c_g = ra_c_g.text.toString().trim()
        sra_c_p = ra_c_p.text.toString().trim()
        sra_c_h = ra_c_h.text.toString().trim()
        srb_c_j = rb_c_j.text.toString().trim()
        sra_e_1 = ra_e_1.text.toString().trim()
        sra_e_2 = ra_e_2.text.toString().trim()
        sra_e_3 = ra_e_3.text.toString().trim()

        SRPD2 = RPD2.text.toString().trim()

        stvla2 = tvla2.text.toString().trim()

        stvlo2 = tvlo2.text.toString().trim()
        surlt2 = urlt2.text.toString().trim()
        sCountry1 = countryT1.text.toString().trim()


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

        var user = Report_r24(userID, SRPD2, sra_e_2, ImageUri.toString(), surlt2 ,stvla2,stvlo2,dateTime,sCountry1)

        //    database.child("imageUrl").setValue(ImageUri.toString())
        //  database.child("Report Road Damages").child("imageUrl").setValue(ImageUri.toString()).child(userID).child("Question1").setValue(user)
        //  database.child("Report Road Damages").child(userID).child("Question1").setValue(user)

        database.child("REPORT POWER NETWORK DAMAGES").child(userID).child("Question3").setValue(user)

            .addOnCompleteListener {
                Toast.makeText(this, "Report successfully", Toast.LENGTH_LONG).show()
            }.addOnFailureListener { err ->
                Toast.makeText(this, "ERROR ${err.message}", Toast.LENGTH_LONG).show()
            }

    }
    private fun saveData18() {

        sra_a_1 = ra_a_1.text.toString().trim()
        sra_a_2 = ra_a_2.text.toString().trim()
        sra_a_3 = ra_a_3.text.toString().trim()
        sra_a_4 = ra_a_4.text.toString().trim()
        sra_b_1 = ra_b_1.text.toString().trim()
        sra_b_2 = ra_b_2.text.toString().trim()
        sra_b_3 = ra_b_3.text.toString().trim()
        sra_b_4 = ra_b_4.text.toString().trim()
        sra_b_5 = ra_b_5.text.toString().trim()
        sra_c_e = ra_c_e.text.toString().trim()
        sra_c_v = ra_c_v.text.toString().trim()
        sra_c_g = ra_c_g.text.toString().trim()
        sra_c_p = ra_c_p.text.toString().trim()
        sra_c_h = ra_c_h.text.toString().trim()
        srb_c_j = rb_c_j.text.toString().trim()
        sra_e_1 = ra_e_1.text.toString().trim()
        sra_e_2 = ra_e_2.text.toString().trim()
        sra_e_3 = ra_e_3.text.toString().trim()

        SRPD2 = RPD2.text.toString().trim()

        stvla2 = tvla2.text.toString().trim()

        stvlo2 = tvlo2.text.toString().trim()
        surlt2 = urlt2.text.toString().trim()
        sCountry1 = countryT1.text.toString().trim()


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

        var user = Report_r24(userID, SRPD2, sra_e_3, ImageUri.toString(), surlt2 ,stvla2,stvlo2,dateTime,sCountry1)

        //    database.child("imageUrl").setValue(ImageUri.toString())
        //  database.child("Report Road Damages").child("imageUrl").setValue(ImageUri.toString()).child(userID).child("Question1").setValue(user)
        //  database.child("Report Road Damages").child(userID).child("Question1").setValue(user)

        database.child("REPORT POWER NETWORK DAMAGES").child(userID).child("Question3").setValue(user)

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

            binding.fireBaseImage1.setImageURI(null)
            Toast.makeText(this@Report_Power, "Successfully uploaded", Toast.LENGTH_SHORT).show()
            if (progressDialog.isShowing) progressDialog.dismiss()

        }.addOnFailureListener {
            if (progressDialog.isShowing) progressDialog.dismiss()
            Toast.makeText(this@Report_Power, "Failed", Toast.LENGTH_SHORT).show()

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
            uploadProgressBar2.visibility = View.VISIBLE
            val imageStorageRootReference = storage.reference
            val imageCollectionReference = imageStorageRootReference.child("images")
            val imageFileReference = imageCollectionReference.child(imageFileName!!)
            val downloadURL = imageFileReference.downloadUrl
            val remoteURL = downloadURL.toString()



            imageFileReference.putFile(ImageUri!!).addOnCompleteListener {
                Snackbar.make(mainView, "Image uploaded!", Snackbar.LENGTH_LONG).show()
                uploadProgressBar2.visibility = View.GONE
                Log.d(TAG, "Download URL = " + ImageUri.toString())

                imageFileReference.downloadUrl.addOnSuccessListener {

                    urlt2.text = it.toString()


                   /// database.child("imageUrl").setValue(it.toString())


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
                    uploadProgressBar2.visibility = View.GONE
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
        val geocoder = Geocoder(this@Report_Power, Locale.getDefault())
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

                        tvla2.text = latitude.toString()
                        tvlo2.text = longitude.toString()
                        // CountryT.text = address
                        countryName1 = adress.get(0).countryName
                        countryT1.text = countryName1
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







}














