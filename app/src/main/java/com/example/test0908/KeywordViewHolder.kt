package com.example.test0908

import android.view.View
import android.view.ViewGroup
import com.example.test0908.databinding.RvItemBinding

class KeywordViewHolder(layoutId: Int, parent: ViewGroup) :
BindViewHolder<Keyword,RvItemBinding>(layoutId, parent){
    override fun  bind(item:Keyword){
        binding.tvKeyword.text =item.word

        //수정
        binding.tvKeyword.setOnClickListener {
            binding.tvKeyword.visibility = View.GONE
            binding.etKeyword.visibility = View.VISIBLE
            binding.etKeyword.setText(item.word)
        }
    }
}