import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StopwatchDisplay(
    formattedTime: String,
    timestamps: List<String>,
    splits: List<String>,
    startStopStopwatch: () -> Unit,
    roundStopwatch: () -> Unit,
    resetStopwatch: () -> Unit,
    isStopwatchActive: Boolean,
) {
    Row {
        Column { Text(formattedTime, fontSize = 40.sp) }
        Spacer(modifier = Modifier.size(10.dp))
        Row {
            Column {
                Text("Klokkeslett")
                for (timestamp in timestamps) {
                    Text(timestamp)
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column {
                Text("Tid fra forrige")
                for (split in splits) {
                    Text(split)
                }

            }
        }
    }
    Row {
        Button(onClick = startStopStopwatch) { Text(if (isStopwatchActive) "Stop" else "Start") }
        Spacer(modifier = Modifier.size(10.dp))
        Button(onClick = roundStopwatch) { Text("Runde") }
        Spacer(modifier = Modifier.size(10.dp))
        Button(onClick = resetStopwatch) { Text("Tilbakestill") }
    }

}
