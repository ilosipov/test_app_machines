package com.ilosipov.machines.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ilosipov.machines.R
import com.ilosipov.machines.model.Machine
import com.squareup.picasso.Picasso

/**
 * Класс MachineAdapter - адаптер списка станков
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 20.04.2020
 * @version $Id$
 */

class MachineAdapter(private var context: Context,
                     private var resource: Int,
                     private var listMachine: List<Machine>) :
    RecyclerView.Adapter<MachineAdapter.MachineViewHolder>() {
    private lateinit var listener : Listener

    class MachineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MachineViewHolder =
        MachineViewHolder(LayoutInflater.from(context).inflate(resource, parent, false))

    override fun onBindViewHolder(holder: MachineViewHolder, position: Int) {
        val machine = listMachine[position]

        holder.itemView.setOnClickListener { listener.onClick(position) }

        val image = holder.itemView.findViewById<ImageView>(R.id.image_machine)
        if (machine.photo.isNotEmpty()) {
            Picasso.get().load(machine.photo).error(R.drawable.ic_error_image).into(image)
        }

        val nameMachine = holder.itemView.findViewById<TextView>(R.id.name_machine)
        nameMachine.text = machine.model
    }

    override fun getItemCount() : Int {
        return listMachine.size
    }

    interface Listener {
        fun onClick(position: Int)
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }
}