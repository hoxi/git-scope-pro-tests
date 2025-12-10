# Git Scope Pro - Test Repository

This repository is designed to test all features of
the [Git Scope Pro IntelliJ Plugin](https://github.com/hoxi/git-scope-pro).

## Repository Structure

### Main Repository: `git-scope-pro-tests`

- **3 branches**: `main`, `feature/models-enhancement`, `feature/utils-expansion`
- **10 tags**: v0.1.0, v0.2.0-alpha, v0.3.0, v0.4.0-beta, v0.5.0, v1.0.0, cleanup, logging-update, refactor-services,
  submodule-init
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

The tests are organized into logical groups for easier execution and understanding:

- **Section A: Basic Scope Creation & Reference Types** (Tests 1-6)
- **Section B: Scope UI & Navigation Features** (Tests 7-12)
- **Section C: Branch & Tag Operations** (Tests 13-15)
- **Section D: Submodule Testing** (Tests 17-18)
- **Section E: Bug Fixes Verification** (Tests 19-21)
- **Section F: Advanced Features** (Tests 22-25)

### Quick Reference

- **Test 1**: Create scope with v0.5.0, verify Change Browser shows correct files, open diff for ConfigService.java
- **Test 2**: Create scope with v0.2.0-alpha and v0.4.0-beta, compare differences in Change Browser
- **Test 3**: Single-click files in Change Browser to open preview tabs, double-click for permanent tabs
- **Test 4**: Create scope using commit hash (e.g., 6ee9035), verify changes shown
- **Test 5**: Rename scope tab, hover to see original hash/name, restore original name
- **Test 6**: Create scopes with HEAD~5 and HEAD~10, compare differences
- **Test 7**: Verify status bar shows current scope name, updates on scope switch
- **Test 8**: Use Alt+H to toggle between HEAD and last scope
- **Test 9**: Select file in Change Browser, press Ctrl+D to open diff
- **Test 10**: Open diff, use F7/Shift+F7 to navigate between changes within the diff
- **Test 11**: Create scope with v0.3.0, open Main.java, verify gutter highlights lines added after v0.3.0
- **Test 12**: Right-click file in Change Browser, test Show Diff, Show in Project, and Rollback (using IntelliJ built-in)
- **Test 13**: Create scope with v0.4.0, switch between branches, verify scope auto-updates (gutter and Change Browser)
- **Test 14**: Create scope with origin/feature/models-enhancement
- **Test 15**: Create local branch, verify it appears as LOCAL in scope selector, delete branch, verify it disappears
- **Test 17**: Create scope with v0.3.0, verify submodule libs/common-lib shown as modified
- **Test 18**: Create scope in submodule with lib-v1.0.0, switch to feature/enhanced-parsing branch, compare
- **Test 19**: Open Main.java, create scopes with v0.3.0 and v0.5.0, switch between them, verify diff updates
- **Test 20**: Use Alt+F1 to open Select In dialog, verify Git Scope appears; test crosshair button
- **Test 21**: Create scope, modify file, open Commit diff, switch scopes, verify diff continues working
- **Test 22**: Create 3 scopes, use right-click to reorder tabs, restart IntelliJ, verify order persists
- **Test 23**: Use Alt+F1, verify Git Scope in Select In dialog
- **Test 24**: Right-click commit in Git Log, create scope from commit
- **Test 25**: Create two scopes: main (full) and main..HEAD (common ancestor), compare differences in MathUtils.java

---

## Section A: Basic Scope Creation & Reference Types

### Test 1: Basic Scope Creation and Diff Viewing

**Objective**: Verify basic scope creation with tags and diff viewing

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

### Test 3: Single Click Preview Tab

**Objective**: Test preview tab behavior with single click in Change Browser

1. Create a scope selecting **v0.5.0** tag
2. Single-click on a file in Change Browser (e.g., `ConfigService.java`)
3. **Expected**: File opens in a preview tab (italicized tab name)
4. Single-click on another file (e.g., `DataService.java`)
5. **Expected**: Preview tab is replaced with the new file
6. Double-click on a file
7. **Expected**: File opens in a permanent tab (non-italicized)

### Test 4: Commit Hash as Scope

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

### Test 5: Tab Rename and Restore

**Objective**: Test renaming scope tabs and restoring original names

1. Create a scope with a commit hash (e.g., `6ee9035`)
2. Right-click on the scope tab and select **Rename**
3. Enter a custom name like "Logging Changes"
4. **Expected**: Tab displays "Logging Changes"
5. Hover over the renamed tab
6. **Expected**: Tooltip shows the original commit hash (e.g., `6ee9035`)
7. Right-click on the renamed tab and restore the original name
8. **Expected**: Tab displays the original commit hash again

### Test 6: Git References (HEAD~N)

**Objective**: Test relative git references

1. Create a scope with **HEAD~5**
2. **Expected**: Shows changes from 5 commits ago to current HEAD
3. Create a scope with **HEAD~10**
4. **Expected**: Shows changes from 10 commits ago to current HEAD
5. Compare the two - HEAD~10 should show more changes

---

## Section B: Scope UI & Navigation Features

### Test 7: Status Bar Widget

**Objective**: Verify status bar shows current scope

1. Create a scope named "My Test Scope" selecting **v1.0.0**
2. **Expected**: Status bar at bottom should display "My Test Scope" or similar indicator
3. Switch to different scope
4. **Expected**: Status bar updates to show new scope name
5. Clear/deactivate scope
6. **Expected**: Status bar shows default state (e.g., "HEAD")

### Test 8: Alt+H Toggle Shortcut

**Objective**: Test quick toggle between HEAD and last scope

1. Create a scope selecting **v0.5.0**
2. Press **Alt+H**
3. **Expected**: Should toggle to HEAD (no scope filter)
4. Press **Alt+H** again
5. **Expected**: Should toggle back to v0.5.0 scope
6. Verify Change Browser updates accordingly each time

### Test 9: Ctrl+D Diff Shortcut

**Objective**: Test quick diff opening

1. Create a scope selecting **v0.4.0-beta**
2. Select a changed file in Change Browser (e.g., `ConfigService.java`)
3. Press **Ctrl+D**
4. **Expected**: Diff window opens for selected file showing changes

### Test 10: F7/Shift+F7 Navigation in Diff

**Objective**: Test stepping forward and backward between changes within a diff

1. Create a scope selecting **v0.5.0** tag
2. Open `Main.java` in Change Browser and show diff
3. Press **F7** in the diff window
4. **Expected**: Navigate to next change/hunk in the diff
5. Press **Shift+F7**
6. **Expected**: Navigate to previous change/hunk in the diff

### Test 11: Line Status Gutter

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

### Test 12: Right-Click Actions (Show Diff, Show in Project, Rollback)

**Objective**: Test right-click context menu actions

#### Show Diff

1. Create a scope selecting **v0.5.0**
2. Right-click on a changed file in Change Browser
3. Select **Show Diff**
4. **Expected**: Diff window opens showing changes

#### Show in Project

5. Right-click on a file in Change Browser
6. Select **Show in Project**
7. **Expected**: Project panel expands and highlights the selected file

#### Rollback to Scope Version

8. Create a scope selecting **v0.2.0-alpha**
9. Right-click on `StringUtils.java` in Change Browser
10. Select **Rollback to scope version** (using IntelliJ's built-in rollback)
11. **Expected**: IntelliJ prompts to confirm rollback to previous HEAD version
12. Confirm the rollback
13. **Expected**: File content reverts to v0.2.0-alpha version (only has isEmpty, capitalize, reverse methods)
14. Verify in editor that methods added later (truncate, countOccurrences, repeat) are removed
15. Undo the rollback using IntelliJ's Local History or VCS → Revert

---

## Section C: Branch & Tag Operations

### Test 13: Automatic Scope Update on Branch Switch

**Objective**: Test that scope automatically updates when switching branches

1. Switch to branch **feature/models-enhancement**:
   ```bash
   git checkout feature/models-enhancement
   ```
2. Create a scope selecting **v0.4.0** tag
3. **Expected**: Shows changes specific to feature/models-enhancement branch
4. Note the files and gutter highlighting in the editor
5. Switch to branch **main**:
   ```bash
   git checkout main
   ```
6. **Expected**: Scope automatically updates - Change Browser shows different files, gutter highlighting updates
7. Switch to branch **feature/utils-expansion**:
   ```bash
   git checkout feature/utils-expansion
   ```
8. **Expected**: Scope automatically updates again - Change Browser and gutter reflect the new branch
9. Verify that both Change Browser and gutter highlighting update automatically with each branch switch

### Test 14: Remote Branch References

**Objective**: Test comparing with remote branches

1. Return to **main** branch and ensure remote branches are fetched:
   ```bash
   git checkout main
   git fetch origin
   ```
2. Create a scope selecting **origin/feature/models-enhancement**
3. **Expected**: Shows appropriate branch differences

### Test 15: Local Branch Creation and Deletion

**Objective**: Verify local branches appear/disappear correctly in scope selector

1. Ensure you're on **main** branch
2. Open Git Scope tool window and click to create a new scope
3. In the branch/tag selector, verify you see existing branches marked as:
    - LOCAL: `main`, `feature/models-enhancement`, `feature/utils-expansion`
    - REMOTE: `origin/main`, `origin/feature/models-enhancement`, `origin/feature/utils-expansion`
4. Create a new local branch from current position:
   ```bash
   git checkout -b test/local-branch-verification
   ```
5. Open the scope selector again (or create a new scope)
6. **Expected**: `test/local-branch-verification` appears in the list marked as LOCAL
7. Select this local branch as the scope base
8. **Expected**: Scope is created successfully
9. Make a small change to verify scope is working:
   ```bash
   echo "// Test comment" >> src/main/java/net/tagpad/Main.java
   ```
10. **Expected**: Change Browser shows `Main.java` as modified relative to the branch point
11. Switch back to **main** branch:
    ```bash
    git checkout main
    ```
12. Delete the local test branch:
    ```bash
    git branch -d test/local-branch-verification
    ```
13. Open scope selector again
14. **Expected**: `test/local-branch-verification` no longer appears in the LOCAL branches list
15. If you had a scope using that branch, verify it either:
    - Shows an error/warning that the branch no longer exists, OR
    - Becomes inactive/invalid

**Success**:

- Local branches are clearly marked as LOCAL
- New local branches immediately appear in scope selector
- Deleted local branches immediately disappear from scope selector
- Plugin handles branch deletion gracefully

---

## Section D: Submodule Testing

### Test 17: Submodule Changes Detection

**Objective**: Verify plugin detects and shows submodule changes

1. Create a scope selecting **v0.3.0** tag
2. **Expected**: Change Browser should show `libs/common-lib` as modified
3. Select the submodule in Change Browser
4. **Expected**: Should indicate submodule has been updated to a different commit
5. Create a scope selecting **submodule-init** tag
6. **Expected**: Should show the initial submodule addition

### Test 18: Submodule Internal Changes and Branch Comparison

**Objective**: Test viewing changes within the submodule itself and branch comparison

#### Part A: Submodule Internal Changes

1. Create a scope in the submodule context selecting **lib-v1.0.0** tag
2. **Expected**: Shows all changes in submodule files since lib-v1.0.0:
    - `Parser.java` (added)
    - `Constants.java` (updated version and values)
    - `DateUtils.java` (added)

#### Part B: Submodule Branch Comparison

3. Switch to feature branch:
   ```bash
   cd libs/common-lib
   git checkout feature/enhanced-parsing
   ```
4. Create a scope selecting **lib-v1.1.0**
5. **Expected**: Shows changes on the feature branch:
    - Enhanced `Parser.java` with overloaded methods
    - New `JsonUtils.java`
6. Return to master branch and compare:
   ```bash
   git checkout master
   ```
7. **Expected**: Different files shown (DateUtils instead of JsonUtils)

---

## Section E: Bug Fixes Verification

### Test 19: Scope Diff for Specific File (Issue #68)

**Objective**: Verify switching between different scopes for a specific file shows correct diffs

**Bug**: IDE native line indentation guides were disabled when Git Scope plugin was enabled

1. Return to main repository root (if in submodule)
2. Open `Main.java` in the editor
3. Create a scope selecting **v0.3.0** tag
4. **Expected**: Gutter shows lines added after v0.3.0
5. Right-click on `Main.java` in Change Browser and select **Show Diff**
6. **Expected**: Diff shows changes from v0.3.0 to HEAD
7. Create another scope selecting **v0.5.0** tag
8. Right-click on `Main.java` in Change Browser and select **Show Diff**
9. **Expected**: Diff shows changes from v0.5.0 to HEAD (fewer changes than v0.3.0)
10. Switch between the two scopes
11. **Expected**: Diff and gutter highlighting update automatically to reflect the selected scope

**Success**: Diff correctly shows changes for the selected scope, and switching scopes updates the diff accordingly

### Test 20: "Select In" Action and Crosshair Button (Issue #59)

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

### Test 21: Commit Diff Window with Local Modifications (Issue #56)

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

---

## Section F: Advanced Features

### Test 22: Scope Tab Reordering

**Objective**: Verify scope tabs can be reordered and persist across restarts

**Feature**: Right-click on scope tabs to move them left or right, order persists

**Note**: Drag-and-drop for tab reordering is not supported

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

**Success**: Tab order can be customized using right-click context menu and persists through IDE restarts

### Test 23: "Select In" Dialog Integration

**Objective**: Test project panel filter and "Select In" dialog integration

1. Create any scope with changes
2. Use **Alt+F1** to open "Select In" dialog
3. Verify "Git Scope" appears as an option
4. Test navigation from Project panel to Git Scope

### Test 24: Git Panel Integration

**Objective**: Test ability to use commits as scopes directly from Git panel

1. Open Git tool window (View → Tool Windows → Git)
2. Navigate to Log tab
3. Right-click on a specific commit in the history
4. **Expected**: Context menu includes option to create scope from this commit
5. Create scope from commit
6. **Expected**: New scope tab is created using that commit as the base
7. Verify the scope works correctly

### Test 25: Common Ancestor Mode

**Objective**: Test "Only Changes Since Common Ancestor" mode to show differences between branches

1. Ensure you're on **main** branch
2. Create a scope named "main full" selecting **main** branch (without common ancestor mode)
3. **Expected**: Shows all changes from main to HEAD
4. Create another scope named "main..HEAD" selecting **main** with **Only Changes Since Common Ancestor** enabled
5. **Expected**: Shows only changes unique to current branch since diverging from main
6. Compare the two scopes:
    - "main full" should show more changes
    - "main..HEAD" should show only changes since the common ancestor
7. Open `MathUtils.java` in the editor
8. Switch between the two scopes
9. **Expected**: `MathUtils.java` shows different gutter highlighting:
    - "main full" highlights all lines different from main branch tip
    - "main..HEAD" highlights only lines changed since common ancestor
10. Verify the diffs shown in Change Browser differ between the two scopes

**Success**: Common ancestor mode correctly identifies and shows only changes since the point where branches diverged

---

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

### Core Functionality (Section A-B: Tests 1-12)

- ✅ Scope creation with branches, tags, and commit hashes
- ✅ Change Browser shows correct diffs
- ✅ Preview tabs work with single-click, permanent tabs with double-click
- ✅ Line gutter highlighting works
- ✅ Keyboard shortcuts function correctly (Alt+H, Ctrl+D, F7/Shift+F7)
- ✅ Status bar widget shows current scope
- ✅ Right-click actions (Show Diff, Show in Project, Rollback) work
- ✅ Plugin handles various tag formats (semantic versions, alpha/beta, descriptive names)
- ✅ Navigation between changes in diffs works
- ✅ Git references (HEAD~N) work correctly
- ✅ Scope renaming works with tooltip showing original name and ability to restore

### Branch & Tag Operations (Section C: Tests 13-15)

- ✅ Scope automatically updates when switching branches
- ✅ Remote branch comparisons work
- ✅ Local branches are clearly marked as LOCAL
- ✅ New local branches immediately appear in scope selector
- ✅ Deleted local branches immediately disappear from scope selector
- ✅ Plugin handles branch deletion gracefully

### Submodule Testing (Section D: Tests 17-18)

- ✅ Submodule changes are detected and displayed
- ✅ Plugin works in submodule contexts
- ✅ Submodule branch comparisons work

### Bug Fixes (Section E: Tests 19-21)

- ✅ Scope diff for specific files shows correct changes when switching scopes
- ✅ "Select In" action includes Git Scope option (Issue #59)
- ✅ Crosshair button locates current file in Git Scope window (Issue #59)
- ✅ Commit: diff window works with local modifications (Issue #56)
- ✅ Commit: diff persists through scope changes (Issue #56)
- ✅ Commit: diff works when original file tab is closed (Issue #56)
- ✅ Gutter highlighting restores correctly after closing Commit: diff (Issue #56)

### Advanced Features (Section F: Tests 22-25)

- ✅ Scope tabs can be reordered via right-click context menu (drag-and-drop not supported)
- ✅ Scope tab order persists across IntelliJ restarts
- ✅ "Select In" dialog integration works
- ✅ Git panel integration allows creating scopes from commits
- ✅ Common ancestor mode correctly shows differences since branch divergence

## Contributing

If you find any issues with this test repository or want to add more test scenarios, please open an issue or pull
request.

## License

This is a test repository for the Git Scope Pro plugin. Use freely for testing purposes.
