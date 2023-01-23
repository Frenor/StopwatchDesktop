import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(
            verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()
        ) {
            var time = remember { Time() }
            var cpr = remember { CPR() }
            var stopWatch = remember { StopWatch() }
            var isStopwatch by remember { mutableStateOf(false) }
            var isCPR by remember { mutableStateOf(false) }
            TimeDisplay(
                formattedTime = time.currentTime,
                formattedDate = time.currentDate,
                modifier = Modifier.weight(1F).fillMaxWidth()
            )
            if (isStopwatch) {
                StopwatchDisplay(
                    formattedTime = stopWatch.duration,
                    timestamps = stopWatch.roundTimeString,
                    splits = stopWatch.roundSplitString,
                    sinceLast = stopWatch.timeSinceLastString
                )
                Row(
                    modifier = Modifier.fillMaxWidth().padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = stopWatch::startStopInterval
                    ) { Text(if (stopWatch.isStopwatchInInterval) "Stopp intervall" else "Start intervall") }
                    Spacer(Modifier.size(20.dp))
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            if (stopWatch.roundSplitString.isEmpty()) {
                                stopWatch.resetStopwatch()
                                isStopwatch = false
                            } else {
                                stopWatch.resetStopwatch()
                            }
                        }
                    ) {
                        Text(if (stopWatch.roundSplitString.isEmpty()) "Avslutt" else "Tilbakestill")
                    }
                }
            } else if (isCPR) {
                BeatDisplay(
                    beat = cpr.beat, totalBeat = cpr.totalBeat
                )
                Row(
                    modifier = Modifier.fillMaxWidth().padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = cpr::startCPR
                    ) { Text("Start HLR") }
                    Spacer(Modifier.size(20.dp))
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { cpr.stopCPR(); isCPR = false }) {
                        Text("Stopp HLR")
                    }
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { isStopwatch = false; isCPR = true }) {
                        Text("HLR")
                    }
                    Spacer(Modifier.size(20.dp))
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = { isStopwatch = true; isCPR = false }) {
                        Text("Stoppeklokke")
                    }
                }
            }
        }
    }
}

fun main() = application {
    Window(title = "Stoppeklokka", onCloseRequest = ::exitApplication) {
        this.window.isAlwaysOnTop = true
        App()
    }
}
