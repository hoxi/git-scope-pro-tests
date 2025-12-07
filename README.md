# Git Scope Pro - Test Repository

This repository is designed to test all features of the [Git Scope Pro IntelliJ Plugin](https://github.com/hoxi/git-scope-pro).

## Repository Structure

### Main Repository: `git-scope-pro-tests`
- **3 branches**: `main`, `feature/models-enhancement`, `feature/utils-expansion`
- **10 tags**: v0.1.0, v0.2.0-alpha, v0.3.0, v0.4.0-beta, v0.5.0, v1.0.0, cleanup, logging-update, refactor-services, submodule-init
- **13 Java files** across 3 packages (packageA, packageB, packageC)
- **12 commits** with various types of changes (additions, modifications, deletions)

### Submodule: `libs/common-lib` → `git-scope-pro-tests-subrepo`
- **2 branches**: `master`, `feature/enhanced-parsing`
- **3 tags**: lib-v1.0.0, lib-v1.1.0, lib-v1.2.0
- **4 Java utility files**
- **3 commits on master**, **2 additional commits on feature branch**

## Setup Instructions

1. Clone the repository with submodules:
   ```bash
   git clone --recurse-submodules https://github.com/hoxi/git-scope-pro-tests.git
   ```

2. If already cloned without submodules, initialize them:
   ```bash
   git submodule update --init --recursive
   ```

3. Open the project in IntelliJ IDEA with the Git Scope Pro plugin installed

## Manual Test Cases

### Test 1: Basic Scope Creation and Diff Viewing

**Objective**: Verify basic scope creation with branches and tags

1. Open the Git Scope tool window (View → Tool Windows → Git Scope)
2. Create a new scope and select **v0.5.0** tag
3. **Expected**: Change Browser should show all files changed between v0.5.0 and current HEAD
4. Verify you see changes in:
   - `ConfigService.java` (refactored with singleton pattern)
   - `DataService.java` (added capacity and search methods)
   - `CacheService.java` (newly added)
   - `Main.java` (integrated all packages)
5. Right-click on `ConfigService.java` → **Show Diff**
6. **Expected**: Diff window opens showing the refactoring changes (singleton pattern added)

### Test 2: Tag-to-Tag Comparison

**Objective**: Test comparing between two specific tags

1. Create a scope selecting **v0.2.0-alpha** tag
2. **Expected**: Shows all changes from v0.2.0-alpha to HEAD
3. Switch scope to **v0.4.0-beta**
4. **Expected**: Change Browser updates to show changes from v0.4.0-beta to HEAD
5. Verify differences between the two views:
   - v0.2.0-alpha scope should show more files (packageB, packageC, submodule changes)
   - v0.4.0-beta scope should show fewer files (only changes after that tag)

### Test 3: Branch Comparison

**Objective**: Test comparing HEAD with feature branches

1. Switch to branch **feature/models-enhancement**:
   ```bash
   git checkout feature/models-enhancement
   ```
2. Create a scope selecting **v0.5.0** tag
3. **Expected**: Should show 3 commits worth of changes:
   - New `Customer.java` file
   - Enhanced `User.java` (with createdAt and phoneNumber)
   - Enhanced `Product.java` (with category and description)
4. Switch to branch **feature/utils-expansion**:
   ```bash
   git checkout feature/utils-expansion
   ```
5. Create a scope selecting **v0.4.0-beta** tag
6. **Expected**: Should show 3 commits worth of changes:
   - New `CollectionUtils.java`
   - Enhanced `MathUtils.java` (with lcm, power, isEven, isOdd)
   - Enhanced `StringUtils.java` (with truncate, countOccurrences, repeat)

### Test 4: Line Status Gutter

**Objective**: Verify gutter highlights modified lines relative to selected scope

1. Return to **main** branch
2. Enable gutter highlighting: Settings → Version Control → Enable "Highlight modified lines in gutter"
3. Create a scope selecting **v0.3.0** tag
4. Open `Main.java` in the editor
5. **Expected**: Lines added after v0.3.0 should be highlighted in the gutter:
   - Import statements for StringUtils, ConfigService, DataService, User, Product
   - All demo code (user creation, product creation, string utilities demo, data service demo)
6. Switch scope to **v0.5.0**
7. **Expected**: Fewer lines highlighted (only changes after v0.5.0)

### Test 5: Commit Hash as Scope

**Objective**: Test using specific commit hashes

1. Get a commit hash from the middle of history:
   ```bash
   git log --oneline
   ```
2. Copy the hash for "Add logging statements to Main.java using LoggingService" (e.g., `6ee9035`)
3. Create a scope with this commit hash
4. **Expected**: Shows all changes since that commit
5. Open `Main.java` in Change Browser
6. **Expected**: Should show logging additions and all subsequent changes

### Test 6: Git References (HEAD~N)

**Objective**: Test relative git references

1. Create a scope with **HEAD~5**
2. **Expected**: Shows changes from 5 commits ago to current HEAD
3. Create a scope with **HEAD~10**
4. **Expected**: Shows changes from 10 commits ago to current HEAD
5. Compare the two - HEAD~10 should show more changes

### Test 7: Submodule Changes Detection

**Objective**: Verify plugin detects and shows submodule changes

1. Create a scope selecting **v0.3.0** tag
2. **Expected**: Change Browser should show `libs/common-lib` as modified
3. Navigate to the submodule change entry
4. **Expected**: Should indicate submodule has been updated to a different commit
5. Create a scope selecting **submodule-init** tag
6. **Expected**: Should show the initial submodule addition

### Test 8: Submodule Internal Changes

**Objective**: Test viewing changes within the submodule itself

1. Navigate to `libs/common-lib` directory
2. Check current commit in submodule:
   ```bash
   cd libs/common-lib
   git log --oneline
   ```
3. Create a scope in the submodule context selecting **lib-v1.0.0** tag
4. **Expected**: Shows all changes in submodule files since lib-v1.0.0:
   - `Parser.java` (added)
   - `Constants.java` (updated version and values)
   - `DateUtils.java` (added)
   - `JsonUtils.java` (if on feature/enhanced-parsing branch)

### Test 9: Status Bar Widget

**Objective**: Verify status bar shows current scope

1. Create a scope named "My Test Scope" selecting **v1.0.0**
2. **Expected**: Status bar at bottom should display "My Test Scope" or similar indicator
3. Switch to different scope
4. **Expected**: Status bar updates to show new scope name
5. Clear/deactivate scope
6. **Expected**: Status bar shows default state (e.g., "HEAD")

### Test 10: Alt+H Toggle Shortcut

**Objective**: Test quick toggle between HEAD and last scope

1. Create a scope selecting **v0.5.0**
2. Press **Alt+H**
3. **Expected**: Should toggle to HEAD (no scope filter)
4. Press **Alt+H** again
5. **Expected**: Should toggle back to v0.5.0 scope
6. Verify Change Browser updates accordingly each time

### Test 11: Ctrl+D Diff Shortcut

**Objective**: Test quick diff opening

1. Create a scope selecting **v0.4.0-beta**
2. Select a changed file in Change Browser (e.g., `ConfigService.java`)
3. Press **Ctrl+D**
4. **Expected**: Diff window opens for selected file showing changes

### Test 12: F7/Shift+F7 Navigation

**Objective**: Test navigating between diff windows

1. Open multiple diffs from Change Browser for different files
2. Press **F7**
3. **Expected**: Navigate to next diff window
4. Press **Shift+F7**
5. **Expected**: Navigate to previous diff window

### Test 13: Rollback to Scope Version

**Objective**: Test rollback functionality

1. Create a scope selecting **v0.2.0-alpha**
2. Right-click on `StringUtils.java` in Change Browser
3. Select **Rollback to scope version**
4. **Expected**: File content reverts to v0.2.0-alpha version (only has isEmpty, capitalize, reverse methods)
5. Verify in editor that methods added later (truncate, countOccurrences, repeat) are removed
6. Reset the change:
   ```bash
   git checkout -- src/main/java/net/tagpad/packageA/StringUtils.java
   ```

### Test 14: Show in Project

**Objective**: Test navigation to file in Project panel

1. Create any scope with changes
2. Right-click on a file in Change Browser
3. Select **Show in Project**
4. **Expected**: Project panel expands and highlights the selected file

### Test 15: Rename Scope Tabs

**Objective**: Test renaming scope tabs

1. Create a scope with default name
2. Right-click on the scope tab
3. Select **Rename** (if available)
4. Enter a custom name like "Testing v0.5.0"
5. **Expected**: Tab displays the new custom name

### Test 16: Pre-release Version Tags

**Objective**: Test handling of alpha/beta version tags

1. Create a scope selecting **v0.2.0-alpha**
2. **Expected**: Plugin correctly parses and displays the alpha version
3. Create a scope selecting **v0.4.0-beta**
4. **Expected**: Plugin correctly handles beta version tag
5. Verify both show appropriate changes in Change Browser

### Test 17: Descriptive Tags

**Objective**: Test non-semantic version tags

1. Create a scope selecting **cleanup** tag
2. **Expected**: Shows changes from the cleanup commit onward
3. Create a scope selecting **logging-update** tag
4. **Expected**: Shows changes from when logging was added
5. Verify these work identically to version tags

### Test 18: Remote Branch References

**Objective**: Test comparing with remote branches

1. Ensure remote branches are fetched:
   ```bash
   git fetch origin
   ```
2. Create a scope selecting **origin/main**
3. **Expected**: Shows changes between remote main and local HEAD
4. Create a scope selecting **origin/feature/models-enhancement**
5. **Expected**: Shows appropriate branch differences

### Test 19: Multi-commit Range Testing

**Objective**: Verify correct diff across multiple commit types

1. Create a scope selecting **v0.1.0** (initial commit)
2. **Expected**: Shows ALL changes in the entire project:
   - All files in packageA, packageB, packageC
   - All submodule changes
   - All modifications to Main.java
3. Verify the scope shows additions, modifications, and deletions correctly

### Test 20: Submodule Branch Comparison

**Objective**: Test submodule with different branches

1. Navigate to submodule:
   ```bash
   cd libs/common-lib
   ```
2. Switch to feature branch:
   ```bash
   git checkout feature/enhanced-parsing
   ```
3. In the submodule context, create a scope selecting **lib-v1.1.0**
4. **Expected**: Shows changes on the feature branch:
   - Enhanced `Parser.java` with overloaded methods
   - New `JsonUtils.java`
5. Return to master branch and compare
6. **Expected**: Different files shown (DateUtils instead of JsonUtils)

---

## Bug Fix Verification Tests

### Test 21: Indent Guides Visibility (Issue #68)

**Objective**: Verify indent guides work correctly when Git Scope is enabled

**Bug**: IDE native line indentation guides were disabled when Git Scope plugin was enabled

1. Enable indent guides: Settings → Editor → General → Appearance → "Show indent guides"
2. Open a Java file with nested code blocks (e.g., `Main.java` or `Order.java`)
3. **Expected**: Vertical indent guide lines are visible showing code structure
4. Create a scope and activate it (e.g., select **v0.5.0**)
5. **Expected**: Indent guides remain visible and functional
6. Switch between different scopes
7. **Expected**: Indent guides continue to display correctly
8. Toggle scope on/off with **Alt+H**
9. **Expected**: Indent guides remain visible throughout

**Success**: Indent guides are always visible regardless of Git Scope state

### Test 22: "Select In" Action and Crosshair Button (Issue #59)

**Objective**: Verify "Select In" integration and crosshair navigation

**Feature**: Added "Git Scope" to "Select In" action and crosshair button

1. Open a changed file in the editor (e.g., `ConfigService.java`)
2. Right-click in the editor or use shortcut **Alt+F1**
3. Select **"Select In"** from context menu
4. **Expected**: "Git Scope" appears as an option in the Select In dialog
5. Choose "Git Scope"
6. **Expected**: Git Scope tool window opens and highlights the current file in the Change Browser
7. Look for a crosshair/target icon button in Git Scope tool window toolbar
8. Open a different file in editor (e.g., `DataService.java`)
9. Click the crosshair button in Git Scope window
10. **Expected**: Git Scope navigates to and highlights the currently open file

**Success**: Select In integration works and crosshair button locates current file

### Test 23: File List Update After Branch Switch (Issue #62)

**Objective**: Verify file list updates automatically when switching Git branches

**Bug**: File list did not update after switching branches, requiring manual tab switching

1. Create a scope selecting **v0.4.0-beta** on **main** branch
2. Note the files shown in Change Browser
3. Switch to **feature/models-enhancement** branch:
   ```bash
   git checkout feature/models-enhancement
   ```
4. **Expected**: Change Browser automatically updates to show different files:
   - New: `Customer.java`
   - Modified: `User.java`, `Product.java`
5. Switch to **feature/utils-expansion** branch:
   ```bash
   git checkout feature/utils-expansion
   ```
6. **Expected**: Change Browser automatically updates again to show:
   - New: `CollectionUtils.java`
   - Modified: `MathUtils.java`, `StringUtils.java`
7. Switch back to **main** branch:
   ```bash
   git checkout main
   ```
8. **Expected**: Change Browser updates to show main branch changes

**Success**: File list updates immediately after each branch switch without manual intervention

### Test 24: Commit Diff Window with Local Modifications (Issue #56)

**Objective**: Verify Commit: diff window works correctly with local modifications and scope changes

**Bug**: Commit panel diff failed when file was open in another tab and non-HEAD scope was selected

#### Part A: Basic Commit Diff with Local Modifications

1. Create a scope selecting **v0.5.0** (not HEAD)
2. Open `DataService.java` in editor
3. Make a local modification (e.g., add a comment):
   ```java
   // This is a test comment
   ```
4. **Expected**: Gutter shows the local change relative to v0.5.0 scope
5. Open Git tool window (View → Tool Windows → Git)
6. In the Commit tab, find `DataService.java` in the changed files list
7. Double-click to open **Commit: diff** window
8. **Expected**: Commit diff opens showing your local modification
9. Verify the diff is showing the difference correctly

#### Part B: Switch Scope While Commit Diff Open

10. With Commit: diff window still open, switch to a different scope (e.g., **v0.4.0-beta**)
11. **Expected**: Commit diff continues to work correctly, showing local changes against HEAD (not the scope)
12. Switch scope back to **v0.5.0**
13. **Expected**: Commit diff still works correctly
14. Toggle scope with **Alt+H** (to HEAD)
15. **Expected**: Commit diff remains functional

#### Part C: Close Original File

16. Keep Commit: diff window open
17. Close the original `DataService.java` editor tab (not the Commit: diff)
18. Switch focus to Commit: diff window
19. **Expected**: Commit diff still displays and works correctly
20. Switch scopes again while only Commit: diff is open
21. **Expected**: Diff continues to function

#### Part D: Restore After Closing Commit Diff

22. Close the Commit: diff window
23. Re-open `DataService.java` from Project view
24. **Expected**: File opens with gutter highlighting relative to currently selected scope
25. Verify local modifications are still shown in gutter
26. Switch scopes
27. **Expected**: Gutter updates to show changes relative to new scope

#### Part E: Multiple Commit Diffs

28. Modify another file (e.g., `ConfigService.java`)
29. Open Commit: diff for `ConfigService.java`
30. Keep first Commit: diff window also open (so you have 2 Commit: diffs)
31. Switch scopes multiple times
32. **Expected**: Both Commit: diff windows continue working correctly

**Success**: Commit: diff window always works regardless of:
- Which scope is selected
- Whether original file tab is open
- Switching scopes while diff is open
- Multiple Commit: diff windows open simultaneously

### Test 25: Scope Tab Reordering (New Feature)

**Objective**: Verify scope tabs can be reordered and persist across restarts

**Feature**: Right-click on scope tabs to move them left or right, order persists

1. Create multiple scopes (at least 3):
   - Scope 1: **v0.3.0** (name it "Version 0.3")
   - Scope 2: **v0.5.0** (name it "Version 0.5")
   - Scope 3: **v1.0.0** (name it "Version 1.0")
2. Verify initial tab order (left to right)
3. Right-click on "Version 1.0" tab (rightmost)
4. **Expected**: Context menu shows options including "Move Left" or "Move Right"
5. Select **"Move Left"**
6. **Expected**: "Version 1.0" tab moves one position to the left
7. Right-click on "Version 0.3" tab (leftmost)
8. **Expected**: Context menu shows appropriate move options
9. Select **"Move Right"**
10. **Expected**: "Version 0.3" tab moves one position to the right
11. Arrange tabs in a specific order (e.g., 1.0, 0.3, 0.5)
12. Note the exact order
13. Close IntelliJ completely
14. Reopen IntelliJ and the project
15. Open Git Scope tool window
16. **Expected**: Scope tabs appear in the same order as before restart
17. Create a new scope and reorder it
18. Restart IntelliJ again
19. **Expected**: New order is persisted

**Alternative**: If using drag-and-drop instead of context menu:
- Verify tabs can be dragged to reorder
- Verify order persists across restarts

**Success**: Tab order can be customized and persists through IDE restarts

## Expected Repository State

### Main Branch Commits (in order):
1. Initial commit
2. Add packageA with utility classes (StringUtils and MathUtils) - **v0.2.0-alpha**
3. Add packageB with service classes - **v0.3.0**
4. Add logging statements to Main.java - **logging-update**
5. Add git submodule - **submodule-init**
6. Add ArrayUtils and FileUtils - **v0.4.0-beta**
7. Remove excessive debug logging - **cleanup**
8. Update submodule to Parser version
9. Add packageC with model classes - **v0.5.0**
10. Refactor packageB - **refactor-services**
11. Integrate all packages in Main.java - **v1.0.0**
12. Update submodule to feature/enhanced-parsing branch

### Feature Branches:
- **feature/models-enhancement**: 3 commits diverging from v0.5.0
- **feature/utils-expansion**: 3 commits diverging from v0.4.0-beta

### Submodule Commits:
- **master**: lib-v1.0.0 → lib-v1.1.0 → lib-v1.2.0
- **feature/enhanced-parsing**: diverges from lib-v1.1.0 with 2 additional commits

## Troubleshooting

### Submodule not showing changes
```bash
git submodule update --init --recursive
```

### Plugin not detecting changes
- Ensure you're on the correct branch
- Refresh the Git Scope tool window
- Rebuild the project if needed

### Gutter highlights not showing
- Enable in Settings → Version Control → "Highlight modified lines in gutter"
- Ensure a scope is active

## Success Criteria

After completing all test cases, you should have verified:

### Core Functionality (Tests 1-20)
- ✅ Scope creation with branches, tags, and commit hashes
- ✅ Change Browser shows correct diffs
- ✅ Line gutter highlighting works
- ✅ Keyboard shortcuts function correctly (Alt+H, Ctrl+D, F7/Shift+F7)
- ✅ Submodule changes are detected and displayed
- ✅ Status bar widget shows current scope
- ✅ Right-click actions (Show Diff, Show in Project, Rollback) work
- ✅ Plugin handles various tag formats (semantic versions, alpha/beta, descriptive names)
- ✅ Navigation between diffs works
- ✅ Plugin works in both main repo and submodule contexts
- ✅ Git references (HEAD~N) work correctly
- ✅ Remote branch comparisons work

### Bug Fixes (Tests 21-24)
- ✅ Indent guides remain visible with Git Scope enabled (Issue #68)
- ✅ "Select In" action includes Git Scope option (Issue #59)
- ✅ Crosshair button locates current file in Git Scope window (Issue #59)
- ✅ File list updates automatically after branch switch (Issue #62)
- ✅ Commit: diff window works with local modifications (Issue #56)
- ✅ Commit: diff persists through scope changes (Issue #56)
- ✅ Commit: diff works when original file tab is closed (Issue #56)
- ✅ Gutter highlighting restores correctly after closing Commit: diff (Issue #56)

### New Features (Test 25)
- ✅ Scope tabs can be reordered via right-click context menu
- ✅ Scope tab order persists across IntelliJ restarts

## Contributing

If you find any issues with this test repository or want to add more test scenarios, please open an issue or pull request.

## License

This is a test repository for the Git Scope Pro plugin. Use freely for testing purposes.
