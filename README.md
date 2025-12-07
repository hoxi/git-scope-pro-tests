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
- ✅ Scope creation with branches, tags, and commit hashes
- ✅ Change Browser shows correct diffs
- ✅ Line gutter highlighting works
- ✅ Keyboard shortcuts function correctly
- ✅ Submodule changes are detected and displayed
- ✅ Status bar widget shows current scope
- ✅ Right-click actions (Show Diff, Show in Project, Rollback) work
- ✅ Plugin handles various tag formats (semantic versions, alpha/beta, descriptive names)
- ✅ Navigation between diffs works
- ✅ Plugin works in both main repo and submodule contexts

## Contributing

If you find any issues with this test repository or want to add more test scenarios, please open an issue or pull request.

## License

This is a test repository for the Git Scope Pro plugin. Use freely for testing purposes.
