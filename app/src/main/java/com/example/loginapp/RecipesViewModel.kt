package com.example.loginapp

import androidx.lifecycle.ViewModel
import com.example.loginapp.model.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class RecipesViewModel : ViewModel() {

    // Tüm tariflerin listesi
    private val allRecipes = listOf(
        Recipe(1, "Recipe 1", R.drawable.pizza, "Delicious recipe"),
        Recipe(2, "Recipe 2", R.drawable.salad, "Tasty recipe"),
        Recipe(3, "Recipe 3", R.drawable.spagetti, "Yummy recipe")
    )

    // UiState'i tutan StateFlow
    private val _uiState = MutableStateFlow(UiState())  // Başlangıçta boş bir UiState gönderiyoruz
    val uiState: StateFlow<UiState> = _uiState

    // Arama sorgusunu işleyen fonksiyon
    fun searchRecipes(query: String) {
        if (query.length < 3) return // 1 veya 2 karakterli sorguları geç

        // Yükleme durumunu başlat
        _uiState.value = UiState(isLoading = true)

        // Simülasyon: Arama işlemi 2 saniye sürüyor
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)  // 2 saniye bekleyelim (Yükleniyor simülasyonu)

            // Başlık veya açıklamada sorguyu içeren tarifleri filtrele
            val filteredRecipes = allRecipes.filter {
                it.name.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true)
            }

            // Filtrelenmiş tarifleri StateFlow'a gönder ve yüklenmeyi bitir
            _uiState.value = UiState(
                isLoading = false,  // Yüklenme bitti
                recipes = filteredRecipes
            )
        }
    }
}
