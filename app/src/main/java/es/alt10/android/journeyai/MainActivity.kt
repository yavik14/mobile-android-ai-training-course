package es.alt10.android.journeyai

import android.os.Bundle
import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import es.alt10.android.journeyai.ui.navigation.JournalNavGraph
import es.alt10.android.journeyai.ui.theme.JourneyAITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }
        setContent {
            JourneyAITheme {
                JournalNavGraph(modifier = Modifier.fillMaxSize())
            }
        }
    }
}
