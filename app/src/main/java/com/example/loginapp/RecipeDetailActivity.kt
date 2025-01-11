package com.example.loginapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecipeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_detail)

        // XML'deki View'ları bul
        val recipeImage = findViewById<ImageView>(R.id.recipeDetailImage)
        val recipeName = findViewById<TextView>(R.id.recipeDetailName)
        val recipeDescription = findViewById<TextView>(R.id.recipeDetailDescription)

        // Intent ile gelen verileri al
        val recipeId = intent.getIntExtra("RECIPE_ID", -1)
        val recipeNameText = intent.getStringExtra("RECIPE_NAME")
        val recipeImageRes = intent.getIntExtra("RECIPE_IMAGE", -1)
        val recipeDescriptionText = intent.getStringExtra("RECIPE_DESCRIPTION")

        // Verileri ekrana yerleştir
        recipeImage.setImageResource(recipeImageRes)
        recipeName.text = recipeNameText
        recipeDescription.text = recipeDescriptionText
    }
}
