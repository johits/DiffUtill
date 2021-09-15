package com.example.test0908

import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.example.test0908.databinding.RvItemBinding


class KeywordAdapter(private val listener: KeyWordListener) :
    BaseListAdapter<Keyword, RvItemBinding>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeywordViewHolder =
        KeywordViewHolder(R.layout.rv_item, parent, listener)
            .apply {
                binding.apply {
                    // 수정
                    tvKeyword.setOnClickListener {
                        tvKeyword.visibility = View.GONE
                        etKeyword.visibility = View.VISIBLE
                        etKeyword.setText(getItem(bindingAdapterPosition).word)
                    }

                    etKeyword.doOnTextChanged { text, _, _, _ ->
                        listener.onEditTextChanged(text = text.toString(), bindingAdapterPosition)
                    }
                }
            }


    interface KeyWordListener {
        fun onEditTextChanged(text: String, position: Int)
    }
}