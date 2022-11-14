package com.example.lab2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab2.ui.theme.Lab2Theme
import com.example.lab2.ui.theme.Lab2green

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab2Theme{
                HelloMessage()
            }
        }
    }
}


@Composable
fun HelloMessage() {
    var name by remember { mutableStateOf("") }
    var textFieldName by remember { mutableStateOf("") }
    var imageChanger by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        NameTextField(name = textFieldName, changed = { textFieldName = it })
        HelloButton{
            name = textFieldName
            imageChanger = !imageChanger
        }
        Image(
            painter = painterResource(id = if(imageChanger) {
                R.drawable.puppy
            } else {
                R.drawable.image4
            }),
            contentDescription = stringResource(id = R.string.puppy),
            modifier = Modifier
                .padding(top = 40.dp, bottom = 40.dp)
                .size(210.dp)
                .clip(CircleShape),

            )
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Lab2green)
        ) {
            MessageText(newName = name)
        }
    }
}

@Composable
fun HelloButton(clicked: () -> Unit){
    Button(onClick= clicked) {
        Text(
            stringResource(id = R.string.buttonHello)
        )
    }
}

@Composable
fun NameTextField(name: String, changed: (String) ->Unit){
    TextField(
        value = name,
        label = {Text(stringResource(id = R.string.entryField))},
        onValueChange = changed,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp)
    )
}

@Composable
fun MessageText(newName: String){
    if(newName.isNotEmpty()) {
        Text(
            stringResource(R.string.helloMessage) + " " + newName,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Lab2Theme (darkTheme = false){
        HelloMessage()
    }
}

@Preview(showBackground = true)
@Composable
fun DarkDefaultPreview() {
    Lab2Theme(darkTheme = true) {
        HelloMessage()
    }
}