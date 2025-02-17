package com.twitter.core.model

import java.io.IOException

data class GenericException(
    override val message: String?,
    val hasUserFriendlyMessage: Boolean
) : IOException()