package org.cheva.miniprojecttodolist.TambahTugas

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import org.cheva.miniprojecttodolist.components.CustomText
import org.cheva.miniprojecttodolist.components.TextBox
import org.cheva.miniprojecttodolist.navigation.DashboardScreen
import org.cheva.miniprojecttodolist.navigation.DataEvent
import org.cheva.miniprojecttodolist.navigation.DataState
import org.cheva.miniprojecttodolist.navigation.Tugas
import org.cheva.miniprojecttodolist.ui.components.ResultDialog

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TambahTugasScreen (
    state : TambahTugasState,
    onEvent : (TambahTugasEvent) -> Unit,
    dataState : DataState,
    dataEvent : (DataEvent) -> Unit,
    onNavigate : (Any) -> Unit
) {
    val oldTask = dataState.reqEditTask

    if(dataState.reqEditTask != Tugas("edit", "edit", "edit")){
        onEvent(TambahTugasEvent.OnJudulChanged(oldTask.judul))
        onEvent(TambahTugasEvent.OnDeskripsiChanged(oldTask.deskripsi))
        onEvent(TambahTugasEvent.OnKategoriChanged(oldTask.kategori))
        dataEvent(DataEvent.ClearRequestEditTask)
    }

    LaunchedEffect(state.success) {
        val task = Tugas(state.judul, state.deskripsi, state.kategori)
        if (state.success){
            if (!dataState.isEdit) {
                dataEvent(DataEvent.AddTask(task))
            }
            else {
                dataEvent(DataEvent.EditTask(oldTask, task))
                dataEvent(DataEvent.IsEdit(false))
                dataEvent(DataEvent.ClearRequestEditTask)
            }
            delay(1000)
            onEvent(TambahTugasEvent.onDismissDialog)
            onNavigate(DashboardScreen)
        }
    }
    Scaffold {
        if (state.message.isNotBlank()) {
            ResultDialog(
                isSuccess = state.success,
                message = state.message,
                onDismiss = { onEvent(TambahTugasEvent.onDismissDialog) }
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomText(
                text = "Tambah Tugas",
                textSize = 28.sp,
                textWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))
            TextBox(
                name = "Judul Tugas",
                placeholder = "Masukkan Judul Tugas",
                value = state.judul,
                onValueChange = {onEvent(TambahTugasEvent.OnJudulChanged(it))}
            )

            Spacer(modifier = Modifier.height(8.dp))
            TextBox(
                name = "Deskripsi Tugas",
                placeholder = "Masukkan Deskripsi Tugas",
                value = state.deskripsi,
                onValueChange = { onEvent(TambahTugasEvent.OnDeskripsiChanged(it)) }
            )

            Spacer(modifier = Modifier.height(8.dp))
            TextBox(
                name = "Kategori Tugas",
                placeholder = "Masukkan Kategori Tugas",
                value = state.kategori,
                onValueChange = { onEvent(TambahTugasEvent.OnKategoriChanged(it)) }
            )

            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00B300)),
                onClick = {
                    if (dataState.isEdit) {
                        onEvent(TambahTugasEvent.onUpdateClicked)
                    } else {
                        onEvent(TambahTugasEvent.onTambahClicked)
                    }
                }
            ) {
                Text(if (dataState.isEdit) "Update" else "Tambah")
            }

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE60000)),
                onClick = {
                    dataEvent(DataEvent.ClearRequestEditTask)
                    dataEvent(DataEvent.IsEdit(false))
                    onNavigate(DashboardScreen)
                }
            ) {
                Text("Kembali")
            }
        }
    }
}

@Preview
@Composable
fun TambahTugasPreview() {
    TambahTugasScreen(
        state = TambahTugasState(),
        onEvent = {},
        onNavigate = {},
        dataState = DataState(),
        dataEvent = {}
    )
}