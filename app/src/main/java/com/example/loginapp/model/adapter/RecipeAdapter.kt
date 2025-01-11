package com.example.loginapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.model.Recipe

class RecipeAdapter(private val recipeList: List<Recipe>) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private var onItemClickListener: ((Recipe) -> Unit)? = null

    // ViewHolder sınıfı
    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeImage: ImageView = itemView.findViewById(R.id.recipeImage)
        val recipeName: TextView = itemView.findViewById(R.id.recipeName)
        val recipeDescription: TextView = itemView.findViewById(R.id.recipeDescription)
        val recipeButton: TextView = itemView.findViewById(R.id.recipeButton)  // Like/Share butonunu burada tanımlayın
        val shareButton: TextView = itemView.findViewById(R.id.shareButton)  // Share butonunu burada tanımlayın
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
            // Tıklanan öğe hakkında bilgi verir
            Toast.makeText(it.context, "Item clicked: ${currentRecipe.name}", Toast.LENGTH_SHORT).show()
            Log.d("RecipeAdapter", "Item clicked: ${currentRecipe.name}")
        }

        // "Like" butonuna tıklama olayları
        holder.recipeButton.setOnClickListener {
            onButtonClicked(currentRecipe)
            Toast.makeText(it.context, "Button clicked: ${currentRecipe.name} - Like", Toast.LENGTH_SHORT).show()
            Log.d("RecipeAdapter", "Button clicked: ${currentRecipe.name} - Like")
        }

        // "Share" butonuna tıklama olayları
        holder.shareButton.setOnClickListener {
            onButtonClicked(currentRecipe)
            Toast.makeText(it.context, "Button clicked: ${currentRecipe.name} - Share", Toast.LENGTH_SHORT).show()
            Log.d("RecipeAdapter", "Button clicked: ${currentRecipe.name} - Share")
        }
    }

    override fun getItemCount() = recipeList.size

    // Tıklama olayını set etmek için
    fun setOnItemClickListener(listener: (Recipe) -> Unit) {
        onItemClickListener = listener
    }

    // Buton tıklama işlevini ekleyin
    private fun onButtonClicked(recipe: Recipe) {
        // Burada butona basıldığında yapılacak işlemler tanımlanabilir
        // Örneğin, veritabanına kaydetme, bir işlem başlatma vb.
    }
}
