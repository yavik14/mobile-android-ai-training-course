package es.alt10.android.journeyai.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.alt10.android.journeyai.data.local.dao.JournalDao
import es.alt10.android.journeyai.data.model.JournalEntry
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class DashboardViewModel(
    private val journalDao: JournalDao
) : ViewModel() {

    val entries: StateFlow<List<JournalEntry>> = journalDao.getAllEntries()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
}
