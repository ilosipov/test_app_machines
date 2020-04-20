package com.ilosipov.machines.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.ilosipov.machines.R
import com.ilosipov.machines.model.Machine
import com.ilosipov.machines.network.RequestLathe

/**
 * Класс MainFragment - представления списка станков
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 20.04.2020
 * @version $Id$
 */

class MainFragment : Fragment() {
    private val log = "MainFragment"

    private val callback = MutableLiveData<List<Machine>>()
    private val requestLathe = RequestLathe()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?) : View? {
        Log.d(log, "onCreateView: initialization MainFragment.")
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        initData()
        return view
    }

    private val response : MutableLiveData<List<Machine>> get() = callback

    private fun initData() {
        requestLathe.getLatheMachine(callback)
        response.observe(viewLifecycleOwner, Observer {
                machine: List<Machine> -> Log.d(log, "$machine")
        })
    }
}