package com.example.test0908

import android.view.ViewGroup
import com.example.test0908.databinding.RvItemBinding

//ListAdapter 만들어 줌
 class KeywordAdapter : BaseListAdapter<Keyword, RvItemBinding>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): KeywordViewHolder{
        return KeywordViewHolder(R.layout.rv_item, parent)
    }


}