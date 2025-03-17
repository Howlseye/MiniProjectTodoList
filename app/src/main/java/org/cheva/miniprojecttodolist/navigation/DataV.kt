package org.cheva.miniprojecttodolist.navigation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class DataState(
    val taskList: List<Tugas> = listOf(
        Tugas("Tugas 1", "Deskripsi Tugas 1", "Kategori 1"),
        Tugas("Tugas 2", "Deskripsi Tugas 2", "Kategori 2"),
        Tugas("Tugas 3", "Deskripsi Tugas 3", "Kategori 3"),
        Tugas("Tugas 4", "Deskripsi Tugas 4", "Kategori 4"),
        Tugas("Tugas 5", "Deskripsi Tugas 5", "Kategori 5")
    ),
    val reqEditTask: Tugas = Tugas("edit", "edit", "edit"),
    val username: String = "",
    val isEdit: Boolean = false,
)

class DataViewModel : ViewModel() {
    private val _state = MutableStateFlow(DataState())
    val state: StateFlow<DataState> = _state.asStateFlow()

    fun onEvent(event: DataEvent) {
        when (event) {
            is DataEvent.AddTask -> addTask(event.task)
            is DataEvent.EditTask -> editTask(event.task1, event.task2)
            is DataEvent.ReqEditTask -> setRequestEditTask(event.task)
            is DataEvent.RemoveTask -> removeTask(event.task)
            is DataEvent.ChangeUsername -> setUsername(event.username)
            is DataEvent.IsEdit -> setisEdit(event.edit)
            DataEvent.ClearUsername -> clearUsername()
            DataEvent.ClearRequestEditTask -> clearRequestEditTask()

        }
    }

    private fun setUsername(username: String) {
        _state.update {
            it.copy(username = username)
        }
    }

    private fun setRequestEditTask(task: Tugas) {
        _state.update {
            it.copy(reqEditTask = task)
        }
    }

    private fun setisEdit(edit: Boolean) {
        _state.update {
            it.copy(isEdit = edit)
        }
    }

    private fun clearUsername() {
        _state.update {
            it.copy(username = "")
        }
    }

    private fun clearRequestEditTask() {
        _state.update {
            it.copy(reqEditTask = Tugas("edit", "edit", "edit"))
        }
    }

    private fun addTask(task: Tugas) {
        _state.update {
            it.copy(taskList = it.taskList + task)
        }
    }

    private fun editTask(task1: Tugas, task2: Tugas) {
        val index = _state.value.taskList.indexOf(task1)
        val list = _state.value.taskList.toMutableList()
        list[index] = task2
        _state.update {
            it.copy(taskList = list)
        }
    }

    private fun removeTask(task: Tugas) {
        _state.update {
            it.copy(taskList = it.taskList - task)
        }
    }
}

sealed interface DataEvent {
    data class ChangeUsername(val username: String) : DataEvent
    data class AddTask(val task: Tugas) : DataEvent
    data class EditTask(val task1: Tugas, val task2: Tugas) : DataEvent
    data class ReqEditTask(val task: Tugas) : DataEvent
    data class RemoveTask(val task: Tugas) : DataEvent
    data class IsEdit(val edit: Boolean): DataEvent
    object ClearUsername : DataEvent
    object ClearRequestEditTask : DataEvent

}
