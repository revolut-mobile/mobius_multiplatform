//package com.example.mobiusapp
//
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.launch
//
//class Main {
//
//    fun main(args: Array<String>) {
//
//        printDelayed("1")
//        printDelayed("2")
//        printDelayed("3")
//        printDelayed("4")
//        printDelayed("5")
//
//        Thread.sleep(3000)
//    }
//
//    fun printDelayed(arg: String) {
//        GlobalScope.launch {
//            delay(2000)
//
//            println(arg)
//        }
//    }
//}