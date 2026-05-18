package com.mim.babelio.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mim.babelio.ui.theme.BrownPrimary
import com.mim.babelio.ui.theme.ButtonBorder
import com.mim.babelio.ui.theme.CardWhite
import com.mim.babelio.ui.theme.TextPrimary
import com.mim.babelio.ui.theme.TextSecondary

/**
 * Branded text field used across all BookNest forms.
 * Handles label, leading icon, optional password-toggle trailing icon,
 * inline error message, and full keyboard/IME configuration.
 *
 * @param value                  Current field value.
 * @param onValueChange          Called on every keystroke.
 * @param label                  Bold label rendered above the field.
 * @param placeholder            Muted hint text inside the field when empty.
 * @param leadingIcon            Icon on the left inside the field.
 * @param isError                When true, border turns red and [errorMessage] appears.
 * @param errorMessage           Text shown below the field when [isError] is true.
 * @param visualTransformation   Use [PasswordVisualTransformation] to mask input.
 * @param trailingIcon           Optional icon on the right (e.g. eye for passwords).
 * @param onTrailingIconClick    Called when the trailing icon is tapped.
 * @param keyboardOptions        IME type and action.
 * @param keyboardActions        Callbacks for IME actions (Next, Done, etc.).
 */
@Composable
fun BookNestTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    leadingIcon: ImageVector,
    isError: Boolean                           = false,
    errorMessage: String                       = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: ImageVector?                 = null,
    onTrailingIconClick: (() -> Unit)?         = null,
    keyboardOptions: KeyboardOptions           = KeyboardOptions.Default,
    keyboardActions: KeyboardActions           = KeyboardActions.Default,
    modifier: Modifier                         = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text       = label,
            fontSize   = 14.sp,
            fontWeight = FontWeight.Medium,
            color      = TextPrimary,
            modifier   = Modifier.padding(bottom = 6.dp),
        )

        OutlinedTextField(
            value                = value,
            onValueChange        = onValueChange,
            modifier             = Modifier
                .fillMaxWidth()
                .height(56.dp),
            placeholder          = {
                Text(
                    text     = placeholder,
                    fontSize = 14.sp,
                    color    = TextSecondary.copy(alpha = 0.7f),
                )
            },
            leadingIcon          = {
                Icon(
                    imageVector        = leadingIcon,
                    contentDescription = null,
                    tint               = if (isError) MaterialTheme.colorScheme.error
                    else TextSecondary,
                    modifier           = Modifier.size(20.dp),
                )
            },
            trailingIcon         = trailingIcon?.let {
                {
                    IconButton(onClick = { onTrailingIconClick?.invoke() }) {
                        Icon(
                            imageVector        = it,
                            contentDescription = if (visualTransformation == VisualTransformation.None)
                                "Hide password" else "Show password",
                            tint               = TextSecondary,
                            modifier           = Modifier.size(20.dp),
                        )
                    }
                }
            },
            visualTransformation = visualTransformation,
            keyboardOptions      = keyboardOptions,
            keyboardActions      = keyboardActions,
            singleLine           = true,
            isError              = isError,
            shape                = RoundedCornerShape(12.dp),
            colors               = OutlinedTextFieldDefaults.colors(
                focusedBorderColor      = BrownPrimary,
                unfocusedBorderColor    = ButtonBorder,
                errorBorderColor        = MaterialTheme.colorScheme.error,
                focusedContainerColor   = CardWhite,
                unfocusedContainerColor = Color(0xFFF5EEE6),
                cursorColor             = BrownPrimary,
            ),
        )

        if (isError && errorMessage.isNotBlank()) {
            Text(
                text     = errorMessage,
                fontSize = 12.sp,
                color    = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(start = 4.dp, top = 4.dp),
            )
        }
    }
}