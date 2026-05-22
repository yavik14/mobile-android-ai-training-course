# Project Plan

A modern Android Journaling app called JourneyAI. 
Features:
- Home screen with a list of past entries sorted by date.
- Floating action button to create new entries.
- New entry screen with fields for title, date, content, and a mood drop-down.
- Use Material 3 design, nature-themed headers, full edge-to-edge display, and adaptive layouts.

## Project Brief

# Project Brief: JourneyAI

JourneyAI is a modern, AI-inspired journaling application designed to provide a tranquil yet energetic space for self-reflection. The app emphasizes a "journey" theme through Material 3 aesthetics, nature-inspired visuals, and a seamless, adaptive user interface.

## Features

*   **Journal Dashboard:** A clean, chronologically sorted list of past journal entries featuring tranquil nature-themed headers and Material 3 cards.
*   **Streamlined Entry Creation:** A prominent Floating Action Button (FAB) that allows users to quickly capture thoughts and moments.
*   **Rich Journal Editor:** A dedicated entry screen with fields for title, date, and content, complemented by a mood-selection dropdown to track emotional trends.
*   **Adaptive & Edge-to-Edge Experience:** A fully responsive layout that adapts to different screen sizes (phones, tablets, foldables) using a full edge-to-edge display for maximum immersion.

## High-Level Technical Stack

*   **Language:** Kotlin
*   **UI Framework:** Jetpack Compose with Material 3
*   **Navigation:** Jetpack Navigation 3 (State-driven architecture)
*   **Adaptive Strategy:** Compose Material Adaptive library for responsive layouts
*   **Concurrency:** Kotlin Coroutines for fluid UI transitions and background processing
*   **Image Loading:** Coil (for nature-themed headers)

## Implementation Steps
**Total Duration:** 23m

### Task_1_Core_Setup_Theming: Initialize the project infrastructure including the Material 3 nature-themed color scheme, typography, and edge-to-edge configuration. Set up the Room database and data models for journal entries (id, title, content, date, mood).
- **Status:** COMPLETED
- **Updates:** Material 3 nature-themed colors and typography implemented. Edge-to-edge enabled in MainActivity. Room database and JournalEntry entity defined. Project builds successfully with compileSdk 37. Adaptive app icon added.
- **Acceptance Criteria:**
  - Material 3 theme with nature-inspired colors is implemented
  - Edge-to-edge display is enabled in MainActivity
  - Room database and JournalEntry entity are defined
  - Project builds successfully
- **Duration:** 6m 2s

### Task_2_UI_Screens: Implement the Journal Dashboard (home) and Journal Entry screens. The dashboard should feature a list of entries with Coil-loaded nature headers. The entry screen should include fields for title, content, date, and a mood dropdown selection.
- **Status:** COMPLETED
- **Updates:** Implemented Journal Dashboard with Coil-loaded nature headers and FAB. Implemented Journal Entry screen with title, content, date picker, and mood dropdown. Integrated Jetpack Navigation 3 for screen transitions. Screens are connected to ViewModels and the Room database.
- **Acceptance Criteria:**
  - Dashboard screen displays a list of entries with images
  - Floating Action Button is present on the dashboard
  - Journal Entry screen has all required input fields and mood dropdown
  - UI follows Material 3 guidelines and nature aesthetic
- **Duration:** 5m 42s

### Task_3_Logic_Navigation: Integrate Navigation 3 for screen transitions and connect the UI screens to the Room database via ViewModels. Implement CRUD operations to save and retrieve journal entries sorted by date.
- **Status:** COMPLETED
- **Updates:** Navigation 3 state persistence implemented. CRUD operations verified (Save, Edit, Delete). Entries sorted newest first. App state maintained across configuration changes. UI refined for better UX.
- **Acceptance Criteria:**
  - Navigation 3 correctly handles transitions between Dashboard and Entry screens
  - New journal entries are successfully saved to Room
  - Entries are retrieved and displayed in chronological order
  - App state is maintained during navigation
- **Duration:** 11m 16s

### Task_4_Adaptive_Branding_Verify: Implement adaptive layouts for different screen sizes using the Compose Adaptive library. Create an adaptive app icon and perform final branding refinements. Conduct a full run and verify session.
- **Status:** IN_PROGRESS
- **Acceptance Criteria:**
  - Layout adapts gracefully to tablets and foldables (e.g., List-Detail pane)
  - Adaptive app icon is correctly configured
  - App does not crash and meets all user requirements
  - Build passes and existing tests pass
- **StartTime:** 2026-05-22 10:05:10 CEST

