package com.example.apollo.ui.theme.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.apollo.navigation.ROUTE_ABOUT
import com.example.apollo.navigation.ROUTE_LOGIN
import com.example.apollo.ui.theme.ApolloTheme


@Composable
fun HomeScreen(navController: NavController){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier// padding(
//            start = 20.dp,
//            end = 20.dp,
//            bottom = 0.dp,
//            top = 0.dp
//        )
            .background(color = Color.Red),
    ) {
        Text(
            text = "Home Screen",
            fontSize = 40.sp,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Serif
        )
        Button(
            onClick = { navController.navigate(ROUTE_ABOUT) },
            colors = ButtonDefaults.buttonColors(Color.Blue),
            modifier = Modifier.fillMaxWidth()
                .padding(
                    top = 0.dp,
                    start = 10.dp,
                    end = 20.dp,
                    bottom = 0.dp
                )
        ) {
            Text(text = "About",
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Red
            )
        }
        Button(
            onClick = { navController.navigate(ROUTE_LOGIN) },
            colors = ButtonDefaults.buttonColors(Color.Cyan),
            modifier = Modifier.fillMaxWidth()
                .padding(
                    top = 0.dp,
                    start = 10.dp,
                    end = 20.dp,
                    bottom = 0.dp
                )
        ) {
            Text(text = "Log In",
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
    name = "Hme Screen Preview"
)
@Composable
fun HomeScreenPreview(){
    ApolloTheme {
        HomeScreen(navController = rememberNavController())
    }
}