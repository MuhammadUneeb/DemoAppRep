package com.example.demoapp.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.demoapp.models.AssociatedDrug
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
@HiltViewModel
class DemoSharedViewModel @Inject constructor(@ApplicationContext applicationContext: Context):ViewModel() {
    private val _email = MutableStateFlow<String>("")
    val email = _email.asStateFlow()

    fun setMessage(newMessage: String) {
        _email.value = newMessage
    }
    private val _drug = MutableStateFlow(AssociatedDrug(null,null,null))
    val drug = _drug.asStateFlow()
    fun setDetailscreenData(drugValue: AssociatedDrug){
        _drug.value=drugValue

    }
}