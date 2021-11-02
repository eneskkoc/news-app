package com.example.navigation.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.example.navigation.R
import com.example.navigation.core.BaseAdapter
import com.example.navigation.data.model.Article
import com.example.navigation.databinding.FragmentMainBinding
import com.example.navigation.databinding.ItemListBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class MainFragment : Fragment() {
    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory
    lateinit var viewModel: MainFragmentViewModel
    private var binding: FragmentMainBinding? = null
    lateinit var adapter: BaseAdapter<ItemListBinding, Article>


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel = ViewModelProvider(this, viewModelProvider).get(MainFragmentViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding!!.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var action: NavDirections
        adapter = object : BaseAdapter<ItemListBinding, Article>(R.layout.item_list) {
            override fun bindView(binding: ItemListBinding, item: Article?, adapterPosition: Int) {
                binding.viewModel = item
            }
            override fun clickListener(item: Article?, position: Int, binding: ItemListBinding) {
                action = MainFragmentDirections.actionMainFragmentToDetailFragment(position)
                Navigation.findNavController(requireView()).navigate(action)
            }
        }
        binding?.recyclerviewNews?.adapter = adapter
        viewModel.news()
        viewModel.data.observe(viewLifecycleOwner, { state ->
            when (state) {
                is MainFragmentViewModel.State.OnCompleted -> onNews()
                is MainFragmentViewModel.State.OnError -> onMessage(state.error)
            }
        })

    }

    private fun onNews() {
        val news = viewModel.news?.toCollection(ArrayList())
        adapter.items = news
    }

    private fun onMessage(error: String) {
        Log.e("backend hatası", error)

    }

}