package com.vokasi.countryapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vokasi.countryapp.databinding.ActivityMainBinding

class MainActivity :
    AppCompatActivity(),
    DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var provinces: Array<String>

    private val countries = arrayOf(
        "Indonesia",
        "United States",
        "United Kingdom",
        "Germany",
        "France",
        "Australia",
        "Japan",
        "China",
        "Brazil",
        "Canada"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        provinces = resources.getStringArray(R.array.provinces)

        with(binding) {

            // Spinner Country
            val adapterCountry = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_spinner_item,
                countries
            )

            adapterCountry.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
            )

            spinnerCountry.adapter = adapterCountry

            spinnerCountry.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {

                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        Toast.makeText(
                            this@MainActivity,
                            countries[position],
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    override fun onNothingSelected(
                        parent: AdapterView<*>
                    ) {
                    }
                }

            // Spinner Province
            val adapterProvinces = ArrayAdapter(
                this@MainActivity,
                android.R.layout.simple_spinner_item,
                provinces
            )

            adapterProvinces.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
            )

            spinnerProvinces.adapter = adapterProvinces

            // Date Picker Dialog
            btnShowCalendar.setOnClickListener {

                val datePicker = DatePicker()

                datePicker.show(
                    supportFragmentManager,
                    "datePicker"
                )
            }

            // Time Picker Dialog
            btnShowTimePicker.setOnClickListener {

                val timePicker = TimePicker()

                timePicker.show(
                    supportFragmentManager,
                    "timePicker"
                )
            }

            btnShowAlertDialog.setOnClickListener {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Keluar")

                builder.setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
                builder.setPositiveButton("Ya") { dialog, which ->
                    finish()
                }
                builder.setNegativeButton("Tidak") { dialog, _ ->
                    dialog.dismiss()
                }

                val dialog = builder.create()
                dialog.show()
            }

            btnShowCustomDialog.setOnClickListener {
                val dialog = DialogExit()
                dialog.show(supportFragmentManager, "dialogExit")
            }
        }
    }

    // Hasil Date Picker
    override fun onDateSet(
        p0: android.widget.DatePicker?,
        p1: Int,
        p2: Int,
        p3: Int
    ) {

        val selectedDate = "$p3/${p2 + 1}/$p1"

        Toast.makeText(
            this@MainActivity,
            selectedDate,
            Toast.LENGTH_SHORT
        ).show()
    }

    // Hasil Time Picker
    override fun onTimeSet(
        p0: android.widget.TimePicker?,
        p1: Int,
        p2: Int
    ) {

        val selectedTime = String.format(
            "%02d:%02d",
            p1,
            p2
        )

        Toast.makeText(
            this@MainActivity,
            selectedTime,
            Toast.LENGTH_SHORT
        ).show()
    }
}