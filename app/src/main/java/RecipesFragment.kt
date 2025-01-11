import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.widget.SearchView
import com.example.loginapp.R
import com.example.loginapp.RecipeAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RecipesFragment : Fragment(R.layout.fragment_recipes) {

    private lateinit var viewModel: RecipesViewModel
    private lateinit var adapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_recipes, container, false)

        viewModel = ViewModelProvider(this).get(RecipesViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // ViewModel'den gelen tarifleri başlangıçta boş bir liste ile veriyoruz
        adapter = RecipeAdapter(emptyList()) // Boş bir listeyle başlatıyoruz
        recyclerView.adapter = adapter

        val searchView = view.findViewById<SearchView>(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { viewModel.searchRecipes(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { viewModel.searchRecipes(it) }
                return true
            }
        })

        lifecycleScope.launch {
            viewModel.recipes.collect { recipes ->
                // ViewModel'den gelen tarife listesini adapte ediyoruz
                adapter = RecipeAdapter(recipes) // Burada yeni listeyi geçiyoruz
                recyclerView.adapter = adapter
            }
        }

        return view
    }
}
