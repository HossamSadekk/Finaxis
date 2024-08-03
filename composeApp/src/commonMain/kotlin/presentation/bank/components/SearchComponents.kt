package presentation.bank.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import core.designSystem.helper.SearchState
import finaxis.composeapp.generated.resources.Res.font
import finaxis.composeapp.generated.resources.poppins_light
import finaxis.composeapp.generated.resources.poppins_regular
import org.jetbrains.compose.resources.Font

@Composable
fun SearchSection(
    modifier: Modifier = Modifier,
    state: SearchState,
    onSearch: (String) -> Unit,
) {
    Surface(
        color = MaterialTheme.colorScheme.secondaryContainer,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier.height(60.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 10.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "search icon",
                modifier = Modifier.size(
                    24.dp
                ),
                tint = MaterialTheme.colorScheme.onTertiary
            )

            TextField(
                value = state.query, onValueChange = { value ->
                    onSearch(value)
                },
                placeholder = {
                    Text(
                        text = "Search about your bank",
                        style = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(font.poppins_light))),
                        color = MaterialTheme.colorScheme.onTertiary
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = MaterialTheme.colorScheme.inversePrimary
                ),
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                textStyle = MaterialTheme.typography.bodyMedium.copy(fontFamily = FontFamily(Font(font.poppins_regular)))
            )
        }
    }
}