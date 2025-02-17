package com.twitter.domain.useCase

import com.twitter.core.utils.Constants.TWITTER_LIMIT
import java.text.Normalizer
import javax.inject.Inject

class TwitterCountUseCase @Inject constructor() {

    fun calculateRemainingCharacters(input: String): Int {
        val adjustedCount = countTwitterCharacters(input)
        return TWITTER_LIMIT - adjustedCount
    }

    fun calculateCharacterCount(input: String): Int {
        return countTwitterCharacters(input)
    }

    fun isTweetValid(input: String): Boolean {
        return input.isNotBlank() && calculateRemainingCharacters(input) >= 0
    }

    private fun countTwitterCharacters(input: String): Int {
        val normalizedInput = Normalizer.normalize(input, Normalizer.Form.NFC)

        return normalizedInput.foldIndexed(0) { index, count, char ->
            if (Character.isSurrogate(char) && index + 1 < normalizedInput.length) {
                count + 2 // Emojis or extended code points count as 2
            } else {
                count + 1
            }
        }
    }
}
