package org.cheva.miniprojecttodolist.components

import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextBox(
    modifier: Modifier = Modifier,
    name: String,
    placeholder: String,
    value: String,
    icon: @Composable (() -> Unit)? = null,
    icon2: @Composable (() -> Unit)? = null,
    onValueChange: (value: String) -> Unit,
//    visual: VisualTransformation = VisualTransformation.None
    pass: Boolean = false,
    theme: Boolean = isSystemInDarkTheme()
) {
    
    Column(
        modifier = modifier.padding(8.dp)
    ) {
        Text(
            text = name,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier =
                Modifier.fillMaxWidth().
                border(1.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(8.dp)),
            shape = RoundedCornerShape(8.dp),
            leadingIcon = icon,
            trailingIcon = icon2,
            placeholder = { Text(text = placeholder) },
            value = value,
            onValueChange = onValueChange,
            visualTransformation = if (pass) PasswordVisualTransformation() else VisualTransformation.None,
        )
    }
}

@Composable
fun VectorIcon(
    icon: ImageVector,
    description: String
){
    Icon(
        imageVector =  icon,
        contentDescription = description,
        modifier = Modifier.size(32.dp),
        tint = Color.Black
    )
}

@Preview
@Composable
private fun TextBoxPreview() {
    Surface {
        TextBox(
            name = "rawr",
            placeholder = "rawr",
            value = "",
            icon = { VectorIcon(Icons.Filled.Person, "Person") },
            onValueChange = {}
        )
    }
}