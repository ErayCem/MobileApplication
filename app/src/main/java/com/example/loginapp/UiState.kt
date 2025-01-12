package com.example.loginapp

import com.example.loginapp.model.Recipe

// UiState sınıfı, yüklenme durumunu ve tarifleri tutar
data class UiState(
    val isLoading: Boolean = false,  // Yükleniyor durumu
    val recipes: List<Recipe> = emptyList(),  // Tarifler
    val error: String? = null  // Hata mesajı (opsiyonel)
)
