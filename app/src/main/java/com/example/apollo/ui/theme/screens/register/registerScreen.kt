package com.example.apollo.ui.theme.screens.register


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.apollo.R
import com.example.apollo.data.AuthViewModel
import com.example.apollo.navigation.ROUTE_HOME
import com.example.apollo.navigation.ROUTE_LOGIN
import com.example.apollo.navigation.ROUTE_REGISTER
import com.example.apollo.ui.theme.ApolloTheme

@Composable
fun RegisterScreen(navController: NavController) {
    var email by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var pass by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var confpass by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var context = LocalContext.current
    Box() {
        Image(
            painter = painterResource(id = R.drawable.curved_wallpaper),
            contentDescription = "curved background",
            modifier = Modifier.fillMaxSize()
        )
    }
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        Text(
            text = "PLEASE REGISTER HERE",
            fontFamily = FontFamily.Serif,
            fontSize = 30.sp,
            fontWeight = FontWeight.ExtraBold,
            color = Color.Blue,
            textAlign = TextAlign.Center
        )
        OutlinedTextField(
            value = email,
            onValueChange = {email = it},
            label = { Text(
                text = "Enter Email Address"
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = pass,
            onValueChange = {pass = it},
            label = { Text(
                text = "Enter Password"
            )
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        )
        OutlinedTextField(
            value = confpass,
            onValueChange = {confpass = it},
            label = { Text(
                text = "Confirm Password"
            )
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
        )
        Button(onClick = {
            val myregister = AuthViewModel(navController, context)
            myregister.signup(
                email.text.trim(),
                pass.text.trim(),
                confpass.text.trim()
            )
            navController.navigate(ROUTE_HOME) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 0.dp,
                    bottom = 0.dp
                ),
            colors = ButtonDefaults.buttonColors(Color.Cyan)) {
            Text(
                text ="Register",
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif
            )

        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Already have an account?")
        Button(onClick = { navController.navigate(ROUTE_LOGIN) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 0.dp,
                    bottom = 0.dp
                ),
            colors = ButtonDefaults.buttonColors(Color.Cyan)) {
            Text(
                text ="Log In",
                color = Color.Black,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp,
                fontFamily = FontFamily.Serif
            )

        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComponent(myLabel: String){
    var text by remember {
        mutableStateOf(value = "")
    } // enabling text input
    TextField(modifier = Modifier
        .fillMaxWidth()
        .padding(
            start = 20.dp,
            end = 20.dp,
            top = 0.dp,
            bottom = 0.dp
        )
        .clip(
            shape = RoundedCornerShape(20.dp)
        )
        .background(color = Color.Red),
        value = text, onValueChange = {
                newText -> text = newText // does the observation of the input field
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            cursorColor = Color.Magenta,
            disabledLabelColor = Color.Blue,
            focusedTextColor = Color.Blue,
            unfocusedTextColor = Color.Cyan
        ),
        label = { TextFieldLabels(value = myLabel)})  // label
}
@Composable
fun TextFieldLabels(value: String){
    Text(text = value
    )
}
@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Register Screen Preview"
)
@Composable
fun RegisterScreenPreview(){
    ApolloTheme {
        RegisterScreen(navController = rememberNavController())
    }
}