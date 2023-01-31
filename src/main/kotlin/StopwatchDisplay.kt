import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun StopwatchDisplay(
    stopWatch: StopWatch,
) {
    Row(modifier = Modifier.padding(top = 40.dp), horizontalArrangement = Arrangement.spacedBy(15.dp)) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            AutoSizeText(
                stopWatch.duration,
            )
        }
        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            Column {
                Text("Starttid", fontWeight = FontWeight.Bold)
                for (timestamp in stopWatch.intervalStartString) {
                    Text(timestamp)
                }
            }
            Column {
                Text("Varighet", fontWeight = FontWeight.Bold)
                for (split in stopWatch.intervalDurationString) {
                    Text(split)
                }
            }
            Column {
                Text("Siden forrige", fontWeight = FontWeight.Bold)
                for (time in stopWatch.timeSinceLastString) {
                    Text(time)
                }
            }
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth().padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            modifier = Modifier.weight(1f), onClick = stopWatch::startStopInterval
        ) { Text(if (stopWatch.isStopwatchInInterval) "Stopp intervall" else "Start intervall") }
        Spacer(Modifier.size(20.dp))
        Button(modifier = Modifier.weight(1f), onClick = stopWatch::resetStopwatch) {
            Text("Tilbakestill")
        }
    }

}
