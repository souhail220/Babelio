package com.mim.babelio.ui.screens.authentication

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
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
import com.mim.babelio.ui.components.BookNestTextField
import com.mim.babelio.ui.components.PrimaryButton
import com.mim.babelio.ui.theme.*

/**
 * Login screen — email + password.
 * Reuses [BookNestTextField], [AppIconWithBadge], and [PrimaryButton].
 *
 * @param onSignIn Called with (email, password) when the form is submitted.
 * @param onSignUp Navigate to the sign-up screen.
 */
@Composable
fun LoginScreen(
    onSignIn: (email: String, password: String) -> Unit = { _, _ -> },
    onSignUp: () -> Unit = {},
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
            LoginContent(onSignIn = onSignIn, onSignUp = onSignUp)
        }
    }
}

@Composable
private fun LoginContent(
    onSignIn: (String, String) -> Unit,
    onSignUp: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var email           by remember { mutableStateOf("") }
    var password        by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val emailError = email.isNotBlank() && !email.contains("@")
    val formValid  = email.contains("@") && password.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 28.dp, vertical = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AppIconWithBadge()

        Spacer(Modifier.height(28.dp))

        Text(
            text= "Welcome Back",
            fontSize= 28.sp,
            fontWeight= FontWeight.Bold,
            color= TextPrimary,
            letterSpacing= (-0.3).sp,
        )

        Spacer(Modifier.height(8.dp))

        Text(text= "Sign in to continue your reading journey",
            fontSize= 15.sp, color= TextSecondary, textAlign= TextAlign.Center,)

        Spacer(Modifier.height(40.dp))

        BookNestTextField(
            value           = email,
            onValueChange   = { email = it },
            label           = "Email",
            placeholder     = "Enter your email",
            leadingIcon     = Icons.Outlined.Email,
            isError         = emailError,
            errorMessage    = "Enter a valid email address",
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
            value                = password,
            onValueChange        = { password = it },
            label                = "Password",
            placeholder          = "Enter your password",
            leadingIcon          = Icons.Outlined.Lock,
            visualTransformation = if (passwordVisible)
                VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon         = if (passwordVisible)
                Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
            onTrailingIconClick  = { passwordVisible = !passwordVisible },
            keyboardOptions      = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction    = ImeAction.Done,
            ),
            keyboardActions      = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    if (formValid) onSignIn(email, password)
                }
            ),
        )

        Spacer(Modifier.height(36.dp))

        PrimaryButton(
            text     = "Sign In",
            onClick  = { if (formValid) onSignIn(email, password) },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(Modifier.height(20.dp))

        TextButton(onClick = onSignUp) {
            Text(
                text = buildAnnotatedString {
                    withStyle(SpanStyle(color = TextSecondary, fontSize = 14.sp)) {
                        append("Don't have an account? ")
                    }
                    withStyle(
                        SpanStyle(
                            color          = BrownPrimary,
                            fontSize       = 14.sp,
                            fontWeight     = FontWeight.SemiBold,
                            textDecoration = TextDecoration.Underline,
                        )
                    ) { append("Sign up") }
                },
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true, widthDp = 390, heightDp = 844)
@Composable
fun LoginScreenPreview() {
    BookNestTheme {
        LoginScreen()
    }
}