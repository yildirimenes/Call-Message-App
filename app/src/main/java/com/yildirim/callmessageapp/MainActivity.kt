package com.yildirim.callmessageapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yildirim.callmessageapp.ui.theme.CallMessageAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CallMessageAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CallMessage()
                }
            }
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CallMessage() {
    val tfPhoneNumber = remember { mutableStateOf("") }
    val tfPhoneMessage = remember { mutableStateOf("") }
    val context = LocalContext.current
    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Call Message Operation")
                },
            )
        },
        ){
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            PhoneField(
                tfPhoneNumber.value,
                mask = "(000) 000 00 00",
                maskNumber = '0',
                label = { Text(text = "Phone Number")},
                onPhoneChanged = { tfPhoneNumber.value = it }
            )
            Spacer(modifier = Modifier.size(30.dp))
            OutlinedTextField(
                value = tfPhoneMessage.value,
                onValueChange = { tfPhoneMessage.value = it},
                label = { Text(text = "Message")},
                shape = RoundedCornerShape(12.dp),
            )
            Spacer(modifier = Modifier.size(30.dp))

            Row(
                modifier = Modifier
                    .padding(all = 2.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ){

                Button(
                    onClick = { makePhoneCall(tfPhoneNumber.value,context) },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Text(text = "Call", fontSize = 16.sp)
                    }
                }
                Button(
                    onClick = {
                        sendMessage(tfPhoneNumber.value,tfPhoneMessage.value,context)
                        tfPhoneMessage.value = "" },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Row {
                        Icon(
                            imageVector = Icons.Default.MailOutline,
                            contentDescription = null,
                            modifier = Modifier.padding(end = 4.dp)
                        )
                        Text(text = "Message", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CallMessageAppTheme {
        CallMessage()
    }
}