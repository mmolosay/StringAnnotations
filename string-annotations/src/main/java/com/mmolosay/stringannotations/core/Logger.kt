package com.mmolosay.stringannotations.core

import android.util.Log

/*
 * Copyright 2022 Mikhail Malasai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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