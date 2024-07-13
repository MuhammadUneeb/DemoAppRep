package com.example.demoapp.appui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demoapp.viewmodels.DemoSharedViewModel

@Composable
fun DetailScreen(sharedViewModel: DemoSharedViewModel) {
 Surface {
     val drug=sharedViewModel.drug.collectAsState()
     Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
         Text(text = drug.value.name.toString(), fontSize = 24.sp, color = Color.Black)
         Spacer(modifier = Modifier.height(6.dp))
         Text(text = drug.value.dose.toString(), fontSize = 14.sp, color = Color.Black)
         Spacer(modifier = Modifier.height(6.dp))
         Text(text = drug.value.strength.toString(), fontSize = 24.sp, color = Color.Black)
     }
 }
}