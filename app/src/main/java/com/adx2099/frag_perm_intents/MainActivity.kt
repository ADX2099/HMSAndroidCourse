package com.adx2099.frag_perm_intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adx2099.frag_perm_intents.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), PermissionFragment.DataInterface {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        binding.btnActDos.setOnClickListener {
            val intent = Intent(this,MiSegundaActivity::class.java)
            startActivity(intent)
        }
    }

    override fun sendData(data: Bundle) {
        val fragment=supportFragmentManager.findFragmentById(R.id.fragmentIntents)
        if(fragment is IntentsFragment) fragment.receiveData(data)
    }

}