package com.mycompose.android.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mycompose.android.presentation.base.BaseActivity
import com.mycompose.android.presentation.product.ProductListActivity
import com.mycompose.android.ui.theme.FirstComposeAppTheme

class MainActivity : BaseActivity() {


    private var uri: Uri? = null

    var st: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FirstComposeAppTheme {
                Conversation(messages = SampleData.conversationSample)
            }
        }

        /*Log.e("Length ", st!!.length.toString())*/
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(messages) { message ->
            MessageCard(msg = message)
        }
    }
}

data class Message(val author: String, val body: String)


@Composable
fun MessageCard(msg: Message) {
    val context = LocalContext.current
    Row(modifier = Modifier
        .padding(all = 8.dp)
        .clickable(onClick = {
            context.startActivity(Intent(context, ProductListActivity::class.java))
        })
    ) {
        Image(
            painter = painterResource(com.mycompose.app.R.drawable.ic_faq_new),
            contentDescription = "",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = msg.author, color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleSmall
            )
            Spacer(Modifier.height(4.dp))
            Surface(shape = MaterialTheme.shapes.medium, shadowElevation = 1.dp) {
                Text(
                    text = msg.body, modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewMessageCard() {
    MessageCard(
        msg = Message("Lexi", "Hey, take a look at Jetpack Compose, it's great!")
    )
}