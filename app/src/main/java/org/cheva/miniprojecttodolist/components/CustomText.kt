package org.cheva.miniprojecttodolist.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomText (
    modifier: Modifier = Modifier,
    text: String,
    textAlign: TextAlign = TextAlign.Start,
    textSize: TextUnit = 16.sp,
    textWeight: FontWeight = FontWeight.Normal,
    isDark: Boolean = isSystemInDarkTheme(),
    color: Color = if (isDark) Color.White else Color.Black
    )
{
    Text (
        modifier = modifier.fillMaxWidth(),
        color = color,
        text = text,
        style = TextStyle(
            textAlign = textAlign,
            fontSize = textSize,
            fontWeight = textWeight
        )
    )
}

@Preview
@Composable
private fun CustomTextPreview() {
    CustomText(text = "Rawr", textAlign = TextAlign.End)
}