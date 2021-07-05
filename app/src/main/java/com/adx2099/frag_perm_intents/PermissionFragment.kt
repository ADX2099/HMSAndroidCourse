package com.adx2099.frag_perm_intents

import android.Manifest
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.adx2099.frag_perm_intents.databinding.FragmentPermissionBinding


class PermissionFragment : Fragment(), DataReceiver.DataListener{

    companion object{
        const val CAMERA_REQUEST_CODE=100
    }

    private var receiver:DataReceiver?=null
    private var dataInterface:DataInterface?=null

    private var _binding: FragmentPermissionBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentPermissionBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.btnLocation.setOnClickListener {

        }

        binding.btnCamara.setOnClickListener { prepareCamera() }

        return view
    }

    private fun prepareCamera() {
        if(checkCameraPermission()){
            //Haz algo con la camara
        }
        else{
            requestCameraPermission()
        }
    }

    private fun checkCameraPermission(): Boolean {
        val camera=ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.CAMERA)
        return camera==PackageManager.PERMISSION_GRANTED
    }

    private fun sendData() {
        val bundle=Bundle().apply {
            putString("dato1","Mensaje de regreso")
        }
        dataInterface?.sendData(bundle)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        receiver=DataReceiver().apply {
            myDataListener=this@PermissionFragment
        }
        receiver?.register(requireContext())
    }

    private fun requestCameraPermission() {
        requestPermissions(arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CAMERA_REQUEST_CODE ->{
                if(checkCameraPermission()){
                    //haz algo con la camara
                }else{
                    AlertDialog.Builder(requireContext())
                        .setTitle("Mensaje")
                        .setMessage("Por favor acepta el permiso de camara")
                        .setCancelable(false)
                        .setPositiveButton("OK"){dialogInterface,_ ->
                            dialogInterface.dismiss()
                        }
                        .create().show()
                }
            }

            else ->{}
        }

    }

    override fun onDataReceived(bundle: Bundle) {
        for(key in bundle.keySet()){
            Log.e("Data","$key dato: ${bundle.get(key)}")
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is DataInterface) dataInterface=context
    }

    override fun onDestroy() {
        super.onDestroy()
        requireContext().unregisterReceiver(receiver)
    }

    interface DataInterface{
        fun sendData(data:Bundle)
    }
}