import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFontFamilyResolver
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isFinite
import androidx.compose.ui.unit.sp
import kotlin.math.absoluteValue
import kotlin.math.ceil

@Composable
fun TimeDisplay(
    time: Time, modifier: Modifier, onCopyClick: (String) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AutoSizeText(
                text = time.currentTime,
                fontWeight = FontWeight.Bold,
            )
            IconButton(onClick = { onCopyClick("[" + time.currentTime + "] ") }) {
                Icon(
                    Icons.Default.Send,
                    contentDescription = null
                )
            }
        }
        AutoSizeText(
            text = time.currentDate,
            fontWeight = FontWeight.Light,
        )
    }
}

@Composable
fun AutoSizeText(
    text: String,
    fontWeight: FontWeight? = null,
) {
    BoxWithConstraints(contentAlignment = Alignment.TopCenter) {
        var shrunkFontSize = 500.sp

        val calculateIntrinsics = @Composable {
            val mergedStyle = TextStyle(
                fontSize = shrunkFontSize,
                fontWeight = fontWeight,
            )
            Paragraph(
                text = text,
                style = mergedStyle,
                constraints = Constraints(maxWidth = ceil(LocalDensity.current.run { maxWidth.toPx() }).toInt()),
                density = LocalDensity.current,
                fontFamilyResolver = LocalFontFamilyResolver.current,
                spanStyles = listOf(),
                placeholders = listOf(),
                maxLines = 1,
                ellipsis = false
            )
        }

        var intrinsics = calculateIntrinsics()

        val targetWidth = maxWidth * 0.90f

        check(targetWidth.isFinite) { "maxFontSize must be specified if the target with isn't finite!" }

        with(LocalDensity.current) {
            if (targetWidth < intrinsics.minIntrinsicWidth.toDp()) while ((targetWidth - intrinsics.minIntrinsicWidth.toDp()).toPx().absoluteValue.toDp() > 60.dp / 2f) {
                shrunkFontSize *= targetWidth.toPx() / intrinsics.minIntrinsicWidth
                intrinsics = calculateIntrinsics()
            }
            // checks if the text fits in the bounds and scales it by 90% until it does
            while (intrinsics.didExceedMaxLines || maxHeight < intrinsics.height.toDp() || maxWidth < intrinsics.minIntrinsicWidth.toDp()) {
                shrunkFontSize *= 0.95f
                intrinsics = calculateIntrinsics()
            }
        }

        Text(
            text = text,
            fontSize = shrunkFontSize,
            fontWeight = fontWeight,
        )
    }
}
