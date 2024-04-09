package com.example.apollo.ui.theme.screens.about

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
import com.example.apollo.navigation.ROUTE_HOME
import com.example.apollo.ui.theme.ApolloTheme


@Composable
fun AboutScreen(navController: NavController){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(color = Color.Magenta)
    ) {
        Text(
            text = "About Screen",
            fontSize = 40.sp,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Serif
        )
        Button(
            onClick = { navController.navigate(ROUTE_HOME)
            },
            colors = ButtonDefaults.buttonColors(Color.Green),
            modifier = Modifier.fillMaxWidth()
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 0.dp,
                    top = 0.dp
                )
        ) {
            Text(
                text = "Back to home screen"
            )
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "About Screen Preview"
)
@Composable
fun AboutScreenPreview(){
    ApolloTheme {
        AboutScreen(navController = rememberNavController())
    }

}