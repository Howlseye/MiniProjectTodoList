package org.cheva.miniprojecttodolist.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.cheva.miniprojecttodolist.R
import org.cheva.miniprojecttodolist.components.CustomText
import org.cheva.miniprojecttodolist.navigation.*
import org.cheva.miniprojecttodolist.navigation.LoginScreen
import org.cheva.miniprojecttodolist.navigation.TambahTugasScreen
import org.cheva.miniprojecttodolist.ui.components.ResultDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    state: DashboardState,
    dataState: DataState,
    onEvent: (DashboardEvent) -> Unit,
    dataEvent: (DataEvent) -> Unit,
    onNavigate: (Any) -> Unit,
){

    Scaffold(
        topBar = {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
//                    .padding(8.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row (
                    modifier = Modifier
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.person),
                        contentDescription = "Person",
                        modifier = Modifier.size(38.dp)
                    )
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = dataState.username,
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                IconButton (
                    onClick = { onNavigate(LoginScreen) }
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.logout),
                        contentDescription = "Logout",
                        modifier = Modifier.size(38.dp)
                    )
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onNavigate(TambahTugasScreen) },
                containerColor = Color(0xFF00B300),
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add),
                    contentDescription = "Add",
                    modifier = Modifier.size(32.dp)
                )
            }
        }
     ) { innerPadding ->
        if (state.successDelete) {
            ResultDialog(
                isSuccess = state.successDelete,
                message = "Berhasil Dihapus",
                onDismiss = { onEvent(DashboardEvent.ResetSuccessDelete) }
            )
        }
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            CustomText(
                text = "Daftar Tugas",
                textAlign = TextAlign.Center,
                textSize = 24.sp,
                textWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            LazyColumn(
                modifier = Modifier
                    .padding(8.dp)
                    .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                items(dataState.taskList) { task ->
                    val no: Int = dataState.taskList.indexOf(task) + 1
                    val judul = task.judul
                    val deskripsi = task.deskripsi
                    val kategori = task.kategori

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.Center,
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Text(
                                text = "$no. $judul",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Row(
                                modifier = Modifier.padding(end = 8.dp),
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                IconButton(
                                    onClick = {
                                        dataEvent(DataEvent.ReqEditTask(task))
                                        dataEvent(DataEvent.IsEdit(true))
                                        onNavigate(TambahTugasScreen)
                                    }
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.edit),
                                        contentDescription = "Edit",
                                    )
                                }

                                IconButton(
                                    onClick = {
                                        dataEvent(DataEvent.RemoveTask(task))
                                        onEvent(DashboardEvent.DeleteTask) }
                                ){
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Delete",
                                    )
                                }
                            }
                        }

                        Text(
                            text = "Deskripsi: $deskripsi",
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Text(
                            text = "Kategori: $kategori",
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.primary
                        )
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 4.dp),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DashboardScreenPrev() {
    DashboardScreen(
        state = DashboardState(),
        onEvent = {},
        onNavigate = {},
        dataState = DataState(),
        dataEvent = {}
    )
}