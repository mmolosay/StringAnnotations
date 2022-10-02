package com.mmolosay.stringannotations.internal

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
 * Core component, that represents the library itself with some dependencies of type [D].
 *
 * Call [configure], if you want to alter library's default behaviour.
 *
 * Call [dispose], when you're done working with library and ready to
 * free its dependencies.
 */
public interface StringAnnotations<D> {

    /**
     * Whether the library is configured or not.
     */
    public val isConfigured: Boolean

    /**
     * Configures the library's dependencies.
     *
     * Call to this method is not necessary, since the library will be configured with default
     * dependencies at the moment of the very first call to its API.
     * You should use this method, if you want to change default behaviour and provide custom
     * dependencies.
     */
    public fun configure(dependencies: D)

    /**
     * Disposes all held objects.
     *
     * Should be called, when the library is never going to be needed again.
     */
    public fun dispose()
}