package com.example.marcos.okhttptest

/**
 * Created by Marcos on 12/11/2017.
 */

class Escola {
    //add ID
    private var escolaNome: String? = null
    private var longitude: String? = null
    private var latitude: String? = null

    constructor(escolaNome: String, longitude: String, latitude: String) {
        this.escolaNome = escolaNome
        this.longitude = longitude
        this.latitude = latitude
    }

    constructor()

    fun getescolaNome(): String? {
        return this.escolaNome
    }

    fun setescolaNome(s : String) {
        this.escolaNome = s
    }

    fun getlongitude(): String? {
        return this.longitude
    }

    fun setlongitude(s : String) {
        this.longitude = s
    }

    fun getlatitude(): String? {
        return this.latitude
    }

    fun setlatitude(s : String) {
        this.latitude = s
    }

}
