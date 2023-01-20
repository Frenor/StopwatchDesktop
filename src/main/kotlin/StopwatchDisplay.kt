import androidx.compose.foundation.layout.*
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
) {
    Row(modifier = Modifier.padding(top = 40.dp)) {
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            AutoSizeText(
                formattedTime,
            )
        }
        Spacer(modifier = Modifier.size(10.dp))
        Row(modifier = Modifier.weight(1f)) {
            Column {
                Text("Klokkeslett")
                for (timestamp in timestamps) {
                    Text(timestamp)
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            Column {
                Text("Tid fra forrige")
                for (split in splits) {
                    Text(split)
                }

            }
        }
    }
}
