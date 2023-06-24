package io.github.mmolosays.stringannotations.compose

import io.github.mmolosays.stringannotations.compose.processor.MasterAnnotationProcessor
import io.github.mmolosays.stringannotations.core.BaseStringAnnotations
import io.github.mmolosays.stringannotations.processor.parser.CommonValueParser

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
 * Core component, that represents the library itself.
 *
 * Call [StringAnnotations.configure], if you want to alter library's default behaviour.
 *
 * Call [StringAnnotations.dispose], when you're done working with library and ready to
 * free its dependencies.
 */
public object StringAnnotations : BaseStringAnnotations<StringAnnotations.Dependencies>() {

    override fun makeDefaultDependencies(): Dependencies =
        Dependencies(
            processor = MasterAnnotationProcessor(defaultValueParser = CommonValueParser),
        )

    public data class Dependencies(
        override val processor: ComposeAnnotationProcessor,
    ) : BaseStringAnnotations.Dependencies
}