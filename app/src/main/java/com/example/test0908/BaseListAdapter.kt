package com.example.test0908

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Bae-tory
 * 사용법
 * 1. BaseListAdapter를 상속하여 ChildAdapter(별칭) 클래스를 생성한다.
 *
 * 2. ChildAdapter에서는 onCreateViewHolder만 재정의 하면 된다.
 *    이 때, 뷰홀더 생성은 아래 `6번`을 참고 뷰홀더는 다른 클래스에 생성하는게 좋다.
 *
 * 3. 해당 어뎁터에 넘겨줄 DataModel을 생성한다.
 *
 * 4. list를 adapter.submitlist를 이용하여 set하고 이 때, 기존 list와는 다른 인스턴스여야 한다.
 *
 * 5. DiffModel를 상속하여 diffId를 재정의한다.
 *
 * 6. ListAdapter는 getItemCount가 없고 별도의 데이터를 생성하므로 클릭 이벤트는 getItem(bindingAdapterPoisition을 사용한다)
 *
 * 7. 뷰홀더는 BindViewHolder를 상속하여 생성한다.
 *
 * 8. binding과 context는 바로 get하여 사용하면 된다.
 * - 이외에 쓸만한 생명주기는 다음과 같다
 * @see BindViewHolder.onCreated - 뷰홀더가 생성될 때 호출
 * @see BindViewHolder.bind - 홀더에 데이터가 bind될 때 호출
 * @see BindViewHolder.onRecycled - 홀더가 재사용될 때 호출
 * @see BindViewHolder.onDetachedFromWindow - 홀더가 메모리에서 삭제될 때 호출
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseListAdapter<T : DiffModel, B : ViewDataBinding>(
    diffCallback: DiffUtil.ItemCallback<T> = BaseDiffCallback as DiffUtil.ItemCallback<T>,
) : androidx.recyclerview.widget.ListAdapter<T, BindViewHolder<T, B>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindViewHolder<T, B> {
        return BindViewHolder(viewType, parent)
    }

    override fun onBindViewHolder(holder: BindViewHolder<T, B>, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onViewAttachedToWindow(holder: BindViewHolder<T, B>) {
        super.onViewAttachedToWindow(holder)
        holder.onAttach()
    }

    override fun onViewRecycled(holder: BindViewHolder<T, B>) {
        super.onViewRecycled(holder)
        holder.onRecycled()
    }

    override fun onViewDetachedFromWindow(holder: BindViewHolder<T, B>) {
        super.onViewDetachedFromWindow(holder)
        holder.onDetachedFromWindow()
    }
}

open class BindViewHolder<T, B : ViewDataBinding>(
    @LayoutRes layoutId: Int,
    parent: ViewGroup,
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
), LifecycleOwner {

    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }
    val binding: B = DataBindingUtil.bind(itemView)!!
    protected val context: Context get() = binding.root.context

    /**
     * 뷰 사이즈 다르게 할 때 super.onCreated 반드시 호출
     */
    open fun onCreated() {
        adjustViewSizeByModel(binding)
    }

    open fun bind(item: T) {}
    open fun onRecycled() {}
    open fun onDetachedFromWindow() {}

    /**
     * 뷰홀더 생성될 때 호출
     */
    open fun adjustViewSizeByModel(binding: B) {}

    /**
     * this is for LiveData
     * @see LiveData
     * @see Lifecycle.State
     */
    fun onAttach() {
        lifecycleRegistry.currentState = Lifecycle.State.RESUMED
    }

    fun onDetach() {
        lifecycleRegistry.currentState = Lifecycle.State.CREATED
    }

    override fun getLifecycle(): Lifecycle = lifecycleRegistry
}
