package com.revolut.domain.repositories

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.util.*

class CallLoggingFeature(private val tag: String) {

    companion object Feature : HttpClientFeature<Config, CallLoggingFeature> {

        override val key: AttributeKey<CallLoggingFeature> =
            AttributeKey<CallLoggingFeature>("CallLoggingFeature")

        override fun prepare(block: Config.() -> Unit): CallLoggingFeature =
            Config().apply(block).let { CallLoggingFeature(it.tag) }

        override fun install(feature: CallLoggingFeature, scope: HttpClient) {

            scope.requestPipeline.intercept(HttpRequestPipeline.Render) { payload ->
                println("${feature.tag} request --> $payload")
            }

            scope.sendPipeline.intercept(HttpSendPipeline.Engine) { payload ->
                println("${feature.tag} send --> $payload")
            }

            scope.responsePipeline.intercept(HttpResponsePipeline.Receive) { payload ->
                println("${feature.tag} Receive response <-- $payload")
                println("${feature.tag} ${payload.response}")
            }

            scope.responsePipeline.intercept(HttpResponsePipeline.Parse) { payload ->
                println("${feature.tag} Parse response <-- $payload")
                println("${feature.tag} ${payload.response}")
            }

            scope.responsePipeline.intercept(HttpResponsePipeline.Transform) { payload ->
                println("${feature.tag} Transform response <-- $payload")
                println("${feature.tag} ${payload.response}")
            }

            scope.responsePipeline.intercept(HttpResponsePipeline.State) { payload ->
                println("${feature.tag} State response <-- $payload")
                println("${feature.tag} ${payload.response}")
            }

            scope.responsePipeline.intercept(HttpResponsePipeline.After) { payload ->
                println("${feature.tag} After response <-- $payload")
                println("${feature.tag} ${payload.response}")
            }

            scope.receivePipeline.intercept(HttpReceivePipeline.Before) { payload ->

                println("${feature.tag} receive <-- $payload")
                println("${feature.tag} status ${payload.status}")
                println("${feature.tag} request ${payload.call.request.url}")
                println("${feature.tag} response ${payload.call.response}")
            }

        }
    }

    class Config {
        var tag = "CallLogging"
    }
}