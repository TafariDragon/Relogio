package com.example.relogio

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.relogio.databinding.ActivityFullscreenBinding

class FullscreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullscreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
        {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        var carga=0
        val bateriaReceiver:BroadcastReceiver= object : BroadcastReceiver()
        {

            override fun onReceive(context: Context?, intent: Intent?) {
               if(intent !=null)
               {
                   val nivel: Int= intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0)
                   carga=nivel
                   binding.txtNivelBateria.setText(carga.toString()+"% ")

               }
            }
        }

        registerReceiver(bateriaReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))


             binding.checkBoox.setOnClickListener {
                 if(binding.checkBoox.isChecked)
                 {
                     binding.txtNivelBateria.setText(carga.toString()+"% ")

                 }else{
                     binding.txtNivelBateria.setText("")
                 }
             }

             binding.layoutMenu.animate().translationY(500F)

             binding.btnExit.setOnClickListener{
                 binding.layoutMenu.visibility=View.GONE
                 binding.layoutMenu.animate().translationY(binding.layoutMenu.measuredHeight.toFloat()).setDuration(
                     resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
                 )

             }

             binding.btnConfig.setOnClickListener {
                    binding.layoutMenu.visibility=View.VISIBLE
                    binding.layoutMenu.animate().translationY(0F).setDuration(
                        resources.getInteger(android.R.integer.config_mediumAnimTime).toLong()
                    )
             }

        }

}