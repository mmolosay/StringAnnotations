package io.github.mmolosays.stringannotations.compose

import io.github.mmolosays.stringannotations.args.Arguments
import io.github.mmolosays.stringannotations.compose.args.Arguments
import io.github.mmolosays.stringannotations.compose.args.Clickable
import io.github.mmolosays.stringannotations.compose.processor.ComposeSpan
import io.github.mmolosays.stringannotations.processor.AnnotationProcessor

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

/*
 * Typealiases for convenience of use.
 */

/**
 * Definition of [Arguments] for Compose UI.
 */
public typealias ComposeArguments = Arguments<Clickable>

/**
 * Definition of [AnnotationProcessor] for Compose UI.
 */
public typealias ComposeAnnotationProcessor = AnnotationProcessor<ComposeArguments, ComposeSpan>