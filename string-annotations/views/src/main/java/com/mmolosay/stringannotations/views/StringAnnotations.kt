package com.mmolosay.stringannotations.views

import com.mmolosay.stringannotations.core.BaseStringAnnotations
import com.mmolosay.stringannotations.processor.AnnotationProcessor
import com.mmolosay.stringannotations.views.internal.ViewAnnotationProcessor
import com.mmolosay.stringannotations.views.processor.MasterAnnotationProcessor

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
 * Core component, that represents the library itself.
 *
 * Call [StringAnnotations.configure], if you want to alter library's default behaviour.
 *
 * Call [StringAnnotations.dispose], when you're done working with library and ready to
 * free its dependencies.
 */
public object StringAnnotations : BaseStringAnnotations<StringAnnotations.Dependencies>() {

    override fun makeDefaultDependencies(): Dependencies =
        Dependencies.Builder().build()

    /**
     * Dependencies of the library.
     */
    public interface Dependencies {

        public val processor: ViewAnnotationProcessor

        /**
         * Provides convenient interface for assembling library's [Dependencies].
         */
        public class Builder {

            private var processor: ViewAnnotationProcessor? = null

            /**
             * Specifies [AnnotationProcessor] instance to be used.
             */
            public fun annotationProcessor(instance: ViewAnnotationProcessor): Builder =
                apply {
                    this.processor = instance
                }

            /**
             * Assembles [Dependencies].
             */
            public fun build(): Dependencies =
                DependenciesImpl(
                    processor = processor ?: MasterAnnotationProcessor()
                )
        }
    }

    /**
     * Internal implementation of [Dependencies].
     * Should not be used as explicit type.
     */
    internal data class DependenciesImpl(
        override val processor: ViewAnnotationProcessor
    ) : Dependencies
}