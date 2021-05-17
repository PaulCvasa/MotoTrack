package com.example.mototrack

class ExampleItem(/*mImageResource: Int,*/ s: String, s1: String, s2: String, s3: String) {
    private var image = R.drawable.ic_oldtimer_motorcycle_mechanic
    private var mText1 : String = s
    private var mText2 : String = s1
    private var mText3 : String = s2
    private var mText4 : String = s3

    constructor() : this("",
                        "",
                        "",
                        ""
    )

    fun getImageResource(): Int {
        return image
    }

    fun getText1(): String? {
        return mText1
    }

    fun getText2(): String? {
        return mText2
    }

    fun getText3(): String? {
        return mText3
    }

    fun getText4(): String? {
        return mText4
    }

    fun setImageResource() {
        image = R.drawable.ic_oldtimer_motorcycle_mechanic
    }

    fun setText1(s: String) {
        mText1 = s
    }

    fun setText2(s1: String) {
        mText1 = s1
    }

    fun setText3(s2: String) {
        mText1 = s2
    }

    fun setText4(s3: String) {
        mText1 = s3
    }

}