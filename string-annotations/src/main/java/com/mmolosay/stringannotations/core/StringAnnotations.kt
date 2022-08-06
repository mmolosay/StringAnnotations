package com.mmolosay.stringannotations.core

import com.mmolosay.stringannotations.processor.AnnotationProcessor

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
 * Collection of library settings and required dependincies.
 *
 * Call [StringAnnotations.configure] before any other interactions with library.
 *
 * Call [StringAnnotations.dispose], when you're done working with library and ready to
 * free its dependencies.
 */
public object StringAnnotations {

    internal var annotationProcessor: AnnotationProcessor? = null

    /**
     * Provides [Builder] instance to configure library dependencies.
     */
    public fun configure(): Builder =
        BuilderImpl

    /**
     * Disposes all held objects.
     * Should be called, when [StringAnnotations] is never needed again.
     */
    public fun dispose() {
        annotationProcessor = null
    }

    internal fun requireAnnotaitonProcessor(): AnnotationProcessor =
        annotationProcessor ?: throwUnconfiguredException()

    private fun throwUnconfiguredException(): Nothing =
        throw IllegalStateException(
            "StringAnnotations is not configured. " +
                "Make sure you have called StringAnnotations.configure() method."
        )

    /**
     * Defines dependencies of [StringAnnotations].
     * Make sure you have set all of them before working with library.
     */
    public sealed interface Builder {
        /**
         * Defines [AnnotationProcessor] to be used.
         */
        public fun annotationProcessor(processor: AnnotationProcessor): Builder
    }
}