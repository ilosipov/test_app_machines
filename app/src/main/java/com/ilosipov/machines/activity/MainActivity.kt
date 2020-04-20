package com.ilosipov.machines.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ilosipov.machines.R
import com.ilosipov.machines.adapter.MachineAdapter
import com.ilosipov.machines.model.Machine
import com.ilosipov.machines.network.RequestLathe

/**
 * Класс MainActivity - стартовая активити
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 20.04.2020
 * @version $Id$
 */

class MainActivity : AppCompatActivity() {
    private val log = "MainActivity"

    private val callback = MutableLiveData<List<Machine>>()
    private val requestLathe = RequestLathe()

    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        initData()
    }

    private val response : MutableLiveData<List<Machine>> get() = callback

    private fun initData() {
        requestLathe.getLatheMachine(callback)
        response.observe(this, Observer {
                machines: List<Machine> -> Log.d(log, "$machines")

            val adapter = MachineAdapter(this, R.layout.view_machine, machines)
            adapter.setListener(object : MachineAdapter.Listener {
                override fun onClick(position: Int) {
                    startActivity(Intent(this@MainActivity, DetailActivity::class.java))
                }
            })

            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter
        })
    }
}
