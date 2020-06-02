package com.paulacr.wordslearning.feature.wordslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paulacr.wordslearning.R
import com.paulacr.wordslearning.data.ImageWord
import com.paulacr.wordslearning.data.TextWord
import com.paulacr.wordslearning.data.WordItem
import com.paulacr.wordslearning.data.WordItemType

private const val TEXT_WORD_TYPE = 0
private const val IMAGE_WORD_TYPE = 1

class WordsListAdapter(private val listItems: List<WordItem>) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return when (viewType) {
            WordItemType.TEXT_WORD_TYPE.value -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.text_word_item, parent, false)
                TextWordViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.image_word_item, parent, false)
                ImageWordViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int = listItems.count()

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        val item = listItems[position]
        when (holder) {
            is TextWordViewHolder -> holder.bind(item as TextWord)
            is ImageWordViewHolder -> holder.bind(item as ImageWord)
            else -> throw IllegalArgumentException()
        }
    }

    override fun getItemViewType(position: Int): Int {
        val comparable = listItems[position]
        return comparable.type.value
//        return when (comparable.type) {
//            WordItemType.TEXT_WORD_TYPE -> TEXT_WORD_TYPE
//            WordItemType.IMAGE_WORD_TYPE -> IMAGE_WORD_TYPE
//            else -> throw IllegalArgumentException("Invalid type at $position")
//        }
    }

    inner class TextWordViewHolder(itemView: View) : BaseViewHolder<TextWord>(itemView) {
        override fun bind(item: TextWord) {
            val name = itemView.findViewById<TextView>(R.id.name)
            val translation = itemView.findViewById<TextView>(R.id.translation)

            name.text = item.name
            translation.text = item.translation
        }
    }

    inner class ImageWordViewHolder(itemView: View) : BaseViewHolder<ImageWord>(itemView) {
        override fun bind(item: ImageWord) {
            val miniature = itemView.findViewById<ImageView>(R.id.miniature)
            val name = itemView.findViewById<TextView>(R.id.imageTextName)

            miniature.setImageResource(item.imageRes)
            name.text = item.name
        }
    }
}
