package com.example.androidapp.presentation.erreserbak

import com.example.androidapp.data.dto.MahaiaDto

data class CreateReservationState(
    val customerName: String = "",
    val phone: String = "",
    val personCount: String = "", // String for input handling
    val date: String = "", // YYYY-MM-DD
    val time: String = "", // HH:mm
    val selectedTable: MahaiaDto? = null,
    val tables: List<MahaiaDto> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isSuccess: Boolean = false
)
