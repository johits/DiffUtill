package com.example.test0908

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.test0908.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val keywordAdapter = KeywordAdapter()
    private val keyword = ArrayList<Keyword>()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.rv.adapter = keywordAdapter

        binding.btnAdd.setOnClickListener {
            var word = binding.etText.text.toString()
            keyword.add(Keyword(word))
            keywordAdapter.submitList(keyword.toMutableList())
            binding.etText.setText("")
        }

    }
}

