package com.twitter.core.utils.extensions

import com.twitter.core.model.GenericException
import org.json.JSONException
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

fun <T> Response<T>.handleResponse(): T {
    if (!this.isSuccessful) {
        val errorBody = this.errorBody()?.string().orEmpty()

        // Attempt to parse error JSON and extract meaningful message
        val extractedMessage = errorBody.extractErrorMessage() ?: this.message()

        throw GenericException(
            message = extractedMessage,
            hasUserFriendlyMessage = true
        )
    }

    val responseBody = this.body()
        ?: throw GenericException(
            message = "Empty response body received from server.",
            hasUserFriendlyMessage = true
        )

    return responseBody
}

private fun String.extractErrorMessage(): String? {
    return try {
        val jsonObject = JSONObject(this)
        jsonObject.optString("status_message", null)
    } catch (e: JSONException) {
        null
    }
}

suspend fun <T> handleCall(block: suspend () -> Response<T>): T {
    return try {
        block().handleResponse()
    } catch (e: GenericException) {
        // Catching exception from core.utils.extensions.handleResponse and rethrowing with the same message
        throw GenericException(
            message = e.message ?: "An error occurred while processing the request.",
            hasUserFriendlyMessage = e.hasUserFriendlyMessage
        )
    } catch (e: IOException) {
        throw GenericException(
            message = e.localizedMessage ?: "Please check your internet connection and try again.",
            hasUserFriendlyMessage = true
        )
    } catch (e: HttpException) {
        throw GenericException(
            message = "HTTP ${e.code()} Error: ${e.response()?.errorBody()?.string() ?: e.message()}",
            hasUserFriendlyMessage = true
        )
    } catch (e: Exception) {
        throw GenericException(
            message = e.localizedMessage ?: "An unexpected error occurred.",
            hasUserFriendlyMessage = false
        )
    }
}

