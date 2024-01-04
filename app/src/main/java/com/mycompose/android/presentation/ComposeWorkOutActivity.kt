package com.mycompose.android.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.google.gson.Gson
import com.mycompose.android.presentation.base.BaseActivity
import com.mycompose.android.ui.theme.spacing
import com.mycompose.app.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

class ComposeWorkOutActivity : BaseActivity() {

    private val flowViewModel: FlowViewModel by viewModels()

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val booleanPayment: Boolean = showMessage(true)
        // returning boolean value
        val stringMessage: String = showMessage(true)
        //returning string value
        println("Your Payment: $booleanPayment \nMessage: $stringMessage")

        val sum = calculate(10, 5, ::add)

        higherOrdFun(::sub)

        flowGeneralCall()

        flowWithCoroutine()

        setContent {
            Scaffold { innerPadding ->
                Row(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
                ) {

                    renderUIForFlow()

                }
            }
        }

    }

    @ExperimentalAnimationApi
    @Composable
    fun renderUIForFlow() {

        var liveData by remember { mutableStateOf("Hello LiveData") }

        var stateFlow by remember { mutableStateOf("Hello StateFlow") }

        val flow by flowViewModel.stateFlow.collectAsState()

        val sharedFlow by flowViewModel.sharedFlow.collectAsState("Hello SharedFlow")

        val currentContext = LocalContext.current

        Column(
            modifier = Modifier
                .padding(MaterialTheme.spacing.medium)
                .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            flowViewModel.liveData.value?.let { Text(text = it) }

            Button(shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.background)),
                onClick = { flowViewModel.triggerLiveData() }) {
                Text(text = "LiveData")
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(text = flow)
            Button(shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.background)),
                onClick = { flowViewModel.triggerStateFlow() }) {
                Text(text = "StateFlow")
            }

            Spacer(modifier = Modifier.height(30.dp))
            Text(text = sharedFlow)
            Button(shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.background)),
                onClick = { flowViewModel.triggerSharedFlow() }) {
                Text(text = "ShareFlow")
            }

            //
            val counter by flowViewModel.counter.collectAsState()

            CounterUpdate(count = counter,
                onAdd = { flowViewModel.incrementCounter() },
                onRemove = { flowViewModel.decrementCounter() })

        }


    }

    @ExperimentalAnimationApi
    @Composable
    fun CounterUpdate(count: Int, onAdd: () -> Unit, onRemove: () -> Unit) {

       /* Row {

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            Button(onClick = { onAdd() },shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.background)),) {
                Text(text = "Add")
            }

            Text(text = "Counter Value: $count")

            Button(onClick = { onRemove() },shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.background))) {
                Text(text = "Remove")
            }
        }
*/

        Column (modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally){
            var animatedCount by remember { mutableIntStateOf(0) }
            AnimatedComposable(count = animatedCount)

            Button(shape = RoundedCornerShape(4.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.background)),
                onClick = { animatedCount++ }) {
                Text(text = "Increment")
            }
        }

    }

    @ExperimentalAnimationApi
    @SuppressLint("UnusedContentLambdaTargetStateParameter")
    @Composable
    fun AnimatedComposable(
        count: Int, modifier: Modifier = Modifier,
        style: TextStyle = MaterialTheme.typography.bodyMedium
    ) {
        var oldCount by remember { mutableIntStateOf(count) }

        SideEffect {
            oldCount = count
        }

        Row(modifier = modifier) {
            val countString = count.toString()
            val oldCountString = oldCount.toString()

            for (i in countString.indices) {
                val oldChar = oldCountString.getOrNull(i)
                val newChar = countString[i]
                val char = if (oldChar == newChar) oldCountString[i] else countString[i]

                AnimatedContent(targetState = char, label = "",
                    transitionSpec = {
                        slideInVertically { it } with slideOutVertically { -it }
                    }) {
                    Text(text = char.toString(), style = style, softWrap = false)
                }
            }

        }


    }


    fun coroutineRunBlock() = runBlocking {
        val totalTime = measureTimeMillis {

            val delay1 = delayFunction1()
            val delay2 = delayFunction2()

            println("Total time taken: ${delay1 + delay2} ms")
        }
        println("Done")
    }

    fun coroutineRunBlockAwait() = runBlocking {
        val totalTime = measureTimeMillis {
            val delay1 = async { delayFunction1() }
            val delay2 = async { delayFunction2() }

            println("Total time taken: ${delay1.await() + delay2.await()} ms")
        }

        println("Done")
    }


    suspend fun delayFunction1(): Long {
        val delayTime = 1000L
        delay(delayTime)
        return delayTime
    }

    suspend fun delayFunction2(): Long {
        val delayTime = 500L
        delay(delayTime)
        return delayTime
    }


    fun calculate(x: Int, y: Int, result: (Int, Int) -> Int): Int {
        return result(x, y)
    }

    fun add(a: Int, b: Int): Int {
        return a + b
    }


    fun sub(a: Int, b: Int): Int {
        return a - b
    }

    fun calculatefunRet(): ((Int, Int) -> Int) {
        return ::sub
    }

    fun higherOrdFun(result: (Int, Int) -> Int) {
        val add = result(3, 4)

    }


    inline fun <reified T : Any> String.convertToObject(): T? {
        return Gson()?.fromJson(this, T::class.java)
    }


    // Without reified keyword and inline function
    inline infix fun <reified T> showMessage(payment: Boolean): T {
        return when (T::class) {
            Boolean::class -> payment as T
            String::class -> "Sorry!! You don’t have enough balance to proceed" as T
            else -> "Please enter valid type" as T
        }
    }


    fun callCoroutine() {

        val scope = CoroutineScope(Job() + Dispatchers.Main)

        scope.launch {

        }
        scope.cancel()
    }


    fun flowGeneralCall() {
        val flow = flow {
            (0..1).forEach {
                delay(500)
                emit(it)
            }
        }.flowOn(Dispatchers.Default)

        CoroutineScope(Dispatchers.Main).launch {
            flow.collect() {
                Log.e("FlowOf build:", it.toString())
            }
        }
    }


    fun flowWithCoroutine() {
        GlobalScope.launch {
            flowBuilders()
        }

    }


    private suspend fun flowBuilders() {
        coroutineScope {
            flowOf(1, 2, 3, 4).collect {
                Log.e("Flowof build:", it.toString())
            }

            (1..10).asFlow().collect() {
                Log.e("asFlow build:", it.toString())
            }

            channelFlow {
                (0..10).forEach() {
                    send(it)
                }
            }.collect {
                Log.e("channelFlow build:", it.toString())
            }

        }

    }

}