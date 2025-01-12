package com.example.loginapp

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.loginapp.model.Recipe

class RecipeAdapter(private var recipeList: List<Recipe>) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private var onItemClickListener: ((Recipe) -> Unit)? = null

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeImage: ImageView = itemView.findViewById(R.id.recipeImage)
        val recipeName: TextView = itemView.findViewById(R.id.recipeName)
        val recipeDescription: TextView = itemView.findViewById(R.id.recipeDescription)
        val recipeButton: TextView = itemView.findViewById(R.id.recipeButton)
        val shareButton: TextView = itemView.findViewById(R.id.shareButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currentRecipe = recipeList[position]

        holder.recipeImage.setImageResource(currentRecipe.image)
        holder.recipeName.text = currentRecipe.name
        holder.recipeDescription.text = currentRecipe.description

        holder.itemView.setOnClickListener {
            val context = it.context
            val intent = Intent(context, RecipeDetailActivity::class.java)

            intent.putExtra("RECIPE_ID", currentRecipe.id)
            intent.putExtra("RECIPE_NAME", currentRecipe.name)
            intent.putExtra("RECIPE_IMAGE", currentRecipe.image)
            intent.putExtra("RECIPE_DESCRIPTION", currentRecipe.description)

            context.startActivity(intent)
        }

        holder.recipeButton.setOnClickListener {
            onButtonClicked(currentRecipe)
            Toast.makeText(it.context, "Button clicked: ${currentRecipe.name} - Like", Toast.LENGTH_SHORT).show()
            Log.d("RecipeAdapter", "Button clicked: ${currentRecipe.name} - Like")
        }

        holder.shareButton.setOnClickListener {
            onButtonClicked(currentRecipe)
            Toast.makeText(it.context, "Button clicked: ${currentRecipe.name} - Share", Toast.LENGTH_SHORT).show()
            Log.d("RecipeAdapter", "Button clicked: ${currentRecipe.name} - Share")
        }
    }

    override fun getItemCount() = recipeList.size

    // Tarifleri güncellemek için fonksiyon
    fun updateRecipes(newRecipes: List<Recipe>) {
        recipeList = newRecipes
        notifyDataSetChanged() // Listeyi güncelledikten sonra RecyclerView'u bilgilendir
    }

    // Tıklama olaylarını set etmek için bir fonksiyon
    fun setOnItemClickListener(listener: (Recipe) -> Unit) {
        onItemClickListener = listener
    }

    private fun onButtonClicked(recipe: Recipe) {
        // Butona tıklama işlevi burada tanımlanabilir
    }
}
