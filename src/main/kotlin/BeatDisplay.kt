import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BeatDisplay(
    beat: Int,
    totalBeat: Int,
    start: () -> Unit,
    stop: () -> Unit,
) {
    Row(modifier = Modifier.padding(6.dp).fillMaxWidth()) {
        for (i in 0..3) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
                    .aspectRatio(1f)
                    .clip(shape = CircleShape)
                    .background(
                        color = if (beat >= i) {
                            Color.Red
                        } else {
                            Color.Gray
                        }
                    )
            ) {
                Text(
                    text = (i + 1).toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 50.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
                .aspectRatio(1f)
                .clip(shape = RoundedCornerShape(6.dp))
                .background(Color.LightGray)
        ) {
            Text(
                text = (totalBeat).toString(),
                fontSize = 50.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
    Row {
        Button(onClick = start) { Text("Start CPR") }
        Spacer(modifier = Modifier.size(10.dp))
        Button(onClick = stop) { Text("Stop CPR") }
    }
}