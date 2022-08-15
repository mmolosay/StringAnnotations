package com.mmolosay.stringannotations.core

import android.util.Log

/**
 * Logs library events, that require client's attention.
 */
internal object Logger {

    /**
     * Library tag. Used for logging events.
     */
    private const val TAG = "StringAnnotations"

    /**
     * Logs message of inability to parse annotation with specified [type] and [value]
     * into valid span with [Log.WARN] priority.
     */
    fun warnInvalidAnnotation(
        type: String,
        value: String
    ) {
        w("Annotation with attribute=\"$type\" and value=\"$value\" cannot be parsed into valid span")
    }

    /**
     * Logs specified [message] with library tag and [Log.WARN] priority.
     *
     * All library internal logging should go through this method.
     */
    fun w(message: String) {
        Log.w(TAG, message)
    }
}