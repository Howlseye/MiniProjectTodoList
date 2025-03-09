package org.cheva.miniprojecttodolist.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.cheva.miniprojecttodolist.R
import org.cheva.miniprojecttodolist.components.CustomText
import org.cheva.miniprojecttodolist.components.TextBox
import kotlin.math.round

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    state: DashboardState,
    onEvent: (DashboardEvent) -> Unit
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    CustomText(
                        text = "Dashboard",
                        textSize = 28.sp,
                        textWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
     ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
                .fillMaxHeight()
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            TextBox(
                name = "",
                placeholder = stringResource(R.string.add_hint),
                value = state.newtask,
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.add),
                        contentDescription = "Add",
                        modifier = Modifier.size(28.dp),
                    )
                },
                onValueChange = { onEvent(DashboardEvent.OnTaskChanged(it)) },
            )

            Button(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                onClick = {
                if (state.newtask.isNotBlank()) {
                    onEvent(DashboardEvent.OnAddClicked)
                    onEvent(DashboardEvent.OnTaskChanged(""))
                }
            }) {
                Text("Tambah")
            }

            Spacer(modifier = Modifier.height(16.dp))
            CustomText(
                text = "Daftar Tugas",
                textAlign = TextAlign.Center,
                textSize = 24.sp,
                textWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            LazyColumn(
                modifier = Modifier.padding(8.dp)
                    .border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                items(state.list) { task ->
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(task, modifier = Modifier.weight(1f))
                        IconButton(onClick = {
                            onEvent(DashboardEvent.DeleteTask(task))
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }
                }

            }
        }

    }
}

@Preview
@Composable
private fun DashboardScreenPrev() {
    DashboardScreen(
        state = DashboardState(),
        onEvent = {}
    )
}