import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.sp

@Composable
fun TimeDisplay(
    formattedTime: String, formattedDate: String, modifier: Modifier
) {
    var fontSize by remember { mutableStateOf(0f) }
    Column(
        modifier = modifier.onGloballyPositioned { coordinates ->
            fontSize = coordinates.size.width.toFloat() / 5
        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formattedTime,
            maxLines = 1,
            softWrap = false,
            fontSize = fontSize.sp,
        )
        Text(
            text = formattedDate,
            maxLines = 1,
            softWrap = false,
            fontSize = (fontSize * 0.8).sp,
        )
    }
}
