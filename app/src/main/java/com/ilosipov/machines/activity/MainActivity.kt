package com.ilosipov.machines.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
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
    private lateinit var recyclerView : RecyclerView
    private lateinit var progressBar : ProgressBar
    private lateinit var emptyText : TextView
    private val requestLathe = RequestLathe()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(log, "onCreate: initialization MainActivity.")

        recyclerView = findViewById(R.id.recycler_view)
        progressBar = findViewById(R.id.progress_bar)
        emptyText = findViewById(R.id.empty_text)
        initData()
    }

    private val response : MutableLiveData<List<Machine>> get() = callback

    private fun initData() {

        requestLathe.getLatheMachine(callback)
        response.observe(this, Observer {
                machines: List<Machine> ->
            val adapter = MachineAdapter(this, R.layout.view_machine, machines)
            adapter.setListener(object : MachineAdapter.Listener {
                override fun onClick(position: Int) {
                    startActivity(Intent(this@MainActivity, DetailActivity::class.java)
                        .putExtra("machine_bundle", Bundle().apply {
                            putString("type", machines[position].type)
                            putInt("width", machines[position].width)
                            putInt("depth", machines[position].depth)
                            putInt("lenght", machines[position].lenght)
                            putString("model", machines[position].model)
                            putString("image", machines[position].photo)
                            putString("exist", machines[position].exist)
                            putString("location", machines[position].location)
                            putString("producer", machines[position].producer)
                            putString("producing", machines[position].country)
                            putString("condition", machines[position].condition)
                        }))
                }
            })
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter

            recyclerView.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
            emptyText.visibility = View.GONE
        })
    }
}
