package com.example.loginapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.model.Recipe
import com.example.loginapp.RecipeAdapter

class RecipeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_recipe)

        // Recipe listesi
        val recipeList = listOf(
            Recipe(1, "Spaghetti", R.drawable.spagetti, "A delicious spaghetti recipe."),
            Recipe(2, "Pizza", R.drawable.pizza, "A classic pizza recipe."),
            Recipe(3, "Salad", R.drawable.salad, "A healthy salad recipe.")
        )

        // RecyclerView'ı bul
        val recyclerView: RecyclerView = findViewById(R.id.recipeRecyclerView)

        // LayoutManager'ı ayarla
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Adapter'ı oluştur
        val adapter = RecipeAdapter(recipeList)

        // Adapter'ı RecyclerView'a bağla
        recyclerView.adapter = adapter

        // RecyclerView item tıklama olayları (optional: log veya toast ile test edebilirsiniz)
        adapter.setOnItemClickListener { recipe ->
            Toast.makeText(this, "Clicked: ${recipe.name}", Toast.LENGTH_SHORT).show()
        }

        // Sistem çubuğu için padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.recipeRecyclerView)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
