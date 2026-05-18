package com.mim.babelio.ui.screens.authentication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mim.babelio.ui.components.AppIconWithBadge
import com.mim.babelio.ui.components.PrimaryButton
import com.mim.babelio.ui.theme.*

// ── Screen ────────────────────────────────────────────────────────────────────

/**
 * Sign-up screen — collects name, email, and password.
 *
 * @param onCreateAccount Called with (name, email, password) when the form is submitted.
 * @param onSignIn        Navigate back to the login screen.
 */
@Composable
fun SignUpScreen(
    onCreateAccount: (name: String, email: String, password: String) -> Unit = { _, _, _ -> },
    onSignIn: () -> Unit = {},
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundCream),
    ) {
        AnimatedVisibility(
            visible = visible,
            enter   = fadeIn(tween(500)) + slideInVertically(tween(500)) { it / 5 },
        ) {
            SignUpContent(
                onCreateAccount = onCreateAccount,
                onSignIn        = onSignIn,
            )
        }
    }
}

// ── Content ───────────────────────────────────────────────────────────────────

@Composable
private fun SignUpContent(
    onCreateAccount: (String, String, String) -> Unit,
    onSignIn: () -> Unit,
) {
    val focusManager = LocalFocusManager.current

    var name            by remember { mutableStateOf("") }
    var email           by remember { mutableStateOf("") }
    var password        by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    // Simple inline validation
    val nameError     = name.isNotBlank()     && name.length < 2
    val emailError    = email.isNotBlank()    && !email.contains("@")
    val passwordError = password.isNotBlank() && password.length < 8

    val formValid = name.isNotBlank() && email.contains("@") &&
            password.length >= 8 && !nameError && !emailError && !passwordError

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 28.dp, vertical = 52.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        // ── Icon ──────────────────────────────────────────────────────────
        AppIconWithBadge()

        Spacer(Modifier.height(24.dp))

        // ── Heading ───────────────────────────────────────────────────────
        Text(
            text          = "Join BookNest",
            fontSize      = 28.sp,
            fontWeight    = FontWeight.Bold,
            color         = TextPrimary,
            letterSpacing = (-0.3).sp,
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text      = "Create your account to get started",
            fontSize  = 15.sp,
            color     = TextSecondary,
            textAlign = TextAlign.Center,
        )

        Spacer(Modifier.height(36.dp))

        // ── Form fields ───────────────────────────────────────────────────
        BookNestTextField(
            value         = name,
            onValueChange = { name = it },
            label         = "Name",
            placeholder   = "Enter your name",
            leadingIcon   = Icons.Outlined.Person,
            isError       = nameError,
            errorMessage  = "Name must be at least 2 characters",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction    = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
        )

        Spacer(Modifier.height(16.dp))

        BookNestTextField(
            value         = email,
            onValueChange = { email = it },
            label         = "Email",
            placeholder   = "Enter your email",
            leadingIcon   = Icons.Outlined.Email,
            isError       = emailError,
            errorMessage  = "Enter a valid email address",
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction    = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(
                onNext = { focusManager.moveFocus(FocusDirection.Down) }
            ),
        )

        Spacer(Modifier.height(16.dp))

        BookNestTextField(
            value         = password,
            onValueChange = { password = it },
            label         = "Password",
            placeholder   = "Create a password",
            leadingIcon   = Icons.Outlined.Lock,
            isError       = passwordError,
            errorMessage  = "Password must be at least 8 characters",
            visualTransformation = if (passwordVisible)
                VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon  = if (passwordVisible)
                Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
            onTrailingIconClick = { passwordVisible = !passwordVisible },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction    = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    if (formValid) onCreateAccount(name, email, password)
                }
            ),
        )

        Spacer(Modifier.height(36.dp))

        // ── CTA ───────────────────────────────────────────────────────────
        PrimaryButton(
            text    = "Create Account",
            onClick = { if (formValid) onCreateAccount(name, email, password) },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(20.dp))

        // ── Sign-in link ──────────────────────────────────────────────────
        SignInAnnotatedText(onSignIn = onSignIn)
    }
}

// ── Reusable text field ───────────────────────────────────────────────────────

@Composable
private fun BookNestTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    leadingIcon: ImageVector,
    isError: Boolean                         = false,
    errorMessage: String                     = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: ImageVector?               = null,
    onTrailingIconClick: (() -> Unit)?       = null,
    keyboardOptions: KeyboardOptions         = KeyboardOptions.Default,
    keyboardActions: KeyboardActions         = KeyboardActions.Default,
) {
    Column {
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
                focusedBorderColor   = BrownPrimary,
                unfocusedBorderColor = ButtonBorder,
                errorBorderColor     = MaterialTheme.colorScheme.error,
                focusedContainerColor   = CardWhite,
                unfocusedContainerColor = Color(0xFFF5EEE6),
                cursorColor          = BrownPrimary,
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

// ── "Already have an account? Sign in" link ───────────────────────────────────

@Composable
private fun SignInAnnotatedText(onSignIn: () -> Unit) {
    val annotated = buildAnnotatedString {
        withStyle(SpanStyle(color = TextSecondary, fontSize = 14.sp)) {
            append("Already have an account? ")
        }
        withStyle(
            SpanStyle(
                color          = BrownPrimary,
                fontSize       = 14.sp,
                fontWeight     = FontWeight.SemiBold,
                textDecoration = TextDecoration.Underline,
            )
        ) {
            append("Sign in")
        }
    }

    TextButton(onClick = onSignIn) {
        Text(text = annotated, textAlign = TextAlign.Center)
    }
}

// ── Preview ───────────────────────────────────────────────────────────────────

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun SignUpScreenPreview() {
    BookNestTheme {
        SignUpScreen()
    }
}