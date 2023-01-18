import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.time.Duration

fun formatDuration(duration: Duration): String {
    return duration.toComponents { _: Long, minutes: Int, seconds: Int, nanoseconds: Int ->
        String.format(
            "%02d:%02d.%1d",
            minutes,
            seconds,
            nanoseconds / 100000000
        )
    }
}

fun formatDate(time: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("DD.MM.uuuu")
    return time.format(formatter)
}

fun formatTime(time: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    return time.format(formatter)
}
