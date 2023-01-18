import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class Time {
    var currentTime by mutableStateOf("00:00:00")
    var currentDate by mutableStateOf("00.00.0000")
    private var coroutineScopeTime = CoroutineScope(Dispatchers.Main).launch {
        while (true) {
            currentTime = formatTime(LocalDateTime.now())
            currentDate = formatDate(LocalDateTime.now())
            delay(200L)
        }
    }
}
