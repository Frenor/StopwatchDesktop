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
    private var roundMillis = mutableStateListOf<Long>()
    private var coroutineScopeStopwatch = CoroutineScope(Dispatchers.Main)
    private var stopwatchMillis = 0L
    private var stopwatchLatestTimeMillis = 0L
    var isStopwatchActive by mutableStateOf(false)

    fun startStopStopwatch() {
        if (isStopwatchActive) {
            stopStopwatch()
        } else {
            startStopwatch()
        }
    }

    private fun startStopwatch() {
        stopwatchLatestTimeMillis = System.currentTimeMillis()
        isStopwatchActive = true
        coroutineScopeStopwatch.launch {
            while (isStopwatchActive) {
                delay(50L)
                stopwatchMillis += System.currentTimeMillis() - stopwatchLatestTimeMillis
                stopwatchLatestTimeMillis = System.currentTimeMillis()
                duration = formatDuration(stopwatchMillis.toDuration(DurationUnit.MILLISECONDS))
            }
        }

    }

    private fun stopStopwatch() {
        isStopwatchActive = false
        coroutineScopeStopwatch.cancel()
        coroutineScopeStopwatch = CoroutineScope(Dispatchers.Main)
    }

    fun resetStopwatch() {
        isStopwatchActive = false
        coroutineScopeStopwatch.cancel()
        coroutineScopeStopwatch = CoroutineScope(Dispatchers.Main)
        duration = "00:00.0"
        roundTimeString.clear()
        roundSplitString.clear()
        stopwatchMillis = 0L
    }

    fun roundStopwatch() {
        if (!isStopwatchActive) return

        roundTimeString.add(formatTime(LocalDateTime.now()))
        val durationMillis = if (roundMillis.isEmpty()) {
            stopwatchMillis
        } else {
            System.currentTimeMillis() - roundMillis.last()
        }
        roundSplitString.add(
            formatDuration(
                durationMillis.toDuration(DurationUnit.MILLISECONDS)
            )
        )
        roundMillis.add(System.currentTimeMillis())
    }

}
