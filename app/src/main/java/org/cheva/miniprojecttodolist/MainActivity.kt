package org.cheva.miniprojecttodolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.cheva.miniprojecttodolist.dashboard.DashboardScreen
import org.cheva.miniprojecttodolist.dashboard.DashboardState
import org.cheva.miniprojecttodolist.dashboard.DashboardViewModel
import org.cheva.miniprojecttodolist.login.LoginScreen
import org.cheva.miniprojecttodolist.login.LoginViewModel
import org.cheva.miniprojecttodolist.navigation.DashboardScreen
import org.cheva.miniprojecttodolist.navigation.LoginScreen
import org.cheva.miniprojecttodolist.navigation.RegisterScreen
import org.cheva.miniprojecttodolist.register.RegisterScreen
import org.cheva.miniprojecttodolist.register.RegisterViewModel
import org.cheva.miniprojecttodolist.ui.theme.MiniProjectTodoListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniProjectTodoListTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = RegisterScreen,
                    builder = {
                        composable<RegisterScreen> {
                            val viewModel = viewModel<RegisterViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()
                            RegisterScreen(
                                state = state,
                                onEvent = viewModel::onEvent,
                                onNavigate = { navController.navigate(it) }
                            )
                        }
                        composable<DashboardScreen> {
                            val viewModel = viewModel<DashboardViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()
                            DashboardScreen(
                                state = state,
                                onEvent = viewModel::onEvent,
                            )
                        }
                        composable<LoginScreen> {
                            val viewModel = viewModel<LoginViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()
                            LoginScreen(
                                state = state,
                                onEvent = viewModel::onEvent,
                                onNavigate = { navController.navigate(it) }
                            )
                        }
                    }
                )
            }
        }
    }
}