import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.*
import java.time.LocalDateTime
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class StopWatch {
    var duration by mutableStateOf("00:00.0")
    var roundTimeString = mutableStateListOf<String>()
    var roundSplitString = mutableStateListOf<String>()
    var timeSinceLastString = mutableStateListOf<String>()
    private var intervalStartMillis = mutableStateListOf<Long>()
    private var intervalMillis = mutableStateListOf<Long>()
    private var coroutineScopeStopwatch = CoroutineScope(Dispatchers.Main)
    private var stopwatchMillis = 0L
    private var intervalStartTimeMillis = 0L
    var isStopwatchInInterval by mutableStateOf(false)

    fun startStopInterval() {
        if (isStopwatchInInterval) {
            stopInterval()
        } else {
            startInterval()
        }
    }


    private fun startInterval() {

        val sinceLastMillis = if (intervalStartMillis.isEmpty()) {
            0
        } else {
            System.currentTimeMillis() - intervalStartMillis.last()
        }
        timeSinceLastString.add(formatDuration(sinceLastMillis.toDuration(DurationUnit.MILLISECONDS)))

        intervalStartTimeMillis = System.currentTimeMillis()
        intervalStartMillis.add(intervalStartTimeMillis)
        isStopwatchInInterval = true
        coroutineScopeStopwatch.launch {
            while (isStopwatchInInterval) {
                delay(50L)
                stopwatchMillis = System.currentTimeMillis() - intervalStartTimeMillis
                duration = formatDuration(stopwatchMillis.toDuration(DurationUnit.MILLISECONDS))
            }
        }

    }

    private fun stopInterval() {
        if (!isStopwatchInInterval) return

        roundTimeString.add(formatTime(LocalDateTime.now()))
        roundSplitString.add(
            formatDuration(
                stopwatchMillis.toDuration(DurationUnit.MILLISECONDS)
            )
        )
        intervalMillis.add(System.currentTimeMillis())

        duration = "00:00.0"
        stopwatchMillis = 0L

        isStopwatchInInterval = false
        coroutineScopeStopwatch.cancel()
        coroutineScopeStopwatch = CoroutineScope(Dispatchers.Main)
    }

    fun resetStopwatch() {
        coroutineScopeStopwatch.cancel()
        coroutineScopeStopwatch = CoroutineScope(Dispatchers.Main)
        duration = "00:00.0"
        roundTimeString.clear()
        roundSplitString.clear()
        timeSinceLastString.clear()
        stopwatchMillis = 0L
    }


}
