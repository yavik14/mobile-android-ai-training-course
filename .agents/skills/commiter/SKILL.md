---
name: committer
description: Use it when you're asked to create a commit
---

# Commit Structure

All commits should follow the Conventional Commits specification and include an emoji representing the type of change.

## Format
`<type>(<scope>): <emoji> <description>`

## Types and Emojis
- `feature`: ✨ Features
- `fix`: 🐛 Bug Fixes
- `docs`: 📝 Documentation
- `style`: 🎨 Styles and Formatting
- `refactor`: ♻️ Code Refactoring
- `performance`: ⚡ Performance Improvements
- `test`: ✅ Testing
- `build`: 🏗️ Build System or Dependencies
- `chore`: 🔧 Housekeeping tasks
- `revert`: ⏪ Reversions

## Additional rules
- Keep the title concise.
- Imperative description.
- Use ! when break compatibility.