# {{name}}

{{description}}

**Template:** evo-template (batteries-included Clojure/ClojureScript)

## Quick Start

```bash
# Install dependencies
npm install

# Start development server (auto-reload on save)
npm start

# In another terminal, start REPL
clj -M:nrepl

# In your editor, connect to nREPL on port 7888
# Then in REPL:
(require '[repl :as repl])
(repl/go!)
```

Open http://localhost:8080 in your browser.

## Development

### Commands

```bash
# Development
npm start              # Start dev server with hot reload
npm run repl           # Start ClojureScript REPL

# Quality Gates
bb lint                # Run clj-kondo linter
bb check               # Lint + compile check
bb test                # Run test suite
bb check-deps-sync     # Verify deps.edn ↔ shadow-cljs.edn sync

# Cache & Index
bb clean               # Clear all caches + semantic index
bb index               # Rebuild semantic search index

# Git
bb install-hooks       # Install pre-commit hooks
```

### REPL-Driven Development

The primary workflow is REPL-driven:

```clojure
;; Start REPL (one command)
(repl/go!)

;; Run all tests
(repl/rt!)

;; Run specific test
(repl/rq! 'db-test)

;; Add your domain-specific helpers in dev/repl/init.cljc
```

### Project Structure

```
src/              # Source code
test/             # Tests (mirrors src/ structure)
dev/              # REPL helpers and dev tools
public/           # Static assets
scripts/          # Automation scripts
skills/           # Agent workflows (add as needed)
.clj-kondo/       # Linter configuration
```

## Architecture

**TODO:** Document your architecture here:
- Database schema
- State management approach
- Key namespaces and their purposes
- Design patterns and conventions

## MCP Servers

This project includes AI-friendly MCP servers (configured in `.mcp.json`):

- **clojure-shadow-cljs** - REPL integration for AI agents
- **beads** - Issue tracking
- **chrome-devtools** - Browser automation
- **tournament** - AI-assisted decision making

## Documentation

- `CLAUDE.md` - AI agent guide (comprehensive development docs)
- `bb.edn` - Task definitions and automation
- `.clj-kondo/config.edn` - Linter rules

## License

**TODO:** Add your license here
