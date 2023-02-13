package com.ryankoech.krypto.common.core.ktx

fun Any?.isNull() : Boolean {
    return this == null
}

fun Any?.isNotNull() : Boolean {
    return this != null
}