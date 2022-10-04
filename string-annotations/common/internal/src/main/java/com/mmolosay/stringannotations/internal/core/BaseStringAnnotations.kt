package com.mmolosay.stringannotations.internal.core

import com.mmolosay.stringannotations.core.StringAnnotations
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
 * Base implementaiton of [StringAnnotations] with dependencies initialization logic.
 */
public abstract class BaseStringAnnotations<D : BaseStringAnnotations.Dependencies> :
    StringAnnotations<D> {

    /**
     * Current dependencies of the library.
     *
     * Its `internal`, since the library utilises `Template Method` pattern,
     * and all necessary dependencies will be provided to high-level API.
     */
    public val dependencies: D
        get() = getDependenciesInternal()

    private var _dependencies: D? = null

    /**
     * Retrieves current [dependencies].
     * If it's `null`, then it's going to be initialized with default ones.
     */
    private fun getDependenciesInternal(): D {
        if (!isConfigured) {
            val dependencies = makeDefaultDependencies()
            configure(dependencies)
        }
        return requireNotNull(_dependencies)
    }

    /**
     * Assembles default dependencies.
     */
    protected abstract fun makeDefaultDependencies(): D

    // region StringAnnotations

    public override val isConfigured: Boolean
        get() = (_dependencies != null)

    public override fun configure(dependencies: D) {
        _dependencies = dependencies
    }

    public override fun dispose() {
        _dependencies = null
    }

    // endregion

    /**
     * Dependencies of the library.
     */
    public abstract class Dependencies {
        public abstract val processor: AnnotationProcessor<*>
    }

    /**
     * [Dependencies] builder.
     */
    protected interface DependenciesBuilder<S> {
        public fun annotationProcessor(instance: AnnotationProcessor<S>): DependenciesBuilder<S>
        public fun build(): Dependencies
    }
}