package com.adx2099.frag_perm_intents

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle

class DataReceiver: BroadcastReceiver() {
    var myDataListener:DataListener?=null

    companion object{
        const val ACTION_SHARE_DATA="com.adx2099.share_data"
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        val bundle=intent?.extras
        bundle?.let{
            myDataListener?.onDataReceived(it)
        }
    }

    fun register(context: Context){
        val filter=IntentFilter(ACTION_SHARE_DATA)
        context.registerReceiver(this,filter)
    }



    interface DataListener{
        fun onDataReceived(bundle: Bundle)

    }
}