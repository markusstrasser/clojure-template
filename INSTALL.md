# Installation Guide

## Quick Test (Local)

```bash
# From evo-template directory
clojure -Tnew create :template ./evo-template :name test/my-app :target-dir /tmp/my-app

# Navigate and verify
cd /tmp/my-app
npm install
bb lint
bb test
npm start
```

## Install as Template

### Option 1: Local Install (for development)

```bash
# Install from local directory
cd evo-template
clojure -Tnew install :template local/evo-template :version '"0.1.0"'

# Use it
clojure -Tnew create :template local/evo-template :name com.example/my-app
```

### Option 2: Git Repository (recommended)

**After pushing to GitHub:**

```bash
# Install from git
clojure -Ttools install-latest \
  :lib io.github.YOURUSERNAME/evo-template \
  :as evo-template

# Or install specific version
clojure -Ttools install \
  :lib io.github.YOURUSERNAME/evo-template \
  :git/tag "v0.1.0" \
  :as evo-template

# Use it
clojure -Tnew create :template evo-template :name com.example/my-app
```

### Option 3: Maven/Clojars (future)

**After publishing to Clojars:**

```bash
# Install from Clojars
clojure -Ttools install-latest \
  :lib io.github.YOURUSERNAME/evo-template \
  :as evo-template

# Use it
clojure -Tnew create :template evo-template :name com.example/my-app
```

## Template Parameters

```bash
# All parameters
clojure -Tnew create \
  :template evo-template \
  :name com.example/my-app \
  :target-dir ~/projects/my-app \
  :description '"My awesome app"'

# Minimal (uses defaults)
clojure -Tnew create :template evo-template :name my-app
```

**Available parameters:**
- `:name` - Project name (required, format: `namespace/project` or `project`)
- `:target-dir` - Where to create project (default: `./project-name`)
- `:description` - Project description (default: empty)

## Verification

After creating a project, verify:

```bash
cd my-app

# 1. Files exist
ls -la deps.edn shadow-cljs.edn bb.edn .mcp.json

# 2. Linting works
bb lint

# 3. Tests compile and pass
bb test

# 4. Dev server starts
npm install
npm start
# Open http://localhost:8080

# 5. REPL works
clj -M:nrepl
# In editor, connect to port 7888:
(require '[repl :as repl])
(repl/go!)
# Should see: ✓ REPL ready!
```

## Troubleshooting Install

### Template not found

```bash
# List installed templates
clojure -Tnew list

# If missing, reinstall
clojure -Tnew install :template ./evo-template :version '"0.1.0"'
```

### deps-new not installed

```bash
# Install deps-new globally
clojure -Ttools install-latest :lib io.github.seancorfield/deps-new :as new
```

### Template variables not substituting

Check `template.edn`:
- Paths in `:transform` must match actual files in `root/`
- Variables use `{{var}}` syntax
- File paths with variables must match pattern (e.g., `{{top/file}}/{{main/file}}`)

## Publishing Template

### To GitHub

```bash
# 1. Create repo on GitHub: yourusername/evo-template
# 2. Push template
cd evo-template
git init
git add .
git commit -m "Initial evo-template"
git remote add origin git@github.com:yourusername/evo-template.git
git push -u origin main

# 3. Tag release
git tag -a v0.1.0 -m "Release 0.1.0"
git push origin v0.1.0
```

Users can now install via:
```bash
clojure -Tnew create \
  :template io.github.yourusername/evo-template \
  :name com.example/my-app
```

### To Clojars

**TODO:** Add Clojars publishing instructions if needed.

## Updating Template

```bash
# After making changes
cd evo-template

# Test locally
clojure -Tnew create :template . :name test/verify :target-dir /tmp/verify
cd /tmp/verify
npm install && bb test

# If good, increment version and publish
git tag -a v0.2.0 -m "Release 0.2.0"
git push origin v0.2.0
```

Users update via:
```bash
clojure -Ttools install-latest \
  :lib io.github.yourusername/evo-template \
  :as evo-template
```
