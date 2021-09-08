package com.example.test0908

import androidx.recyclerview.widget.DiffUtil


object BaseDiffCallback : DiffUtil.ItemCallback<DiffModel>() {

    //두 개체가 동일한 항목을 나타내는지 여부를 결정하기 위해 DiffUtil에 의해 호출됩니다.
    override fun areItemsTheSame(oldItem: DiffModel, newItem: DiffModel): Boolean {
        return oldItem.diffId == newItem.diffId
    }

    //두 항목에 동일한 데이터가 있는지 확인하려는 경우 DiffUtil에 의해 호출됩니다.
    override fun areContentsTheSame(oldItem: DiffModel, newItem: DiffModel): Boolean {
        return oldItem == newItem
    }
}
