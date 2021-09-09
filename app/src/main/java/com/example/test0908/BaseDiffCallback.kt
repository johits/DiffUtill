package com.example.test0908

import androidx.recyclerview.widget.DiffUtil


object BaseDiffCallback : DiffUtil.ItemCallback<DiffModel>() {
    // 같은 항목인지 비교
    override fun areItemsTheSame(oldItem: DiffModel, newItem: DiffModel): Boolean {
        return oldItem.diffId == newItem.diffId
    }

    //같은 데이터인지 비교
    override fun areContentsTheSame(oldItem: DiffModel, newItem: DiffModel): Boolean {
        return oldItem == newItem
    }
}
