package com.mim.babelio.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mim.babelio.ui.theme.BrownPrimary
import com.mim.babelio.ui.theme.ButtonBorder
import com.mim.babelio.ui.theme.CardWhite
import com.mim.babelio.ui.theme.TextSecondary

@Composable
fun DiscoverSearchBar(query: String, onQueryChange: (String) -> Unit, onSearch: () -> Unit, modifier: Modifier = Modifier, ) {
    OutlinedTextField(
        value         = query,
        onValueChange = onQueryChange,
        modifier      = modifier
            .fillMaxWidth()
            .height(52.dp),
        placeholder   = {
            Text(
                text     = "Search books or authors…",
                fontSize = 14.sp,
                color    = TextSecondary.copy(alpha = 0.7f),
            )
        },
        leadingIcon   = {
            Icon(
                imageVector        = Icons.Outlined.Search,
                contentDescription = "Search",
                tint               = TextSecondary,
                modifier           = Modifier.size(20.dp),
            )
        },
        singleLine    = true,
        shape         = RoundedCornerShape(14.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction    = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
        colors        = OutlinedTextFieldDefaults.colors(
            focusedBorderColor      = BrownPrimary,
            unfocusedBorderColor    = ButtonBorder,
            focusedContainerColor   = CardWhite,
            unfocusedContainerColor = Color(0xFFF5EEE6),
            cursorColor             = BrownPrimary,
        ),
    )
}
