package com.mycompose.android.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mycompose.android.presentation.base.BaseActivity
import com.mycompose.android.presentation.product.ProductListActivity
import com.mycompose.android.presentation.product.ProductViewModel
import com.mycompose.android.ui.theme.FirstComposeAppTheme
import com.mycompose.android.ui.theme.screen.CenteredImageAndText
import com.mycompose.android.ui.theme.screen.CustomCountDownSplashScreen
import com.mycompose.app.R


class MainActivity : BaseActivity() {

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.loading.value
            }
        }*/

        /*enableAutoRotation(this)

        // Check if auto-rotation is enabled
        val isAutoRotationEnabled = isAutoRotationEnabled(this)

        if (isAutoRotationEnabled) {
            // Auto-rotation is enabled
        } else {
            // Auto-rotation is disabled

        }*/

        /*requestWriteSettingsPermission(this)*/

        setContent {
            FirstComposeAppTheme {
                Scaffold { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        /* Conversation(messages = SampleData.conversationSample)*/
                        val currentContext = LocalContext.current
                        /*CountDownSplashScreen(modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.background)
                                .also { var modifier = it },
                            beforeFinished = {}
                        ){
                            startActivity(Intent(currentContext, LoginActivity::class.java))
                            finish()
                        }*/

                        ///
                        CustomCountDownSplashScreen(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.background),
                            beforeFinished = {
                                CenteredImageAndText(
                                    modifier = Modifier
                                        .fillMaxSize(0.4f)
                                        .align(Alignment.Center),
                                    imageDrawableRes = R.drawable.ic_faq_new,
                                    contentDescription = stringResource(id = R.string.app_name),
                                    text = stringResource(id = R.string.app_name),
                                    textStyle = MaterialTheme.typography.titleMedium.copy(
                                        color = contentColorFor(
                                            backgroundColor = MaterialTheme.colorScheme.background
                                        ),
                                        fontWeight = FontWeight.ExtraBold
                                    )
                                )
                            }
                        ) {
                             startActivity(Intent(currentContext, LoginActivity::class.java))
                             finish()
                            /*WaterCount(modifier = Modifier.padding(6.dp))*/
                        }
                        ////
                    }
                }
            }
        }

    }
}

@Composable
fun WaterCount(modifier: Modifier) {

    var count by rememberSaveable { mutableIntStateOf(0) }

    Column(modifier.padding(4.dp)) {

        if (count > 0) {
            var isShowTask by remember { mutableStateOf(true) }
            if (isShowTask) {
                WaterCountTaskItem(
                    taskName = "Have you taken your 15 minute walk today?",
                    onClose = { },
                    modifier = Modifier.padding(16.dp)
                )
            }

            Text(text = "You have had $count glasses.")
        }

        Button(onClick = { count++ }, Modifier.padding(top = 8.dp), enabled = count < 10) {
            Text(text = "Add One")
        }

    }
}

@Composable
fun WaterCountTaskItem(taskName: String, onClose: () -> Unit, modifier: Modifier) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {

        Text(text = taskName)
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = "Close")
        }

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
    Row(
        modifier = Modifier
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

fun setAutoOrientationEnabled(context: Context, enabled: Boolean) {
    Settings.System.putInt(
        context.contentResolver,
        Settings.System.ACCELEROMETER_ROTATION,
        if (enabled) 1 else 0
    )
}


fun enableAutoRotation(context: Context) {
    try {
        // Check if auto-rotate is already enabled
        val rotationSetting = Settings.System.getInt(
            context.contentResolver,
            Settings.System.ACCELEROMETER_ROTATION
        )

        // If auto-rotate is not enabled, enable it
        if (rotationSetting == 0) {
            Settings.System.putInt(
                context.contentResolver,
                Settings.System.ACCELEROMETER_ROTATION,
                1
            )
        }
    } catch (e: Settings.SettingNotFoundException) {
        e.printStackTrace()
    }
}

fun isAutoRotationEnabled(context: Context): Boolean {
    return try {
        Settings.System.getInt(
            context.contentResolver,
            Settings.System.ACCELEROMETER_ROTATION
        ) == 1
    } catch (e: Settings.SettingNotFoundException) {
        e.printStackTrace()
        false
    }
}

private fun requestWriteSettingsPermission(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (!Settings.System.canWrite(context)) {
            val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS)
            intent.data = Uri.parse("package:")
            context.startActivity(intent)
        }
    }
}


/*
@Preview
@Composable
fun PreviewMessageCard() {
    */
/*MessageCard(
        msg = Message("Lexi", "Hey, take a look at Jetpack Compose, it's great!")
    )*//*

    Conversation(messages = SampleData.conversationSample)
}*/
