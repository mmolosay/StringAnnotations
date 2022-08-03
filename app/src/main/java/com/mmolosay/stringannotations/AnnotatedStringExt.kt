package com.mmolosay.stringannotations

import android.content.Context
import android.text.Spanned
import android.text.style.ClickableSpan
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

/*
 * Extensions for convenience use.
 */

/**
 * Returns [Spanned] string, associated with a specified string resource [id] with `<annotation>`s.
 *
 * @throws IllegalStateException if library was not configured.
 */
fun Context.getAnnotatedString(
    @StringRes id: Int,
    clickables: List<ClickableSpan> = emptyList(),
    vararg formatArgs: Any
): Spanned =
    AnnotatedStrings.process(this, id, clickables, *formatArgs)

/**
 * Returns [Spanned] string, associated with a specified string resource [id] with `<annotation>`s.
 *
 * Receiver [Fragment] must be attached to context, otherwise [IllegalStateException] will be thrown.
 *
 * @throws IllegalStateException if fragment was not attached to context.
 * @throws IllegalStateException if library was not configured.
 */
fun Fragment.getAnnotatedString(
    @StringRes id: Int,
    clickables: List<ClickableSpan> = emptyList(),
    vararg formatArgs: Any
): Spanned =
    requireContext().getAnnotatedString(id, clickables, *formatArgs)