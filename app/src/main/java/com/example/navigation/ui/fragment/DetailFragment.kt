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
import com.example.navigation.R
import com.example.navigation.databinding.FragmentDetailBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class DetailFragment : Fragment() {

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory
    lateinit var viewModel: DetailFragmentViewModel
    private var binding: FragmentDetailBinding? = null
    var position: Int? = null

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewModel =
            ViewModelProvider(this, viewModelProvider).get(DetailFragmentViewModel::class.java)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding?.viewModel = viewModel
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments.let { bundle ->
            position = bundle?.let { arg -> DetailFragmentArgs.fromBundle(arg).position }
            position?.let { position -> viewModel.newsDetail(position) }
        }

        viewModel.data.observe(viewLifecycleOwner, { state ->
            when (state) {
                is DetailFragmentViewModel.State.OnCompleted -> onNews()
                is DetailFragmentViewModel.State.OnError -> onMessage(state.error)
            }
        })
    }

    private fun onNews() {
        binding?.tvDetailDesc?.text = viewModel.desc
        binding?.tvDetailTitle?.text = viewModel.title
    }

    private fun onMessage(error: String) {
        Log.e("backend hatası", error)

    }
}