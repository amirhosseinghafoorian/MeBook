package com.example.mebook.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.mebook.ui.components.text_field_util.TextFieldState

@Composable
fun MeBookTextField(
    state: TextFieldState,
    modifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier,
    onValueChange: (String) -> Unit,
    maxLines: Int = Int.MAX_VALUE,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    shape: Shape = MaterialTheme.shapes.medium,
    placeholder: String = "",
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    textStyle: TextStyle = MaterialTheme.typography.body1.copy(
        textAlign = TextAlign.Start,
    ),
    colors: TextFieldColors = TextFieldDefaults.textFieldColors(
        textColor = MaterialTheme.colors.onSurface,
        disabledTextColor = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
        disabledLeadingIconColor = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
        disabledTrailingIconColor = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
        disabledPlaceholderColor = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
        cursorColor = MaterialTheme.colors.onSurface,
        backgroundColor = MaterialTheme.colors.surface,
        focusedLabelColor = Color.Transparent,
        focusedIndicatorColor = Color.Transparent,
        errorIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )
) {
    Column(modifier = modifier) {
        TextField(
            value = state.text,
            onValueChange = onValueChange,
            singleLine = singleLine,
            enabled = enabled,
            readOnly = readOnly,
            isError = state.isError(),
            textStyle = textStyle,
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.5f)
                )
            },
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            modifier = Modifier
                .fillMaxWidth()
                .then(textFieldModifier)
                .border(
                    width = if (state.isError()) 1.dp else 0.dp,
                    color = if (state.isError()) MaterialTheme.colors.error else MaterialTheme.colors.background,
                    shape = MaterialTheme.shapes.medium
                ),
            maxLines = maxLines,
            visualTransformation = visualTransformation,
            keyboardActions = keyboardActions,
            keyboardOptions = keyboardOptions,
            shape = shape,
            colors = colors,
        )

        if (state.isError()) {
            Text(
                text = state.errorMessage ?: "",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp)
            )
        }

    }
}
