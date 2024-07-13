package com.example.demoapp.appui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.demoapp.viewmodels.DemoViewModel
import com.example.demoapp.models.AssociatedDrug
import com.example.demoapp.models.ClassName
import com.example.demoapp.models.Diabete
import com.example.demoapp.models.Medication
import com.example.demoapp.models.MedicationsClasse
import com.example.demoapp.models.MockDemoResponse
import com.example.demoapp.models.Problem
import com.example.demoapp.utils.ApiState
import com.example.demoapp.utils.Screens
import com.example.demoapp.utils.greetUser
import com.example.demoapp.viewmodels.DemoSharedViewModel

@Composable
fun HomeScreen(navController: NavHostController, sharedViewModel: DemoSharedViewModel) {
    val context= LocalContext.current
    Surface {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(22.dp))
            Text(text = greetUser()+" "+sharedViewModel.email.collectAsState().value, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(22.dp))
            val demoViewModel = hiltViewModel<DemoViewModel>()
            val apiResponse=demoViewModel.mockApiState.collectAsState()
            LaunchedEffect(Unit) {
                demoViewModel.mockApiCall()
            }
            when(apiResponse.value){
               is ApiState.Empty ->{

                }
               is ApiState.Loading ->{

                }
                is ApiState.Failure -> {

                }
                is ApiState.Success -> {
                    val apiData= (apiResponse.value as ApiState.Success).data as MockDemoResponse
                    DisplayLazyList(mockDemoResponse = apiData.problems,navController,sharedViewModel)
                }
                null -> {

                }
            }
        }
    }
}
@Composable
fun DisplayLazyList(mockDemoResponse: List<Problem>?, navController: NavHostController,sharedViewModel: DemoSharedViewModel){
    val doses = mutableListOf<AssociatedDrug>()
    mockDemoResponse?.forEach {problem ->
        problem.diabetes?.forEach{diabete: Diabete ->
            diabete.medications?.forEach {medication: Medication ->
                medication.medicationsClasses?.forEach {medicationsClasse: MedicationsClasse ->
                    medicationsClasse.className?.forEach {className: ClassName ->
                        className.associatedDrug?.let { doses.addAll(it) }
                        className.associatedDrug2?.let { doses.addAll(it) }

                    }
                    medicationsClasse.className2?.forEach {className2 ->
                        className2.associatedDrug?.let { doses.addAll(it) }
                        className2.associatedDrug2?.let { doses.addAll(it) }
                    }
                }
            }

        }


    }
    LazyColumn{
            items(doses.toList()){item->
               DisplayListItem(item){
                   sharedViewModel.setDetailscreenData(it)
                   navController.navigate(Screens.DETAIL.name)
               }
            }

    }
}

@Composable
fun DisplayListItem(it: AssociatedDrug,clickOnItem:(AssociatedDrug)->Unit) {
     Box(modifier = Modifier
         .fillMaxWidth()
         .wrapContentHeight()
         .padding(horizontal = 12.dp, vertical = 8.dp)
         .background(color = Color.White, shape = RoundedCornerShape(4.dp))
         .clickable {
             clickOnItem.invoke(it)
         }) {
       Column (horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)){
           Text(text = "${it.name}", fontSize = 14.sp, color = Color.Black)
           Spacer(modifier = Modifier.height(6.dp))
           Text(text = "${it.dose}", fontSize = 14.sp, color = Color.Black)
           Spacer(modifier = Modifier.height(6.dp))
           Text(text = "${it.strength}", fontSize = 14.sp, color = Color.Black)
       }
     }
}
