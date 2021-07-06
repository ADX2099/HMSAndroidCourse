package com.adx2099.frag_perm_intents

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.adx2099.frag_perm_intents.databinding.FragmentIntentsBinding

class IntentsFragment : Fragment() {
    private var _binding: FragmentIntentsBinding? = null
    private val binding get() = _binding!!
    var condicion:Int = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentIntentsBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.btnNavegador.setOnClickListener {
            val webpage: Uri = Uri.parse("https://developer.huawei.com/en/")
            val intent = Intent(Intent.ACTION_VIEW, webpage)
            startActivity(intent)
        }
        binding.btnCall.setOnClickListener{
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:55445565898")
            }
            startActivity(intent)
        }
        binding.btnPetalSerach.setOnClickListener {
            val intent = Intent(Intent.ACTION_WEB_SEARCH).apply {
                putExtra(SearchManager.QUERY, "Petal Search")
            }
                startActivity(intent)

        }
        //TODO: 9 Agrega el siguiente codigo para poder ir hacia el el otro fragment.
        /*
        *
        * claro que lo podrias hacer de esta manera tambien
        * Navigation.findNavController(view).navigate(R.id.action_intentsFragment_to_permissionFragment)
        * En este codigo en especial estas utilizando extensions
        * view.findNavController().navigate(R.id.action_intentsFragment_to_permissionFragment)
        * */
        binding.permiButton.setOnClickListener (
            //finalmente para que sea mas legible y utilizando ktx lo resolvemos asi
            Navigation.createNavigateOnClickListener(R.id.action_intentsFragment_to_permissionFragment))
        //TODO: 10 Prueba tu app y fijate como haces la navegacion
        //TODO: 11 Ahora Crea un nuevo fragment y llamalo fragment condicional
        //TODO: 12 Agrega un nuevo boton que se llame condicional a tu layout
        //TODO: 13 crea una funcion que regrese un entero el cual declararas al inicio y modificarmos

        binding.condicionalButton.setOnClickListener { view: View ->
            if(aDondeIr() == 1){
                view.findNavController().navigate(R.id.action_intentsFragment_to_permissionFragment)
            }else{
                view.findNavController().navigate(R.id.action_intentsFragment_to_condicionalFragment)
            }

        }

        //TODO Agregamos el menu
        setHasOptionsMenu(true)

        return view
    }

    private fun aDondeIr(): Int {
        return condicion
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!, view!!.findNavController()) || super.onOptionsItemSelected(item)
    }
}