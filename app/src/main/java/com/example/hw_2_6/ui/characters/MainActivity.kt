package com.example.hw_2_6.ui.characters

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hw_2_6.databinding.ActivityMainBinding
import com.example.hw_2_6.recycler.CartoonAdapter
import com.example.hw_2_6.ui.characterDetails.SecondActivity
import com.example.hw_2_6.ui.utils.CartoonKeys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ViewModel by viewModels()
    private val cartoonAdapter by lazy { CartoonAdapter(this::onClickItem) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getCharacters().observe(this) {
            cartoonAdapter.submitList(it)
            setupCharactersRecycler()
        }
        //binding.recyclerView.adapter = cartoonAdapter
    }

    private fun setupCharactersRecycler() = with(binding.recyclerView){
        layoutManager = LinearLayoutManager(
            this@MainActivity,
            LinearLayoutManager.VERTICAL,
            false
        )
        adapter = cartoonAdapter
    }

    private fun onClickItem(characterId: Int) {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra(CartoonKeys.CHARACTER_ID_ARG, characterId)
        startActivity(intent)
    }
}