package com.mmolosay.stringannotations.compose

import com.mmolosay.stringannotations.args.values.AnnotationValues
import com.mmolosay.stringannotations.compose.args.Clickable
import com.mmolosay.stringannotations.compose.processor.ComposeSpan
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

/*
 * Typealiases for convenience of use.
 */

/**
 * [AnnotationValues] for Compose UI.
 */
public typealias ComposeAnnotationValues = AnnotationValues<Clickable>

/**
 * [AnnotationProcessor] for Compose UI.
 */
public typealias ComposeAnnotationProcessor = AnnotationProcessor<ComposeSpan>