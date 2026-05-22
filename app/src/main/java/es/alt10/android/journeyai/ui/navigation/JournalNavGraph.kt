package es.alt10.android.journeyai.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import es.alt10.android.journeyai.data.local.database.JournalDatabase
import es.alt10.android.journeyai.ui.dashboard.DashboardScreen
import es.alt10.android.journeyai.ui.dashboard.DashboardViewModel
import es.alt10.android.journeyai.ui.entry.EntryScreen
import es.alt10.android.journeyai.ui.entry.EntryViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun JournalNavGraph(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val database = remember { JournalDatabase.getDatabase(context) }
    val journalDao = remember { database.journalDao() }
    
    val backStack = rememberNavBackStack(Destination.Dashboard as NavKey)
    val strategy = rememberListDetailSceneStrategy<NavKey>()

    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = { 
            // Navigation 3 default onBack handles simple MutableList pop.
            // But we need to handle the 'count' from the strategy in some versions.
            // Based on the source code read, NavDisplay calls onBack() which we can provide.
            if (backStack.size > 1) {
                backStack.removeAt(backStack.size - 1)
            }
        },
        sceneStrategy = strategy,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = { key ->
            val destination = key as Destination
            when (destination) {
                Destination.Dashboard -> NavEntry(
                    key = key,
                    metadata = ListDetailSceneStrategy.listPane(
                        detailPlaceholder = {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Select an entry to read",
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    ),
                    content = { 
                        val dashboardViewModel: DashboardViewModel = viewModel {
                            DashboardViewModel(journalDao)
                        }
                        DashboardScreen(
                            viewModel = dashboardViewModel,
                            onNavigateToEntry = { entryId ->
                                backStack.add(Destination.Entry(entryId))
                            }
                        )
                    }
                )
                is Destination.Entry -> NavEntry(
                    key = key,
                    metadata = ListDetailSceneStrategy.detailPane(),
                    content = { k ->
                        val entryDest = k as Destination.Entry
                        val entryViewModel: EntryViewModel = viewModel {
                            EntryViewModel(journalDao, entryDest.entryId)
                        }
                        EntryScreen(
                            viewModel = entryViewModel,
                            onNavigateBack = {
                                if (backStack.isNotEmpty() && backStack.last() == key) {
                                    backStack.removeAt(backStack.size - 1)
                                }
                            }
                        )
                    }
                )
            }
        }
    )
}
