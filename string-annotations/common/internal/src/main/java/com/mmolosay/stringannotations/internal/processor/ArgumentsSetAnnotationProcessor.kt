package com.mmolosay.stringannotations.internal.processor

import com.mmolosay.stringannotations.args.ArgumentsSet
import com.mmolosay.stringannotations.internal.Logger
import com.mmolosay.stringannotations.processor.BaseAnnotationProcessor
import com.mmolosay.stringannotations.processor.parser.arg.AnnotationArgumentParser
import com.mmolosay.stringannotations.processor.parser.arg.DefaultAnnotationArgumentParser

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
 * [BaseAnnotationProcessor], that works with [ArgumentsSet] as type of annotation arguments.
 *
 * Employs [DefaultAnnotationArgumentParser] for [argParser].
 */
public abstract class ArgumentsSetAnnotationProcessor<V, S> :
    BaseAnnotationProcessor<V, ArgumentsSet, S>() {

    override val argParser: AnnotationArgumentParser = DefaultAnnotationArgumentParser(Logger)
}