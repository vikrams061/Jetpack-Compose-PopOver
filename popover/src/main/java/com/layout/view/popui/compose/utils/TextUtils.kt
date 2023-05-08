package com.layout.view.popui.compose.utils

import android.content.res.Resources.NotFoundException
import androidx.annotation.PluralsRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import org.w3c.dom.Text



/**
 * Formats the string necessary for grammatically correct pluralization
 * of the given resource ID for the given quantity, using the given arguments.
 * Note that the string is selected based solely on grammatical necessity,
 * and that such rules differ between languages. Do not assume you know which string
 * will be returned for a given quantity. See
 * <a href="{@docRoot}guide/topics/resources/string-resource.html#Plurals">String Resources</a>
 * for more detail.
 *
 * <p>Substitution of format arguments works as if using
 * {@link java.util.Formatter} and {@link java.lang.String#format}.
 * The resulting string will be stripped of any styled text information.
 *
 * @param id The desired resource identifier, as generated by the aapt
 *           tool. This integer encodes the package, type, and resource
 *           entry. The value 0 is an invalid identifier.
 * @param quantity The number used to get the correct string for the current language's
 *           plural rules.
 * @param formatArgs The format arguments that will be used for substitution.
 *
 * @throws [NotFoundException] Throws NotFoundException if the given ID does not exist.
 *
 * @return String The string data associated with the resource,
 * stripped of styled text information.
 */
@Composable
@ReadOnlyComposable
fun quantityString(
    @PluralsRes id: Int,
    quantity: Int,
    vararg formatArgs: Any,
): String {
    val resources = LocalContext.current.resources
    return resources.getQuantityString(
        id, quantity, *formatArgs
    )
}