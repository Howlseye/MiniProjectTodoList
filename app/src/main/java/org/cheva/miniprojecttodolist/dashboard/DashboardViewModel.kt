package org.cheva.miniprojecttodolist.dashboard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class DashboardViewModel: ViewModel() {
    private val _state = MutableStateFlow(DashboardState())
    val state: StateFlow<DashboardState> get() = _state

    fun onEvent(event: DashboardEvent) {
        when(event) {
            DashboardEvent.ResetSuccessDelete -> resetSuccessDelete()
            DashboardEvent.DeleteTask -> deleteTask()
        }
    }

    fun deleteTask() {
        _state.update { it.copy(successDelete = true) }
    }

    fun resetSuccessDelete() {
        _state.update { it.copy(successDelete = false) }
    }
}