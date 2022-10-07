package com.mmolosay.stringannotations.views.args

import com.mmolosay.stringannotations.args.Arguments
import com.mmolosay.stringannotations.internal.args.AbstractArgumentsBuilder

internal class ViewsArgumentsBuilder : AbstractArgumentsBuilder<Clickable>() {

    override fun build(): ViewsArguments =
        object : ViewsArguments {
            override val colors = Arguments.Colors(this@ViewsArgumentsBuilder.colors)
            override val clickables = Arguments.Clickables(this@ViewsArgumentsBuilder.clickables)
            override val styles = Arguments.Styles(this@ViewsArgumentsBuilder.styles)
            override val sizes = Arguments.Sizes(this@ViewsArgumentsBuilder.sizes)
        }
}