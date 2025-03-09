package org.cheva.miniprojecttodolist.dashboard

data class DashboardState (
    val newtask: String = "",
    val list: List<String> = emptyList(),
)