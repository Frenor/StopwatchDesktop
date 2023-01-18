import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
                    startStopStopwatch = stopWatch::startStopStopwatch,
                    resetStopwatch = stopWatch::resetStopwatch,
                    roundStopwatch = stopWatch::roundStopwatch,
                    isStopwatchActive = stopWatch.isStopwatchActive
                )
            } else if (isCPR) {
                BeatDisplay(
                    beat = cpr.beat, totalBeat = cpr.totalBeat, start = cpr::startCPR, stop = cpr::stopCPR
                )
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Button(modifier = Modifier.fillMaxWidth(0.5f), onClick = { isStopwatch = false; isCPR = true }) {
                        Text(
                            "HLR"
                        )
                    }
                    Button(modifier = Modifier.fillMaxWidth(),
                        onClick = { isStopwatch = true; isCPR = false }) { Text("Stoppeklokke") }
                }
            }
        }
    }
}

fun main() = application {
    Window(title = "Stoppeklokka", onCloseRequest = ::exitApplication) {
        App()
    }
}
