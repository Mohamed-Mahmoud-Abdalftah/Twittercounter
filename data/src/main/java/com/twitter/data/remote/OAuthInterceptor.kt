package com.twitter.data.remote

import com.twitter.core.utils.Constants.ACCESS_TOKEN
import com.twitter.core.utils.Constants.API_KEY
import com.twitter.core.utils.Constants.API_KEY_SECRET
import com.twitter.core.utils.Constants.ACCESS_TOKEN_SECRET
import okhttp3.Interceptor
import okhttp3.Response
import java.net.URLEncoder
import java.util.UUID
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import javax.inject.Inject

class OAuthInterceptor @Inject constructor(
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val authorizationHeader =
            generateAuthorizationHeader(request.method, request.url.toString())

        val modifiedRequest =
            request.newBuilder().addHeader("Authorization", authorizationHeader).build()

        return chain.proceed(modifiedRequest)
    }

    private fun generateAuthorizationHeader(method: String, url: String): String {
        val nonce = generateNonce()
        val timestamp = generateTimestamp()

        val baseString = createBaseString(method, url, nonce, timestamp)
        val signature = computeSignature(baseString)

        return buildAuthorizationHeader(nonce, timestamp, signature)
    }

    private fun generateNonce(): String = UUID.randomUUID().toString().replace("-", "")

    private fun generateTimestamp(): String = (System.currentTimeMillis() / 1000).toString()

    private fun createBaseString(
        method: String, url: String, nonce: String, timestamp: String
    ): String {
        val parameters = buildParameters(nonce, timestamp)
        return "$method&${encode(url)}&${encode(parameters)}"
    }

    private fun buildParameters(nonce: String, timestamp: String): String {
        return listOf(
            "oauth_consumer_key" to API_KEY,
            "oauth_nonce" to nonce,
            "oauth_signature_method" to "HMAC-SHA1",
            "oauth_timestamp" to timestamp,
            "oauth_token" to ACCESS_TOKEN,
            "oauth_version" to "1.0"
        ).joinToString("&") { "${it.first}=${encode(it.second)}" }
    }

    private fun computeSignature(baseString: String): String {
        val signingKey = "$API_KEY_SECRET&$ACCESS_TOKEN_SECRET"
        val mac = Mac.getInstance("HmacSHA1")
        val secretKeySpec = SecretKeySpec(signingKey.toByteArray(), "HmacSHA1")
        mac.init(secretKeySpec)
        val signatureBytes = mac.doFinal(baseString.toByteArray())
        return android.util.Base64.encodeToString(signatureBytes, android.util.Base64.NO_WRAP)
    }

    private fun buildAuthorizationHeader(
        nonce: String, timestamp: String, signature: String
    ): String {
        return "OAuth " + "oauth_consumer_key=\"${encode(API_KEY)}\", " + "oauth_nonce=\"${
            encode(
                nonce
            )
        }\", " + "oauth_signature=\"${encode(signature)}\", " + "oauth_signature_method=\"HMAC-SHA1\", " + "oauth_timestamp=\"${
            encode(
                timestamp
            )
        }\", " + "oauth_token=\"${encode(ACCESS_TOKEN)}\", " + "oauth_version=\"1.0\""
    }

    private fun encode(value: String): String {
        return URLEncoder.encode(value, "UTF-8").replace("+", "%20")
    }
}
