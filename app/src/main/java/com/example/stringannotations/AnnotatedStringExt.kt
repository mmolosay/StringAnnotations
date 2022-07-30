package com.example.stringannotations

import android.content.Context
import android.text.Spanned
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

/**
 * Returns [Spanned] string, associated with a specified string resource [id] with `<annotation>`s.
 *
 * See [AnnotationType] for supported `<annotation>` types.
 */
fun Context.getAnnotatedString(
    @StringRes id: Int,
    vararg formatArgs: Any
): Spanned =
    AnnotatedStrings.format(this, id, *formatArgs)

/**
 * Returns [Spanned] string, associated with a specified string resource [id] with `<annotation>`s.
 *
 * Receiver [Fragment] must be attached to context, otherwise [IllegalStateException] will be thrown.
 *
 * See [AnnotationType] for supported `<annotation>` types.
 */
fun Fragment.getAnnotatedString(
    @StringRes id: Int,
    vararg formatArgs: Any
): Spanned =
    requireContext().getAnnotatedString(id, *formatArgs)