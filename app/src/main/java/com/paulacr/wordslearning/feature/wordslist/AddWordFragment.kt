package com.paulacr.wordslearning.feature.wordslist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.paulacr.wordslearning.data.TextWord
import com.paulacr.wordslearning.databinding.FragmentAddWordBinding
import com.paulacr.wordslearning.feature.translation.TranslationState

class AddWordFragment : Fragment() {

    private val viewModel by viewModels<AddWordViewModel>()
    private lateinit var binding: FragmentAddWordBinding
    private lateinit var observerWordItems: Observer<List<TextWord>>
    private lateinit var stateObserver: Observer<TranslationState>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
    }

    private fun setupObserver() {
        stateObserver = Observer {
            when (it) {

                TranslationState.FINISHED -> {
                    findNavController().popBackStack()
                }
                TranslationState.ERROR -> {
                    // show snack bar with error
                }
            }
        }
        viewModel.state.observe(viewLifecycleOwner, stateObserver)
    }

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        binding =
//            DataBindingUtil.inflate(inflater, R.layout.fragment_add_word, container, false)
//        binding.viewModel = viewModel
//
//        return binding.root
//    }
//
//    private fun setupWordsList(textWordItems: List<TextWord>) {
//        binding.loadingList.visibility = View.GONE
//        binding.wordsList.visibility = View.VISIBLE
//        binding.wordsList.apply {
//            setHasFixedSize(true)
//            layoutManager = GridLayoutManager(context, 2)
// //            adapter = WordsListAdapter(buildWordItems(mockedTextData, mockedImageData))
//            adapter = WordsListAdapter(buildWordItems(textWordItems.toTypedArray(), mockedImageData))
//            addItemDecoration(GridDividerItemDecoration(getDivider(context), getDivider(context), 2))
//        }
//    }
//
//    private fun getDivider(context: Context) =
//        ContextCompat.getDrawable(context, R.drawable.grid_divider)
}
