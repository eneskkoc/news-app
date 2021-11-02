package com.example.navigation.ui.fragment

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navigation.data.AppDataManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailFragmentViewModel @Inject internal constructor(private var appDataManager: AppDataManager) : ViewModel() {

    val data = MutableLiveData<State>()
    val image = ObservableField<String>()
    val progressVisible = ObservableField(false)
    var title: String? = null
    var desc: String? = null


    @SuppressLint("CheckResult")
    fun newsDetail(position: Int) {
        progressVisible.set(true)
        appDataManager.api()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ //success

                title = it.articles?.get(position)?.title
                desc = it.articles?.get(position)?.description
                image.set(it.articles?.get(position)?.urlToImage)
                data.postValue(State.OnCompleted)
                 progressVisible.set(false)

            }, {
                data.value = State.OnError(it.message ?: "Bir hatayla karşılaşıldı")
            })

    }


    sealed class State {
        object OnCompleted : State()
        data class OnError(val error: String) : State() //parametre göndermek için data class

    }
}