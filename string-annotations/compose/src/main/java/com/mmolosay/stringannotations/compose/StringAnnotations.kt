package com.mmolosay.stringannotations.compose

import com.mmolosay.stringannotations.compose.processor.ComposeSpan
import com.mmolosay.stringannotations.compose.processor.MasterAnnotationProcessor
import com.mmolosay.stringannotations.core.BaseStringAnnotations

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
        DependenciesBuilder().build()

    public data class Dependencies(
        override val processor: ComposeAnnotationProcessor
    ) : BaseStringAnnotations.Dependencies()

    public class DependenciesBuilder :
        BaseStringAnnotations.DependenciesBuilder<ComposeArguments, ComposeSpan> {

        private var processor: ComposeAnnotationProcessor? = null

        override fun annotationProcessor(instance: ComposeAnnotationProcessor): DependenciesBuilder =
            apply {
                this.processor = instance
            }

        override fun build(): Dependencies =
            Dependencies(
                processor = processor ?: MasterAnnotationProcessor()
            )
    }
}