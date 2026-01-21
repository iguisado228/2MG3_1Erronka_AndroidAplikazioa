package com.example.androidapp.data.dto

data class ErreserbaDto(
    val id: Int,
    val bezeroIzena: String,
    val telefonoa: String,
    val pertsonaKopurua: Int,
    val egunaOrdua: String, // Using String for simplicity, can be parsed to Date/LocalDateTime
    val prezioTotala: Double,
    val ordainduta: Int,
    val fakturaRuta: String?,
    val langileaId: Int,
    val mahaiakId: Int
)
