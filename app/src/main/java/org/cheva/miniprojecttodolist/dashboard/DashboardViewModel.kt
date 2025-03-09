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
            is DashboardEvent.OnTaskChanged -> changeTask(event.task)
            is DashboardEvent.DeleteTask -> deleteTask(event.task)
            DashboardEvent.OnAddClicked -> addTask(state.value.newtask)
        }
    }

    fun changeTask(task: String) {
        _state.update { it.copy(newtask = task) }
    }

    fun addTask(task: String) {
        val updateList = _state.value.list.toMutableList()
        updateList.add(task)
        _state.update { it.copy(list = updateList) }
    }


    fun deleteTask(task: String) {
        val updateList = _state.value.list.toMutableList()
        val index = updateList.indexOf(task)
        if (index in updateList.indices) {
            updateList.removeAt(index)
            _state.value = DashboardState(list = updateList)
        }
    }
}