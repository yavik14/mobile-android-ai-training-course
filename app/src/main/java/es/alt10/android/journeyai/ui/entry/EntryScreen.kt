package es.alt10.android.journeyai.ui.entry

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CalendarToday
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryScreen(
    viewModel: EntryViewModel,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    var showDatePicker by remember { mutableStateOf(false) }
    var showMoodMenu by remember { mutableStateOf(false) }
    val moods = listOf("Tranquil", "Energetic", "Happy", "Neutral", "Reflective", "Melancholy")

    val dateFormatter = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(if (viewModel.title.isEmpty()) "New Entry" else viewModel.title) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    // Only show delete if editing an existing entry
                    if (viewModel.entryId != null) {
                        IconButton(onClick = { viewModel.deleteEntry(onNavigateBack) }) {
                            Icon(Icons.Rounded.Delete, contentDescription = "Delete")
                        }
                    }
                    IconButton(onClick = { viewModel.saveEntry(onNavigateBack) }) {
                        Icon(Icons.Rounded.Done, contentDescription = "Save")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .consumeWindowInsets(innerPadding)
                .imePadding()
                .verticalScroll(scrollState)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = viewModel.title,
                onValueChange = { viewModel.title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large,
                singleLine = true
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Date Picker Interaction
                OutlinedTextField(
                    value = dateFormatter.format(Date(viewModel.date)),
                    onValueChange = { },
                    label = { Text("Date") },
                    readOnly = true,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { showDatePicker = true },
                    trailingIcon = {
                        IconButton(onClick = { showDatePicker = true }) {
                            Icon(Icons.Rounded.CalendarToday, contentDescription = "Select Date")
                        }
                    },
                    shape = MaterialTheme.shapes.large,
                    enabled = false,
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledTextColor = MaterialTheme.colorScheme.onSurface,
                        disabledBorderColor = MaterialTheme.colorScheme.outline,
                        disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        disabledTrailingIconColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )

                // Mood Selection
                Box(modifier = Modifier.weight(1f)) {
                    OutlinedTextField(
                        value = viewModel.mood,
                        onValueChange = { },
                        label = { Text("Mood") },
                        readOnly = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { showMoodMenu = true },
                        shape = MaterialTheme.shapes.large,
                        enabled = false,
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledTextColor = MaterialTheme.colorScheme.onSurface,
                            disabledBorderColor = MaterialTheme.colorScheme.outline,
                            disabledLabelColor = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    )
                    DropdownMenu(
                        expanded = showMoodMenu,
                        onDismissRequest = { showMoodMenu = false }
                    ) {
                        moods.forEach { moodOption ->
                            DropdownMenuItem(
                                text = { Text(moodOption) },
                                onClick = {
                                    viewModel.mood = moodOption
                                    showMoodMenu = false
                                }
                            )
                        }
                    }
                    // Overlay to capture clicks since TextField is disabled
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .clickable { showMoodMenu = true }
                    )
                }
            }

            OutlinedTextField(
                value = viewModel.content,
                onValueChange = { viewModel.content = it },
                label = { Text("What's on your mind?") },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 300.dp),
                shape = MaterialTheme.shapes.large
            )
        }

        if (showDatePicker) {
            val datePickerState = rememberDatePickerState(
                initialSelectedDateMillis = viewModel.date
            )
            DatePickerDialog(
                onDismissRequest = { showDatePicker = false },
                confirmButton = {
                    TextButton(onClick = {
                        viewModel.date = datePickerState.selectedDateMillis ?: viewModel.date
                        showDatePicker = false
                    }) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDatePicker = false }) {
                        Text("Cancel")
                    }
                }
            ) {
                DatePicker(state = datePickerState)
            }
        }
    }
}
