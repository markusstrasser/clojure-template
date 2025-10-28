# {{name}} - AI Agent Guide

> **Template:** Based on evo-template - batteries-included Clojure/ClojureScript tooling
> **Project:** {{description}}

## The Start

This project uses AI-friendly development patterns:
- Solo developer using AI agents as helpers
- Focus on 80/20, not performance or production use
- Keep it simple - prioritize debuggability over cleverness

**Development Philosophy:**
- REPL-driven development
- Quality gates before commit
- Progressive disclosure for context management
- Skills for complex workflows

---

## Unified Toolchain

### Babashka Tasks (bb)

```bash
# Quality gates
bb lint check test check-deps-sync

# Cache
bb clean

# Development
bb dev repl-health install-hooks help
```

### llmx CLI (Agent Scripts)

All agent scripts use `llmx` - unified CLI for 100+ LLM providers via LiteLLM.

```bash
llmx "prompt"                                    # Default (Google)
llmx --provider openai --model gpt-5-pro "prompt"
llmx --provider xai "prompt"                    # Grok
llmx --compare "tabs or spaces?"                # Compare providers
cat code.txt | llmx --provider google "analyze" # Pipe input
```

**Model selection:**
- **gemini-2.5-pro**: High token queries, large repos, sessions
- **gpt-5-codex**: Code review, architecture, taste (use `--reasoning-effort high`)
- **grok-4-latest**: Quick queries, fallback

---

## Skills

Filesystem-based workflows with progressive disclosure (L1: metadata, L2: instructions, L3: resources).

Add your project-specific skills in `skills/` directory. Template includes:

```
skills/
  README.md          # Skills overview
  .gitkeep           # Keep directory in git
```

**Example skills to add:**
- Code research workflows
- Diagnostics and health checks
- Session memory search
- Architectural decision workflows

See evo project for skill examples.

---

## Dev Tooling

### REPL Quick Start

```clojure
;; One-command startup (recommended):
(require '[repl :as repl])
(repl/go!)  ; Connect, load namespaces, health check

;; Manual:
(repl/connect!)  (repl/init!)

;; Tests:
(repl/rt!)              ; Run all tests
(repl/rq! 'core-test)   ; Reload and run specific test
```

**Location:** `dev/repl/init.cljc`

### Clojure+ Enhancements

The project uses [clojure-plus](https://github.com/tonsky/clojure-plus) for improved REPL development experience.

**Available features:**

1. **#p Debug Macro** (clojure+.hashp)
   ```clojure
   (let [x 5
         y #p (+ x 2)]  ; prints: #p (+ x 2) [repl:1] 7
     (* x y))
   ```

2. **Better Printing** (clojure+.print)
   - Atoms, refs, volatiles: `#atom 123` instead of `#object[...]`
   - Arrays: `#ints [1 2 3]` instead of `#object["[I" ...]`

3. **Improved Errors** (clojure+.error)
   - Clojure-aware stack traces
   - Reversed traces (most relevant first)
   - Colored output

4. **Better Test Output** (clojure+.test)
   - Structured test context display
   - Clearer expected/actual comparison

### Browser DEBUG Helpers

```javascript
DEBUG.summary()       // State overview
DEBUG.events()        // All events
DEBUG.reload()        // Hard reload
```

### Hot Reload

**Status:** ✅ Working by default (shadow-cljs auto-reload enabled)

- Shadow-cljs automatically reloads code changes for `:browser` targets
- File changes trigger incremental compilation
- Use `DEBUG.reload()` for hard reload if needed

### Key Files

- `dev/repl/init.cljc` - REPL helpers
- `test/fixtures.cljc` - Test data generators (customize for your domain)

---

## Architecture

**CUSTOMIZE THIS SECTION** for your project:

- Document your database schema
- Explain core operations
- Describe state management approach
- List key namespaces and their purposes

**Testing:**
- Run: `bb test` or `(repl/rt!)`
- Tests in `test/` directory mirror `src/` structure
- Use property-based testing with `test.check` for complex logic

---

## MCP Integration

**Active MCPs (configured in .mcp.json):**
- `clojure-shadow-cljs` - Full REPL + file editing via shadow-cljs nREPL
- `beads` - Issue tracking and workflow management
- `chrome-devtools` - Browser automation and debugging
- `tournament` - Bradley-Terry ranking via Swiss-Lite tournaments

**Add more MCPs as needed:**
- Edit `.mcp.json`
- See MCP documentation for available servers

---

## Quality Gates

### Pre-commit Hooks

Run `bb install-hooks` to install. Validates:
- Linting (clj-kondo)
- Tests pass
- CLJS imports valid
- Namespace/path alignment

### Linting

Config: `.clj-kondo/config.edn`

Enforces:
- Clojure best practices
- Namespace consistency
- Unused code detection

Run: `bb lint`

### Standing Instructions

- Maintain clean code architecture
- Prefer synchronous/pure patterns unless async explicitly justified
- Skip tests for docs-only changes; note "Tests: not run"
- **IMPORTANT:** ALWAYS TEST and SPOT CHECK edits before end of session

---

## Notes

- AGENTS.md is a symlink to CLAUDE.md - edit CLAUDE.md only
- Use `bat`, `rg`, targeted file reads for token efficiency
- Check your error catalog (if you create one) for self-diagnosis patterns
