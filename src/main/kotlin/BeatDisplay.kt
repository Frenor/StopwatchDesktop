import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BeatDisplay(
    cpr: CPR
) {
    Row(modifier = Modifier.padding(top = 30.dp, start = 6.dp, end = 6.dp).fillMaxWidth()) {
        for (i in 0..3) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .weight(1f)
                    .padding(10.dp)
                    .aspectRatio(1f)
                    .clip(shape = CircleShape)
                    .background(
                        color = if (cpr.beat >= i) {
                            Color.Red
                        } else {
                            Color.Gray
                        }
                    )
            ) {}
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(6.dp)
                .weight(1f)
                .aspectRatio(1f)
                .clip(shape = RoundedCornerShape(6.dp))
                .background(Color.LightGray),
        ) {
            AutoSizeText(text = cpr.totalBeat.toString())
        }
    }
    Row(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            modifier = Modifier.weight(1f), onClick = cpr::startCPR
        ) { Text("Start HLR") }
        Spacer(Modifier.size(20.dp))
        Button(modifier = Modifier.weight(1f), onClick = {
            cpr.stopCPR();
        }) {
            Text("Stopp HLR")
        }
    }

}
