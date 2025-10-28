# evo-template

Batteries-included Clojure/ClojureScript project template with AI-friendly tooling.

**Philosophy:** 80/20 development with REPL-driven workflow, quality gates, and progressive disclosure for AI agents.

## Features

### Batteries Included

**Dependencies (all pre-configured):**
- **UI:** Replicant (React-like rendering)
- **Schema:** Malli (data validation)
- **Database:** DataScript (in-memory relational)
- **Data:** Specter (deep transformations), Medley (functional utils)
- **Testing:** test.check (property-based), Kaocha (test runner)
- **REPL:** clojure-plus (enhanced printing, errors, tests)
- **Async:** Promesa (modern promises)
- **CSS:** Tailwind CSS (utility-first)
- **Build:** shadow-cljs (fast ClojureScript compilation)

**Tooling:**
- **Babashka tasks** - Quality gates (lint, check, test), cache management
- **clj-kondo** - Comprehensive linter with best practices
- **Git hooks** - Pre-commit validation
- **REPL helpers** - One-command startup, test runners
- **Semantic search** - `ck` tool for code/session search

**MCP Servers (AI integration):**
- **clojure-shadow-cljs** - REPL access for AI agents
- **beads** - Issue tracking
- **chrome-devtools** - Browser automation
- **tournament** - AI-assisted decision making

## Installation

### Prerequisites

```bash
# Global tools (one-time setup)
clojure -Ttools install-latest :lib io.github.seancorfield/deps-new :as new
brew install babashka/brew/neil
npm install -g shadow-cljs
brew install clj-kondo

# Optional (for semantic search)
npm install -g repomix
```

### Install Template

**Option 1: Local (for testing)**
```bash
cd evo-template
clojure -Tnew install :template io.github.yourusername/evo-template :version '"0.1.0"'
```

**Option 2: From Git (recommended)**
```bash
# TODO: After pushing to GitHub
clojure -Tnew create :template io.github.yourusername/evo-template :name com.example/my-app
```

## Usage

### Create New Project

```bash
# Create project
clojure -Tnew create :template evo-template :name com.example/my-app

# Navigate
cd my-app

# Install Node dependencies
npm install

# Setup git hooks
bb install-hooks

# Build semantic search index
bb index

# Start development
npm start
```

### What You Get

```
my-app/
├── deps.edn              # Clojure dependencies (batteries included)
├── shadow-cljs.edn       # ClojureScript build config
├── bb.edn                # Babashka tasks (lint, test, check, etc.)
├── package.json          # Node.js dependencies
├── .mcp.json             # MCP servers for AI agents
├── CLAUDE.md             # AI agent guide
├── README.md             # Project documentation
│
├── src/                  # Source code
│   └── com/example/my_app/
│       ├── main.cljs     # Entry point
│       └── db.cljc       # Database (customize)
│
├── test/                 # Tests (mirrors src/)
│   ├── com/example/my_app/
│   │   └── db_test.cljc
│   └── fixtures.cljc     # Test data generators
│
├── dev/                  # REPL helpers
│   └── repl/
│       └── init.cljc     # One-command startup (repl/go!)
│
├── public/               # Static assets
│   └── index.html
│
├── scripts/              # Automation
│   ├── install-hooks.sh
│   ├── check-version-sync.sh
│   └── hooks/
│       └── pre-push      # Quality gates
│
├── skills/               # Agent workflows (add as needed)
│   └── README.md
│
├── .clj-kondo/           # Linter config
│   └── config.edn
│
└── .gitignore            # Sensible defaults
```

## Post-Creation Checklist

### 1. Verify Setup (2 min)

```bash
# Check quality gates
bb lint     # Should pass
bb check    # Should compile
bb test     # Sample test should pass

# Verify REPL
clj -M:nrepl &
# In editor, connect to port 7888, then:
(require '[repl :as repl])
(repl/go!)
# Should see: ✓ REPL ready!
```

### 2. Customize Project (10 min)

**Required:**
- [ ] Edit `README.md` - Update description, license
- [ ] Edit `CLAUDE.md` - Add architecture section
- [ ] Update `src/.../db.cljc` - Define your schema
- [ ] Update `test/fixtures.cljc` - Add domain test data

**Optional:**
- [ ] Add custom tasks to `bb.edn`
- [ ] Define module boundaries in `.clj-kondo/config.edn`
- [ ] Add skills in `skills/` directory
- [ ] Configure additional MCP servers in `.mcp.json`

### 3. Start Building (5 min)

```bash
# Terminal 1: Development server
npm start

# Terminal 2: REPL
clj -M:nrepl
# Then in editor:
(repl/go!)

# Open browser
open http://localhost:8080
```

## Development Workflow

### Daily Development

```bash
# Start dev server (hot reload enabled)
npm start

# In REPL:
(repl/go!)        # One-command startup
(repl/rt!)        # Run all tests
(repl/rq! 'db)    # Run specific test namespace
```

### Quality Gates (before commit)

```bash
bb lint           # Linter
bb check          # Compile check
bb test           # Full test suite
bb check-deps-sync # Verify deps.edn ↔ shadow-cljs.edn
```

**Automated:** Run `bb install-hooks` to validate on every commit.

### Common Tasks

```bash
# Dependency management
neil dep add :lib metosin/reitit      # Add dependency
neil dep upgrade                       # Update all deps
bb check-deps-sync                     # Sync to shadow-cljs.edn

# Cache management
bb clean          # Clear all caches
bb index          # Rebuild semantic search index

# Testing
bb test           # Run all tests
npm run test      # Alternative (via npm)
```

## Customization Guide

### Adding Dependencies

```bash
# 1. Add to deps.edn (using neil)
neil dep add :lib org.clojure/core.async

# 2. Sync to shadow-cljs.edn (manual)
# Copy version from deps.edn to shadow-cljs.edn :dependencies

# 3. Verify sync
bb check-deps-sync
```

### Adding Build Targets

Edit `shadow-cljs.edn`:

```clojure
:builds
{:frontend {...}  ; Default browser build

 ;; Add new target:
 :admin
 {:target :browser
  :output-dir "public/js/admin"
  :asset-path "/js/admin"
  :modules {:main {:init-fn com.example.my-app.admin/main}}}}
```

Then update `package.json`:
```json
"watch:admin": "npx shadow-cljs watch admin"
```

### Adding Skills

```bash
mkdir skills/my-skill
cat > skills/my-skill/SKILL.md <<EOF
---
name: My Skill
description: Brief description. Triggers: keywords. Requires: tools.
---

# My Skill

## Prerequisites
...
EOF

cat > skills/my-skill/run.sh <<'EOF'
#!/usr/bin/env bash
set -euo pipefail
# Your skill logic here
EOF

chmod +x skills/my-skill/run.sh
```

Document in `CLAUDE.md`.

### Module Boundaries

Edit `.clj-kondo/config.edn`:

```clojure
:dependencies {my-app.db #{my-app.db}
               my-app.api #{my-app.db my-app.schema}
               my-app.ui #{my-app.db my-app.api}}
```

Prevents circular dependencies and enforces layered architecture.

## Template Variables

When creating a project, deps-new substitutes:

- `{{name}}` → Project name (e.g., `my-app`)
- `{{raw-name}}` → Raw name without namespace
- `{{top/ns}}` → Top namespace (e.g., `com.example`)
- `{{main/ns}}` → Full namespace (e.g., `com.example.my-app`)
- `{{top/file}}` → Top directory (e.g., `com/example`)
- `{{main/file}}` → Full directory (e.g., `com/example/my_app`)
- `{{description}}` → Project description
- `{{now/year}}` → Current year

## Troubleshooting

### Tests fail after creation

```bash
# Ensure ClojureScript compiled
npx shadow-cljs compile :test
node out/tests.js
```

### REPL won't connect

```bash
# Check shadow-cljs is running
npx shadow-cljs watch frontend

# Verify nREPL port
cat shadow-cljs.edn | grep :nrepl
# Should show: :nrepl {:port 55449}

# In editor, connect to localhost:55449
```

### Deps out of sync

```bash
# Check what's different
bb check-deps-sync

# Manually sync versions from deps.edn to shadow-cljs.edn
# Or use script (TODO: automate this)
```

### Semantic search not working

```bash
# Install ck
npm install -g repomix  # Or equivalent for your platform

# Rebuild index
bb index
```

## Architecture Decisions

### Why These Libraries?

**Replicant over Reagent:**
- Simpler mental model (no reactions, just data → DOM)
- Faster (no reactivity overhead)
- Explicit re-renders

**Malli over Spec:**
- Better error messages
- Schema inference
- Runtime validation + compile-time checks

**DataScript over Re-frame:**
- Full relational queries
- Persistent data structure
- No global state atom

**Promesa over core.async:**
- Modern Promise-based API
- Better error handling
- Simpler composition

**Babashka over Make:**
- Cross-platform (Windows, macOS, Linux)
- Clojure syntax
- Rich ecosystem (neil, fs, etc.)

### Why Shadow-cljs?

- Fast incremental compilation
- Great REPL experience
- npm integration
- Active development

### Why MCP Servers?

- AI agents can read/write code via clojure-mcp
- Issue tracking via beads
- Browser automation via chrome-devtools
- Decision support via tournament

## Contributing

Found an issue or have an improvement?

1. Test locally: `clojure -Tnew create :template ./evo-template :name test/my-app`
2. Verify all features work
3. Submit PR

## License

MIT (or your chosen license)

---

**Template version:** 0.1.0
**Based on:** evo project architecture
**Maintained by:** [Your Name]
