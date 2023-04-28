package io.github.mmolosays.stringannotations.utils

import android.text.Annotation
import android.util.Log

/*
 * Copyright 2023 Mikhail Malasai
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
 *
 * All library's internal logging should go through this component.
 */
public object Logger {

    /**
     * Library tag. Used for logging events.
     */
    private const val TAG = "StringAnnotations"

    public fun w(message: String) {
        Log.w(TAG, message)
    }

    // region Specific messages

    public fun logUnableToParse(annotation: Annotation): Unit =
        w(
            "Annotation with attribute=\"${annotation.key}\" and value=\"${annotation.value}\" " +
                "cannot be parsed into valid span. " +
                "Make sure attribute and its value are correct and supported."
        )

    // endregion
}