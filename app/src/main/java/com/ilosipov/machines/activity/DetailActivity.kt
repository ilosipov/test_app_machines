package com.ilosipov.machines.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ilosipov.machines.R
import com.squareup.picasso.Picasso

/**
 * Класс DetailActivity - отображает подробные данные по станку
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 20.04.2020
 * @version $Id$
 */

class DetailActivity : AppCompatActivity() {
    private lateinit var bundle : Bundle
    private lateinit var btnBasket : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        btnBasket = findViewById(R.id.btn_basket)
        btnBasket.setOnClickListener(this::onClickBtn)

        bundle = intent.getBundleExtra("machine_bundle")!!
        initActionBar()
        updateUI()
    }

    private fun initActionBar() {
        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true)
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.title = bundle.getString("type")
        }
    }

    private fun updateUI() {
        val icUsed = findViewById<ImageView>(R.id.ic_used)
        val image = findViewById<ImageView>(R.id.image_lathe)
        if (bundle.getString("image")!!.isNotEmpty()) {
            Picasso.get().load(bundle.getString("image"))
                .error(R.drawable.empty).into(image)
        } else {
            image.setImageResource(R.drawable.empty)
        }


        val model = findViewById<TextView>(R.id.model_text)
        model.text = bundle.getString("model")

        val location = findViewById<TextView>(R.id.location_text)
        location.text = String.format(getString(R.string.location_text), bundle.getString("location"))

        val  producing = findViewById<TextView>(R.id.producing_text)
        producing.text = String.format(getString(R.string.producing_text), bundle.getString("producing"))

        val producer = findViewById<TextView>(R.id.producer_text)
        producer.text = String.format(getString(R.string.producer_text), bundle.getString("producer"))

        val sizes = findViewById<TextView>(R.id.sizes_text)
        if (bundle.getInt("lenght") == 0
            || bundle.getInt("depth") == 0
            || bundle.getInt("width") == 0) {
            sizes.visibility = View.GONE
        } else {
            sizes.visibility = View.VISIBLE
            sizes.text = String.format(
                getString(R.string.sizes_text),
                bundle.getInt("lenght"),
                bundle.getInt("width"),
                bundle.getInt("depth")
            )
        }

        val condition = findViewById<TextView>(R.id.condition_text)
        if (bundle.getString("condition") == "used") {
            icUsed.visibility = View.VISIBLE
        } else {
            icUsed.visibility = View.INVISIBLE
        }

        condition.text = String.format(getString(R.string.condition_text), bundle.getString("condition"))

        val exist = findViewById<TextView>(R.id.exist_text)
        if (bundle.getString("exist") == null) {
            exist.text = String.format(getString(R.string.exist_text), getString(R.string.exist_null))
        } else {
            exist.text = String.format(getString(R.string.exist_text), bundle.getString("exist"))
        }
    }

    @Suppress("UNUSED_PARAMETER")
    private fun onClickBtn(v: View) {
        Toast.makeText(this, String.format(getString(R.string.toast_basket),
            bundle.getString("model")), Toast.LENGTH_SHORT).show()
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}