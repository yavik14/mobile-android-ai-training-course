package es.alt10.android.journeyai.ui.entry

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.alt10.android.journeyai.data.local.dao.JournalDao
import es.alt10.android.journeyai.data.model.JournalEntry
import kotlinx.coroutines.launch

class EntryViewModel(
    private val journalDao: JournalDao,
    val entryId: Long?
) : ViewModel() {

    var title by mutableStateOf("")
    var content by mutableStateOf("")
    var date by mutableStateOf(System.currentTimeMillis())
    var mood by mutableStateOf("Neutral")
    var isSaving by mutableStateOf(false)

    init {
        if (entryId != null) {
            viewModelScope.launch {
                val entry = journalDao.getEntryById(entryId)
                if (entry != null) {
                    title = entry.title
                    content = entry.content
                    date = entry.date
                    mood = entry.mood
                }
            }
        }
    }

    fun saveEntry(onComplete: () -> Unit) {
        if (title.isBlank()) return
        isSaving = true
        viewModelScope.launch {
            val entry = JournalEntry(
                id = entryId ?: 0,
                title = title,
                content = content,
                date = date,
                mood = mood
            )
            if (entryId == null) {
                journalDao.insertEntry(entry)
            } else {
                journalDao.updateEntry(entry)
            }
            isSaving = false
            onComplete()
        }
    }

    fun deleteEntry(onComplete: () -> Unit) {
        if (entryId != null) {
            viewModelScope.launch {
                val entry = journalDao.getEntryById(entryId)
                if (entry != null) {
                    journalDao.deleteEntry(entry)
                }
                onComplete()
            }
        }
    }
}
