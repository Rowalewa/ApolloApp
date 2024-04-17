package com.example.apollo.ui.theme.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.apollo.navigation.ROUTE_HOME
import com.example.apollo.navigation.ROUTE_REGISTER
import com.example.apollo.ui.theme.ApolloTheme

@Composable
fun LoginScreen(navController: NavController){
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var pass by remember { mutableStateOf(TextFieldValue("")) }
    var context= LocalContext.current
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(color = Color.Yellow)
    ){
        Text(
            text = "PLEASE LOG IN HERE",
            fontSize = 30.sp,
            color = Color.Red,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Cursive
        )
        Spacer(modifier = Modifier.height(40.dp))
        OutlinedTextField(
            value =email ,
            onValueChange = {email=it},
            label = { Text(text = "Enter Email") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),

            )
        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(value =pass , onValueChange = {pass=it},
            label = { Text(text = "Enter Password") },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { navController.navigate(ROUTE_HOME) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 0.dp,
                    bottom = 0.dp
                ),
            colors = ButtonDefaults.buttonColors(Color.Green)) {
            Text(text = "Back to Home",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = Color.Magenta
            )
        }
        Button(onClick = {

        }, modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 0.dp,
                bottom = 0.dp
            )) {
            Text(text = "Log In",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = Color.Magenta
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Don't have account? Click to Register")
        Button(onClick = {
            navController.navigate(ROUTE_REGISTER)
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 20.dp,
                end = 20.dp,
                top = 0.dp,
                bottom = 0.dp
            ),
            colors = ButtonDefaults.buttonColors(Color.Blue)) {
            Text(text = "Register",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif,
                color = Color.Magenta
            )
        }

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
        ),
        colors = TextFieldDefaults.textFieldColors(Color.Magenta),
        value = text, onValueChange = {
                newText -> text = newText // does the observation of the input field
        },
        label = { TextFieldLabels(value = myLabel)})  // label
}
@Composable
fun TextFieldLabels(value: String){
    Text(text = value,
        color = Color.Red
    )
}
@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Log in screen Preview"
)
@Composable
fun LoginPreview(){
    ApolloTheme {
        LoginScreen(navController = rememberNavController())
    }
}