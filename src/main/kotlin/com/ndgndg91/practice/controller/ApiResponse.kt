package com.ndgndg91.practice.controller

data class ApiResponse<T>(
    val body: T,
)

inline fun <reified T: Any> T.toApiResponse(): ApiResponse<T> {
    return ApiResponse(this)
}