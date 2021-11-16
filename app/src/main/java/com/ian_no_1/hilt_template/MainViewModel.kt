package com.ian_no_1.hilt_template

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    val channel = Channel<Int>(capacity = UNLIMITED )
    val sharedFlow = MutableSharedFlow<Int>()
    val hahaFLow = sharedFlow.asSharedFlow()

//TODO: don't know why collector doesn't triggered
    init {
        Log.d("haha", "waaaa")
        viewModelScope.launch {
            for (i in 0..10) {
                delay(1000)
                Log.d("aaa", "send--> $i")
                sharedFlow.tryEmit(i)
            }
        }

        viewModelScope.launch {
            hahaFLow.collect {
                delay(2000)
                Log.d("aaa", "recv A<-- $it")
            }
        }

        viewModelScope.launch {
            hahaFLow.collect {
                delay(2000)
                Log.d("aaa", "recv B<-- $it")

            }
        }
    }


// This one is ok
//    init {
//        Log.d("haha", "waaaa")
//        viewModelScope.launch {
//            for (i in 0..10) {
//                delay(1000)
//                Log.d("aaa", "send--> $i")
//                channel.trySend(i)
//            }
//        }
//
//        viewModelScope.launch {
//            channel.consumeEach {
//                delay(2000)
//                Log.d("aaa", "recv A<-- $it")
//
//            }
//        }
//
//        viewModelScope.launch {
//            channel.consumeEach {
//                delay(2000)
//                Log.d("aaa", "recv B<-- $it")
//
//            }
//        }
//    }


    fun haha(){
        Log.d("aaa", "haha")
    }
}