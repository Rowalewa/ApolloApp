package com.example.apollo.ui.theme.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.apollo.R
import com.example.apollo.data.AuthViewModel
import com.example.apollo.navigation.ROUTE_ADD_PRODUCT
import com.example.apollo.navigation.ROUTE_VIEW_PRODUCT
import com.example.apollo.ui.theme.ApolloTheme


@Composable
fun HomeScreen(navController: NavController){
    val context = LocalContext.current
    Box {
        Image(painter = painterResource(id = R.drawable.home),
            contentDescription = "home background",
            modifier = Modifier.fillMaxSize())
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
    ) {
        Text(
            text = "Home Screen",
            fontSize = 40.sp,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Serif
        )
        Button(
            onClick = { navController.navigate(ROUTE_ADD_PRODUCT) },
            colors = ButtonDefaults.buttonColors(Color.Yellow),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 0.dp,
                    start = 10.dp,
                    end = 20.dp,
                    bottom = 0.dp
                )
        ) {
            Text(text = "Add Product",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
        }
        Button(
            onClick = { navController.navigate(ROUTE_VIEW_PRODUCT) },
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 0.dp,
                    start = 10.dp,
                    end = 20.dp,
                    bottom = 0.dp
                )
        ) {
            Text(text = "View Product",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                val myLogout = AuthViewModel(navController, context)
                myLogout.logout()
            },
            colors = ButtonDefaults.buttonColors(Color.Green),
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 0.dp,
                    start = 10.dp,
                    end = 20.dp,
                    bottom = 0.dp
                )
        ) {
            Text(text = "Log Out",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Red
            )
        }
    }
}

@Preview(
    showSystemUi = true,
    showBackground = true,
    name = "Home Screen Preview"
)
@Composable
fun HomeScreenPreview(){
    ApolloTheme {
        HomeScreen(navController = rememberNavController())
    }
}