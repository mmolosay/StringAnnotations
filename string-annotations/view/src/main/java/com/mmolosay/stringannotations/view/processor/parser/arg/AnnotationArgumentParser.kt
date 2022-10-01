package com.mmolosay.stringannotations.view.processor.parser.arg

import com.mmolosay.stringannotations.common.shared.args.Arguments
import com.mmolosay.stringannotations.view.processor.token.Token

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
 * Specifies way of parsing value placeholder into some corresponding argument.
 */
public interface AnnotationArgumentParser {

    /**
     * Tries to parse [token] as argument placeholder and obtain its argument from [args].
     */
    public fun <V> parse(token: Token, args: Arguments<V>): V?
}