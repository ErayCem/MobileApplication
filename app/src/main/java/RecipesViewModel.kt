import androidx.lifecycle.ViewModel
import com.example.loginapp.R
import com.example.loginapp.model.Recipe
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class RecipesViewModel : ViewModel() {

    // Tüm tariflerin listesi, burada sabit veriler kullanıyoruz (örneğin, test verileri)
    private val allRecipes = listOf(
        Recipe(1, "Recipe 1", R.drawable.pizza, "Delicious recipe"),
        Recipe(2, "Recipe 2", R.drawable.salad, "Tasty recipe"),
        Recipe(3, "Recipe 3", R.drawable.spagetti, "Yummy recipe")
    )

    // Filtrelenmiş tarifleri tutacak StateFlow
    private val _recipes = MutableStateFlow<List<Recipe>>(emptyList())
    val recipes: StateFlow<List<Recipe>> = _recipes

    // Arama sorgusunu işleyen fonksiyon
    fun searchRecipes(query: String) {
        if (query.length < 3) return // 1 veya 2 karakterli sorguları geç

        // Başlık veya açıklamada sorguyu içeren tarifleri filtrele
        val filteredRecipes = allRecipes.filter {
            it.name.contains(query, ignoreCase = true) || it.description.contains(query, ignoreCase = true)
        }

        // Filtrelenmiş tarifleri StateFlow'a gönder
        _recipes.value = filteredRecipes
    }
}
