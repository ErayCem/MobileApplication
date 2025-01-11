package com.example.loginapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.model.Recipe

class RecipeAdapter(private val recipeList: List<Recipe>) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private var onItemClickListener: ((Recipe) -> Unit)? = null

    // ViewHolder sınıfı
    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeImage: ImageView = itemView.findViewById(R.id.recipeImage)
        val recipeName: TextView = itemView.findViewById(R.id.recipeName)
        val recipeDescription: TextView = itemView.findViewById(R.id.recipeDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentRecipe = recipeList[position]

        // Görselleri ve metinleri yerleştir
        holder.recipeImage.setImageResource(currentRecipe.image)
        holder.recipeName.text = currentRecipe.name
        holder.recipeDescription.text = currentRecipe.description

        // Item tıklama olayları
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(currentRecipe)
        }
    }

    override fun getItemCount() = recipeList.size

    // Tıklama olayını set etmek için
    fun setOnItemClickListener(listener: (Recipe) -> Unit) {
        onItemClickListener = listener
    }
}
