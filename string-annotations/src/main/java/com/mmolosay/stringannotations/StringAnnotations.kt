package com.mmolosay.stringannotations

import com.mmolosay.stringannotations.processor.AnnotationProcessor
import com.mmolosay.stringannotations.processor.DefaultAnnotationProcessor

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
public object StringAnnotations {

    /**
     * Whether the library is configured or not.
     */
    public val isConfigured: Boolean
        get() = (_dependencies != null)

    /**
     * Current dependencies of the library.
     *
     * Its `internal`, since the library utilises `Template Method` pattern,
     * and all necessary dependencies will be provided to high-level API.
     */
    internal val dependencies: Dependencies
        get() = getDependenciesInternal()

    private var _dependencies: Dependencies? = null

    // region Configure & Dispose

    /**
     * Configures the library's dependencies.
     *
     * Call to this method is not necessary, since the library will be configured with default
     * dependencies at the moment of the very first call to its API.
     * You should use this method, if you want to change default behaviour and provide custom
     * dependencies.
     */
    public fun configure(
        annotationProcessor: AnnotationProcessor
    ) {
        val dependencies = makeDependencies(
            annotationProcessor
        )
        configureInternal(dependencies)
    }

    /**
     * Disposes all held objects.
     *
     * Should be called, when the library is never going to be needed again.
     */
    public fun dispose() {
        _dependencies = null
    }

    // endregion

    private fun configureInternal(dependencies: Dependencies) {
        _dependencies = dependencies
    }

    /**
     * Retrieves current [dependencies].
     * If it's `null`, them it's going to be initialized with default ones.
     */
    private fun getDependenciesInternal(): Dependencies {
        if (!isConfigured) {
            configureInternal(makeDefaultDependencies())
        }
        return requireNotNull(_dependencies)
    }

    /**
     * Assembles default [Dependencies].
     */
    private fun makeDefaultDependencies(): Dependencies =
        DependenciesImpl(
            annotationProcessor = DefaultAnnotationProcessor()
        )

    /**
     * Assembles [Dependencies] from specified parameters.
     */
    private fun makeDependencies(
        annotationProcessor: AnnotationProcessor
    ): Dependencies =
        DependenciesImpl(
            annotationProcessor
        )

    /**
     * Internal dependencies of the library.
     */
    internal interface Dependencies {
        val annotationProcessor: AnnotationProcessor
    }

    /**
     * Internal implementation of [Dependencies].
     * Should not be used as explicit type.
     */
    internal data class DependenciesImpl(
        override val annotationProcessor: AnnotationProcessor
    ) : Dependencies
}