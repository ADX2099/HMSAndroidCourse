package com.adx2099.frag_perm_intents

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.adx2099.frag_perm_intents.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //TODO 3 Elimina las referencias a los botones que han sido eliminados
        //TODO 4 Agrega en el navigation graph el destino, en este ejemplo utilizaremos el fregment Intents
        //TODO: 5 Ejecuta tu App despues de os cambios ahora deberias de ver como tu app comienza conel fragment intents
        //TODO 14: Agregaremos el back up buton crea un val para tu val controller y asignalo con el id de tu nav host
        //TODO 15 Sobrescribe el metodo onSupportNavigate Up y agrega el codigo correspondiente

        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this,navController)
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return navController.navigateUp()
    }
}