package com.example.androidapp.data.dto

data class ErreserbaSortuDto(
    val bezeroIzena: String,
    val telefonoa: String,
    val pertsonaKopurua: Int,
    val egunaOrdua: String, // Format: 2024-01-20T14:30:00
    val prezioTotala: Double,
    val fakturaRuta: String = "",
    val langileaId: Int,
    val mahaiakId: Int
)

data class ErreserbaSortuErantzuna(
    val mezua: String,
    val erreserbaId: Int
)
