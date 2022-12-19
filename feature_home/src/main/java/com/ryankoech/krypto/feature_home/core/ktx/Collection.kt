package com.ryankoech.krypto.feature_home.core.ktx

fun Collection<Any>.getNextIndex(currentIndex : Int) : Int {

    if(currentIndex < 0 || currentIndex >= this.size)
        throw Exception("Provided Current Index is out of bounds")

    val nextIndex = currentIndex + 1

    return if(nextIndex >= this.size)
        0
    else
        nextIndex
}