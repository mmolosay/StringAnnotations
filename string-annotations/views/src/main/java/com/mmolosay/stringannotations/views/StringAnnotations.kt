package com.mmolosay.stringannotations.views

import com.mmolosay.stringannotations.internal.core.BaseStringAnnotations
import com.mmolosay.stringannotations.views.internal.ViewsAnnotationProcessor
import com.mmolosay.stringannotations.views.internal.ViewsSpan
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
public object StringAnnotations : BaseStringAnnotations<StringAnnotations.Dependecies>() {

    override fun makeDefaultDependencies(): Dependecies =
        DependenciesBuilder().build()

    public data class Dependecies(
        override val processor: ViewsAnnotationProcessor
    ) : Dependencies()

    public class DependenciesBuilder :
        BaseStringAnnotations.DependenciesBuilder<ViewsSpan> {

        private var processor: ViewsAnnotationProcessor? = null

        override fun annotationProcessor(instance: ViewsAnnotationProcessor): DependenciesBuilder =
            apply {
                this.processor = instance
            }

        override fun build(): Dependecies =
            Dependecies(
                processor = processor ?: MasterAnnotationProcessor()
            )
    }
}