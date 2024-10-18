package tech.xuzheng.forwork.memoserverkt.api.response

import tech.xuzheng.forwork.memoserverkt.api.API

data class Response<T>(
    val success: Boolean,
    val message: String,
    val data: T?
)

fun <T> API.success(
    message: String = "success",
    block: () -> T
): Response<T> = Response(true, message, block())

fun <T> API.failure(message: String = "failure"): Response<T> =
    Response(false, message, null)
