package com.uc.week2visprog_0706012110003

import GlobalVar.DatabaseHewan
import Interface.CardListener
import Adapter.ListHewanRVAdapter
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.uc.week2visprog_0706012110003.databinding.ActivityHomeBinding
import com.uc.week2visprog_0706012110003.databinding.ActivityMainBinding
import com.uc.week2visprog_0706012110003.databinding.ListCardBinding
import java.util.jar.Manifest

class HomeActivity : AppCompatActivity(), CardListener {

    private lateinit var binding: ActivityHomeBinding
    private val adapter = ListHewanRVAdapter(DatabaseHewan.listDataHewan, this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        setupRecyclerView()
        checkPermissions()
        textHidden()

        binding.floatingActionButton2.setOnClickListener{
            val myIntent = Intent(this, RegisterActivity::class.java)
            myIntent.putExtra("Title", "Tambah Hewan")
            startActivity(myIntent)
        }

    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(baseContext, 1)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), DatabaseHewan.STORAGE_PERMISSION_CODE)
        } else {
            Toast.makeText(this, "Storage Permission already granted", Toast.LENGTH_SHORT).show()
        }

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), DatabaseHewan.CAMERA_PERMISSION_CODE)
        } else {
            Toast.makeText(this, "Camera Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == DatabaseHewan.CAMERA_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Camera Permission Denied", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == DatabaseHewan.STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun textHidden() {
        binding.datakosongTextView.isVisible = DatabaseHewan.listDataHewan.isEmpty()
    }


    override fun onEditClicked(position: Int) {
        val myIntent = Intent(this, RegisterActivity::class.java).apply {
            putExtra("position", position)
            putExtra("Title", "Update Hewan")
        }
        startActivity(myIntent)
    }

    override fun onDeleteClicked(position: Int) {
        MaterialAlertDialogBuilder(this)
            .setTitle("Hapus Hewan")
            .setMessage("Are You Sure You Want to Delete This Animal?")
            .setNegativeButton("Cancel") { dialog, which->
            }
            .setPositiveButton("Agree") { dialog, which ->
                DatabaseHewan.listDataHewan.removeAt(position)
                Toast.makeText(baseContext, "Animal Deleted", Toast.LENGTH_LONG).show()
                onResume()
            }
            .show()
    }

}