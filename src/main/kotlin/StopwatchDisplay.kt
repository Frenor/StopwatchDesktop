import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StopwatchDisplay(
    formattedTime: String,
    timestamps: List<String>,
    splits: List<String>,
    sinceLast: List<String>,
) {
    Row(modifier = Modifier.padding(top = 40.dp), horizontalArrangement = Arrangement.spacedBy(15.dp)) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            AutoSizeText(
                formattedTime,
            )
        }
        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(15.dp)) {
            Column {
                Text("Starttid")
                for (timestamp in timestamps) {
                    Text(timestamp)
                }
            }
            Column {
                Text("Varighet")
                for (split in splits) {
                    Text(split)
                }
            }
            Column {
                Text("Siden forrige")
                for (time in sinceLast) {
                    Text(time)
                }
            }
        }
    }
}
