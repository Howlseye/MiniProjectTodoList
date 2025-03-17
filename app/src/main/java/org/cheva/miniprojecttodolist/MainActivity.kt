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
import org.cheva.miniprojecttodolist.TambahTugas.*
import org.cheva.miniprojecttodolist.dashboard.*
import org.cheva.miniprojecttodolist.login.*
import org.cheva.miniprojecttodolist.navigation.*
import org.cheva.miniprojecttodolist.register.*
import org.cheva.miniprojecttodolist.ui.theme.MiniProjectTodoListTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniProjectTodoListTheme {
                val dataViewModel = viewModel<DataViewModel>()
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = RegisterScreen,
                    builder = {
                        composable<RegisterScreen> {
                            val viewModel = viewModel<RegisterViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()
                            val data by dataViewModel.state.collectAsStateWithLifecycle()
                            RegisterScreen(
                                state = state,
                                onEvent = viewModel::onEvent,
                                onNavigate = { navController.navigate(it) },
                                dataState = data,
                                dataEvent = dataViewModel::onEvent,
                            )
                        }
                        composable <DashboardScreen> {
                            val viewModel = viewModel<DashboardViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()
                            val data by dataViewModel.state.collectAsStateWithLifecycle()
                            DashboardScreen(
                                state = state,
                                onEvent = viewModel::onEvent,
                                onNavigate = { navController.navigate(it) },
                                dataState = data,
                                dataEvent = dataViewModel::onEvent,
                            )
                        }
                        composable<LoginScreen> {
                            val viewModel = viewModel<LoginViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()
                            val data by dataViewModel.state.collectAsStateWithLifecycle()
                            LoginScreen(
                                state = state,
                                onEvent = viewModel::onEvent,
                                onNavigate = { navController.navigate(it) },
                                dataState = data,
                                dataEvent = dataViewModel::onEvent,
                            )
                        }
                        composable<TambahTugasScreen> {
                            val viewModel = viewModel<TambahTugasViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()
                            val data by dataViewModel.state.collectAsStateWithLifecycle()
                            TambahTugasScreen(
                                state = state,
                                onEvent = viewModel::onEvent,
                                onNavigate = { navController.navigate(it) },
                                dataState = data,
                                dataEvent = dataViewModel::onEvent,
                            )
                        }
                    }
                )
            }
        }
    }
}