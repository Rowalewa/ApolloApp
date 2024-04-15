package com.example.apollo.ui.theme.screens.contact

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.apollo.R
import com.example.apollo.navigation.ROUTE_HOME
import com.example.apollo.ui.theme.ApolloTheme

@Composable
fun ContactScreen(navController: NavController){
    val image = painterResource(R.drawable.panther)
    Image(painter = image,
        contentDescription = "panther",
        modifier = Modifier.fillMaxSize()
    )
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "CONTACTS",
            color = Color.White,
            fontSize = 25.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = FontFamily.Serif
        )
        Image(
            painter = painterResource(id = R.drawable.turtle),
            contentDescription = "turtle",
            modifier = Modifier
                .padding(
                    start = 0.dp,
                    end = 0.dp,
                    bottom = 0.dp,
                    top = 10.dp
                )
                .clip(shape = RoundedCornerShape(20.dp))
                .height(400.dp)
        )

        IconButton(onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Filled.Phone,
                contentDescription = "Phone Number",
                tint = Color.Blue
            )
        }
        Text(
            text = "+254 712 345 678",
            fontSize = 30.sp,
            fontFamily = FontFamily.Serif,
            color = Color.White
        )
        IconButton(onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Filled.Email,
                contentDescription = "Email Address",
                tint = Color.Red
            )
        }
        Text(
            text = "username@gmail.com",
            fontSize = 30.sp,
            fontStyle = FontStyle.Italic,
            textDecoration = TextDecoration.Underline,
            color = Color.Yellow
        )
        IconButton(onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Filled.LocationOn,
                contentDescription = "Location",
                tint = Color.Magenta
            )
        }
        Text(
            text = "Nairobi, Kenya",
            fontSize = 30.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        OutlinedButton(
            onClick = {navController.navigate(ROUTE_HOME)} ,
            colors = ButtonDefaults.buttonColors(Color.Black),
            modifier = Modifier.fillMaxWidth()
                .padding(
                    top = 0.dp,
                    start = 20.dp,
                    end = 20.dp,
                    bottom = 0.dp
                )
                .border(border = BorderStroke(width = 5.dp, color = Color.Magenta),
                    shape = RoundedCornerShape(20.dp)
                )
        ) {
            Text(text = "Home Screen",
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
    name = "Contact Screen Preview"
)
@Composable
fun ContactScreenPreview(){
    ApolloTheme {
        ContactScreen(navController = rememberNavController())
    }
}