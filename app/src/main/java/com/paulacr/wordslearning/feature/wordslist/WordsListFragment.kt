package com.paulacr.wordslearning.feature.wordslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dgreenhalgh.android.simpleitemdecoration.grid.GridDividerItemDecoration
import com.paulacr.wordslearning.R
import com.paulacr.wordslearning.data.ImageWord
import com.paulacr.wordslearning.data.TextWord
import com.paulacr.wordslearning.data.buildWordItems
import com.paulacr.wordslearning.databinding.FragmentWordsListBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordsListFragment : Fragment() {

    private val viewModel by viewModel<WordsListViewModel>()
    private lateinit var binding: FragmentWordsListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_words_list, container, false)
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupWordsList()
    }

    private fun setupWordsList() {
        binding.wordsList.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 2)
            adapter = WordsListAdapter(buildWordItems(mockedTextData, mockedImageData))
            addItemDecoration(GridDividerItemDecoration(getDivider(context), getDivider(context), 2))
        }
    }

    private fun getDivider(context: Context) =
        ContextCompat.getDrawable(context, R.drawable.grid_divider)

    private val mockedTextData = arrayOf(
        TextWord("first name", "translation 1"),
        TextWord("second name", "translation 2"),
        TextWord("third name", "translation 3")
    )

    private val mockedImageData = arrayOf(
        ImageWord(0, R.drawable.ic_tick, "xxx", "yyyy"),
        ImageWord(1, android.R.mipmap.sym_def_app_icon, "xxx", "yyyy"),
        ImageWord(2, R.drawable.ic_tick, "xxx", "yyyy")
    )
}
