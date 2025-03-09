package org.cheva.miniprojecttodolist.dashboard

sealed interface DashboardEvent {
    data class OnTaskChanged(val task: String) : DashboardEvent
    data class DeleteTask(val task: String) : DashboardEvent
    object OnAddClicked : DashboardEvent
}