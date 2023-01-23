import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.*

class CPR {
    var beat by mutableStateOf(-1)
    var totalBeat by mutableStateOf(0)
    private val mspb = 620L
    private var coroutineScopeBeat = CoroutineScope(Dispatchers.Main)
    private var cprStartMillis = 0L
    private var isCprActive = false

    fun startCPR() {
        if (isCprActive) return

        cprStartMillis = System.currentTimeMillis()
        isCprActive = true
        coroutineScopeBeat.launch {
            while (isCprActive) {
                delay(50L)
                val duration = System.currentTimeMillis() - cprStartMillis
                beat = (duration / mspb).toInt() % 4
                totalBeat = (duration / mspb).toInt() + 1
                if (totalBeat >= 30) {
                    isCprActive = false
                    this.coroutineContext.cancel()
                    coroutineScopeBeat = CoroutineScope(Dispatchers.Main)
                }
            }
        }
    }

    fun stopCPR() {
        if (!isCprActive) return

        cprStartMillis = 0L
        beat = -1
        totalBeat = 0
        isCprActive = false
        coroutineScopeBeat.cancel()
        coroutineScopeBeat = CoroutineScope(Dispatchers.Main)
    }
}
