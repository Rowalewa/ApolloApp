package com.example.apollo.ui.theme.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.apollo.navigation.ROUTE_HOME
import com.example.apollo.ui.theme.ApolloTheme

@Composable
fun LoginScreen(navController: NavController){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Log In",
            fontSize = 30.sp,
            color = Color.Blue,
            fontWeight = FontWeight.ExtraBold
        )
        Spacer(modifier = Modifier.height(40.dp))
        TextFieldComponent(myLabel = "Enter your email address")
        Spacer(modifier = Modifier.height(20.dp))
        TextFieldComponent(myLabel = "Enter your Password")
        Spacer(modifier = Modifier.height(50.dp))
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
    }
}
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
        .background(color = Color.Magenta),
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