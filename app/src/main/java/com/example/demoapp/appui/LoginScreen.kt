package com.example.demoapp.appui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.demoapp.viewmodels.DemoViewModel
import com.example.demoapp.localdb.model.MyInfo
import com.example.demoapp.utils.Screens
import com.example.demoapp.utils.showToast
import com.example.demoapp.viewmodels.DemoSharedViewModel

@Composable
fun DisplayLoginScreen(navController: NavHostController, sharedViewModel: DemoSharedViewModel) {
    val demoViewModel = hiltViewModel<DemoViewModel>()
    val context= LocalContext.current
    var password=""
    var email=""
    Surface {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            EmailLoginField(
                modifier = Modifier.fillMaxWidth(),
            ){
                email=it
            }
            Spacer(modifier = Modifier.height(20.dp))
            PasswordField(
                modifier = Modifier.fillMaxWidth()
            ){
                password=it
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = {
                    if(email.isEmpty()){
                        showToast("Enter your email", context =context )
                    }else if(password.isEmpty()){
                        showToast("Enter your Password", context =context )
                    }else {
                        sharedViewModel.setMessage(email)
                        val myInfo=MyInfo(email=email, password = password)
                        demoViewModel.InsertInfo(myInfo)
                        navController.navigate(Screens.HOME.name){
                            popUpTo(Screens.LOGIN.name){
                                inclusive=true
                            }
                        }
                    }
                },
                enabled = true,
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
        }
    }
}

@Composable
fun EmailLoginField(
    modifier: Modifier = Modifier,
    label: String = "Email",
    placeholder: String = "Enter your Email",
    getEmail:(String)->Unit
) {
    var emailValue by remember {
        mutableStateOf("")
    }
    getEmail.invoke(emailValue)
    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Person,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }
    TextField(
        value = emailValue,
        onValueChange = { emailValue=it },
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = VisualTransformation.None
    )
}
@Composable
fun PasswordField(
    modifier: Modifier = Modifier,
    label: String = "Password",
    placeholder: String = "Enter your Password",
    getPassword:(String)->Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    var passwordValue by remember {
        mutableStateOf("")
    }
getPassword.invoke(passwordValue)
    val leadingIcon = @Composable {
        Icon(
            Icons.Default.Key,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }
    val trailingIcon = @Composable {
        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
            Icon(
                if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }


    TextField(
        value = passwordValue,
        onValueChange = {
            passwordValue=it
        },
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        keyboardActions = KeyboardActions(
            onDone = {keyboardController?.hide() }
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}
