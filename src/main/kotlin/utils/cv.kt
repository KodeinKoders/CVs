package utils

typealias Name = String

fun Name.initials(): String = split(" ").joinToString("") { it[0].toString() }
