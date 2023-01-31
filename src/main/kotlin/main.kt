import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application


@Composable
@Preview
fun App(backgroundColor: Color) {
    var selectedState by remember { mutableStateOf("") }
    val clipboardManager = LocalClipboardManager.current

    MaterialTheme() {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize().background(backgroundColor)
        ) {
            var time = remember { Time() }
            var cpr = remember { CPR() }
            var stopWatch = remember { StopWatch() }
            TimeDisplay(
                time = time, modifier = Modifier.weight(1F).fillMaxWidth(),
                onCopyClick = { value -> clipboardManager.setText(AnnotatedString(value)) }
            )
            if (selectedState == "stopwatch") {
                StopwatchDisplay(
                    stopWatch = stopWatch,
                )
            } else if (selectedState == "cpr") {
                BeatDisplay(
                    cpr = cpr
                )
            }
            BottomAppBar {
                BottomNavigation {
                    BottomNavigationItem(
                        onClick = { selectedState = if (selectedState == "cpr") "" else "cpr" },
                        icon = { Icon(Icons.Default.Favorite, contentDescription = null) },
                        label = { Text("HLR") },
                        selected = selectedState == "cpr"
                    )
                    BottomNavigationItem(
                        onClick = {
                            selectedState = if (selectedState == "stopwatch") "" else "stopwatch"
                        },
                        icon = { Icon(Icons.Default.PlayArrow, contentDescription = null) },
                        label = { Text("Stoppeklokke") },
                        selected = selectedState == "stopwatch"
                    )
                }
            }

        }
    }
}

fun main() = application {
    var undecorated by remember { mutableStateOf(false) }

    key(undecorated) {
        Window(
            title = "Stoppeklokka", onCloseRequest = ::exitApplication, alwaysOnTop = true, undecorated = undecorated
        ) {
            val backgroundColors = mapOf("Blå" to Color.Blue, "Grå" to Color.Gray, "Hvit" to Color.White)

            var backgroundColor by remember { mutableStateOf(Color.White) }

            MenuBar {
                Menu(text = "Farge") {
                    backgroundColors.forEach { entry ->
                        Item(
                            text = entry.key,
                            onClick = { backgroundColor = entry.value },
                            icon = ColorPainter(entry.value),
                        )
                    }
                }
                Menu(text = "Test") {
                    Item(text = "Toggle frame", onClick = { undecorated = !undecorated })
                }
            }
            App(backgroundColor)
        }
    }
}

