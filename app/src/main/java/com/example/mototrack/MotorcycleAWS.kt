package com.example.mototrack

import com.amplifyframework.core.model.Model

class MotorcycleAWS(/*mImageResource: Int,*/ ID: String, vin: String, mileage: Int, motoBrand: String, motoModel: String) :
    Model {
        private var image = R.drawable.ic_oldtimer_motorcycle_mechanic
        private var mID : String = ID
        private var mvin : String = vin
        private var mmileage : Int = mileage
        private var mmotoBrand : String = motoBrand
        private var mmotoModel : String = motoModel

    constructor() : this("",
        "",
        0,
        "",
        ""
    )

    fun getImage() : Int {
        return image
    }

    fun getID() : String {
        return mID
    }

    fun getVIN() : String {
        return mvin
    }

    fun getMileage() : Int {
        return mmileage
    }

    fun getMotoBrand() : String {
        return mmotoBrand
    }

    fun getMotoModel() : String {
        return mmotoModel
    }
}

