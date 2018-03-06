package com.revolut.domain.models

import platform.darwin.dispatch_async
import platform.darwin.dispatch_get_main_queue

actual class RevolutCardImpl(override val id: String) : RevolutCard