package com.example.navigation.ui.fragment

import android.annotation.SuppressLint
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.navigation.data.AppDataManager
import com.example.navigation.data.model.Article
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainFragmentViewModel @Inject internal constructor(
    private var appDataManager: AppDataManager,
) : ViewModel() {
    val data = MutableLiveData<State>()
    var news: List<Article>? = null
    val progressVisible = ObservableField(true)

    @SuppressLint("CheckResult")
    fun news() {
        progressVisible.set(true)
        appDataManager.api()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ //success
                news = it.articles
                data.postValue(State.OnCompleted)
                //progressVisible.set(false)

            }, {
                data.value = State.OnError(it.message ?: "Bir hatayla karşılaşıldı")
            })

    }


    sealed class State {
        object OnCompleted : State()
        data class OnError(val error: String) : State() //parametre göndermek için data class

    }
}