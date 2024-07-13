package com.example.demoapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoapp.domain.usecase.DemoUseCase
import com.example.demoapp.localdb.model.MyInfo
import com.example.demoapp.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DemoViewModel @Inject constructor(
    private val demoUseCase: DemoUseCase):ViewModel() {

    private var _mockApiState: MutableStateFlow<ApiState?> = MutableStateFlow(ApiState.Empty)
    val mockApiState = _mockApiState.asStateFlow()
    fun mockApiCall() {
        viewModelScope.launch {
            _mockApiState.value = ApiState.Loading
            demoUseCase.mockApiUseCase.invoke().catch { e ->
                _mockApiState.value = ApiState.Failure(e)
            }.collect { data ->
                _mockApiState.value = data.let { ApiState.Success(it) }
            }
        }
    }
    fun InsertInfo(myInfo: MyInfo){
        viewModelScope.launch {
            demoUseCase.insertInfo.invoke(myInfo)
        }
    }
}