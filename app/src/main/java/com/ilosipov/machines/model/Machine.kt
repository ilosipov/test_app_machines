package com.ilosipov.machines.model

import com.google.gson.annotations.SerializedName

/**
 * Класс Machine - модель данных станка
 * @author Ilya Osipov (mailto:il.osipov.gm@gmail.com)
 * @since 20.04.2020
 * @version $Id$
 */

data class Machine(
    @SerializedName("id")
    var id : Int = 0,
    @SerializedName("type")
    var type : String = "",
    @SerializedName("model")
    var model : String = "",
    @SerializedName("producer")
    var producer : String = "",
    @SerializedName("producingCountry")
    var country : String = "",
    @SerializedName("machineLocation")
    var location : String = "",
    @SerializedName("diameterMax")
    var diameterMax : Int = 0,
    @SerializedName("diameter")
    var diameter : Int = 0,
    @SerializedName("x")
    var lenght : Int = 0,
    @SerializedName("y")
    var width : Int = 0,
    @SerializedName("z")
    var depth : Int = 0,
    @SerializedName("video1")
    var photo : String = "") {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Machine

        if (id != other.id) return false
        if (type != other.type) return false
        if (model != other.model) return false
        if (producer != other.producer) return false
        if (country != other.country) return false
        if (location != other.location) return false
        if (diameterMax != other.diameterMax) return false
        if (diameter != other.diameter) return false
        if (lenght != other.lenght) return false
        if (width != other.width) return false
        if (depth != other.depth) return false
        if (photo != other.photo) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + type.hashCode()
        result = 31 * result + model.hashCode()
        result = 31 * result + producer.hashCode()
        result = 31 * result + country.hashCode()
        result = 31 * result + location.hashCode()
        result = 31 * result + diameterMax
        result = 31 * result + diameter
        result = 31 * result + lenght
        result = 31 * result + width
        result = 31 * result + depth
        result = 31 * result + photo.hashCode()
        return result
    }

    override fun toString(): String {
        return "Machine:\nid = $id,\ntype = $type,\nmodel = $model,\nproducer = $producer," +
                "\ncountry = $country,\nlocation = $location,\ndiameterMax = $diameterMax," +
                "\ndiameter = $diameter,\nlenght = $lenght,\nwidth = $width,\ndepth = $depth," +
                "\nphoto = $photo\n\n"
    }
}