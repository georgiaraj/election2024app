package com.example.gettoriesout

import android.content.SharedPreferences.Editor
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import com.example.gettoriesout.ui.theme.GetToriesOutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GetToriesOutTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    PostCodeButton(
                        modifier = Modifier
                            .wrapContentSize(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun PostCodeButton(modifier: Modifier = Modifier) {
    var postcodeInput by remember { mutableStateOf("") }
    var partyResult by remember { mutableStateOf("" )}
    Surface(color = Color.Cyan) {
        Column(
                modifier=modifier,
                horizontalAlignment = Alignment.CenterHorizontally)
        {
            Text(
                "Get the Tories Out!",
                Modifier
                    .size(200.dp)
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center))
            Image(
                painter = painterResource(R.drawable.no_tories),
                contentDescription = "1"
            )
            Spacer(modifier=Modifier.height(16.dp))
            EditPostCodeField(
                value = postcodeInput,
                onValueChange = { postcodeInput = it },
                modifier=Modifier.width(150.dp)
            )
            Button(
                onClick = { partyResult = SearchBestParty(value = postcodeInput) }) {
                    Text(stringResource(R.string.search))
            }
            Text(partyResult)
        }
    }
}

@Composable
fun EditPostCodeField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Enter your postcode to find out who to vote for to help get the Tories out!")
        Spacer(modifier=Modifier.width(10.dp))
        TextField(
            value = value,
            modifier = modifier,
            onValueChange = onValueChange,
            label = { Text(stringResource(R.string.post_code)) }
        )
    }
}

fun SearchBestParty(
    value: String
): String { return "Labour" }


@Preview(showBackground = true)
@Composable
fun PostCodeApp() {
    GetToriesOutTheme {
        PostCodeButton(
            modifier= Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center))
    }
}