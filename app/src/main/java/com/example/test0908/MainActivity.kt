package com.example.test0908

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.test0908.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), KeywordAdapter.KeyWordListener {
    private val keywordAdapter = KeywordAdapter(this)
    private val keyword = ArrayList<Keyword>()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.rv.adapter = keywordAdapter

        binding.btnAdd.setOnClickListener {
            val word = binding.etText.text.toString()
            keyword.add(Keyword(word))
            keywordAdapter.submitList(keyword.toMutableList())
            binding.etText.setText("")
        }

        binding.btnDelete.setOnClickListener {
            keywordAdapter.submitList(
                keyword.map {
                    it.word.toInt()
                }
                    .filter {
                        it % 2 != 0
                    }
                    .map {
                        Keyword(it.toString())
                    })
        }
        binding.btnNotify.setOnClickListener {
            keywordAdapter.submitList(keyword.toMutableList())
        }
    }

    // 뷰홀더 내의 edittext가 변경될 때 호출됨
    override fun onEditTextChanged(text: String, position: Int) {
        Log.d("테스트", "text: $text, position: $position")
        keyword[position] = Keyword(word = text)

        // 1. map을 통해 데이터 변경

    }
}

