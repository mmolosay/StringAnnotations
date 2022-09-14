package com.mmolosay.stringannotations.parser

import android.content.Context
import com.mmolosay.stringannotations.core.Token

/**
 * Returns string annotation token as it is.
 */
public object AsIsTokenParser : TokenParser<Token> {

    override fun parse(context: Context, token: Token): Token =
        token
}