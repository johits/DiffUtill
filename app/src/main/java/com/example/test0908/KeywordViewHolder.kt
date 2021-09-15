package com.example.test0908

import android.view.ViewGroup
import com.example.test0908.databinding.RvItemBinding

class KeywordViewHolder(
    layoutId: Int,
    parent: ViewGroup,
    private val listener: KeywordAdapter.KeyWordListener
) : BindViewHolder<Keyword, RvItemBinding>(layoutId, parent) {
    override fun bind(item: Keyword) {
        binding.apply {
            tvKeyword.text = item.word
        }
    }
}