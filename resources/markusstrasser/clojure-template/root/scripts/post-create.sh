#!/bin/bash
# Post-creation setup script
# Run this after creating a new project from evo-template
set -euo pipefail

echo "=== Post-Creation Setup ==="
echo ""

# 1. Create AGENTS.md symlink
if [ ! -L AGENTS.md ]; then
  ln -sf CLAUDE.md AGENTS.md
  echo "✓ Created AGENTS.md symlink to CLAUDE.md"
else
  echo "✓ AGENTS.md symlink already exists"
fi

# 2. Initialize git if not already
if [ ! -d .git ]; then
  git init
  echo "✓ Git initialized"

  # Initial commit
  git add .
  git commit -m "Initial commit from evo-template

- Batteries-included Clojure/ClojureScript project
- 21 pre-configured dependencies
- Quality gates (lint, check, test)
- REPL-driven development
- AI-friendly tooling (MCP, skills, CLAUDE.md)

Template version: 0.1.0"
  echo "✓ Initial commit created"
else
  echo "✓ Git already initialized"
fi

# 3. Install git hooks
echo ""
echo "Installing git hooks..."
bash scripts/install-hooks.sh

# 4. Check if npm is needed
if [ ! -d node_modules ]; then
  echo ""
  echo "📦 Installing npm dependencies..."
  npm install
else
  echo "✓ npm dependencies already installed"
fi

# 5. Build semantic search index
echo ""
echo "Building semantic search index..."
if command -v ck &> /dev/null; then
  bb index
else
  echo "⚠️  ck (semantic search) not found - skipping index"
  echo "   Install: https://github.com/cloudflare/ck"
fi

# 6. Verify setup
echo ""
echo "=== Verifying Setup ==="
echo ""
echo "Running quality gates..."
bb lint && echo "✓ Linting passed" || echo "❌ Linting failed"
bb check && echo "✓ Compilation passed" || echo "❌ Compilation failed"
bb test && echo "✓ Tests passed" || echo "❌ Tests failed"

echo ""
echo "=== ✅ Setup Complete! ==="
echo ""
echo "Next steps:"
echo "  1. Start development server:"
echo "     npm start"
echo ""
echo "  2. In another terminal, start REPL:"
echo "     clj -M:nrepl"
echo ""
echo "  3. Connect your editor to nREPL on port 7888"
echo "     Then in REPL: (require '[repl :as repl]) (repl/go!)"
echo ""
echo "  4. Open browser:"
echo "     http://localhost:8080"
echo ""
echo "Happy coding! 🚀"
