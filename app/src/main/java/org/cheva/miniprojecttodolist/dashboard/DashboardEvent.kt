package org.cheva.miniprojecttodolist.dashboard

sealed interface DashboardEvent {
    object ResetSuccessDelete : DashboardEvent
    object DeleteTask : DashboardEvent
}