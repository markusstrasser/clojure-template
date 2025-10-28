#!/bin/bash
# Test evo-template locally
set -euo pipefail

echo "=== Testing evo-template ==="

# Cleanup previous test
rm -rf /tmp/test-evo-template

# Create project from template
echo "Creating test project..."
clojure -Tnew create \
  :template ./evo-template \
  :name com.example/test-app \
  :target-dir /tmp/test-evo-template \
  :description '"Test application"'

cd /tmp/test-evo-template

echo "✓ Template created"

# Verify files exist
echo "Verifying files..."
for file in deps.edn shadow-cljs.edn bb.edn package.json .mcp.json CLAUDE.md README.md; do
  if [ ! -f "$file" ]; then
    echo "❌ Missing: $file"
    exit 1
  fi
done
echo "✓ All required files present"

# Check source files have correct namespace
echo "Verifying namespace substitution..."
if ! grep -q "com.example.test-app" src/com/example/test_app/main.cljs; then
  echo "❌ Namespace substitution failed in main.cljs"
  exit 1
fi
echo "✓ Namespace substitution correct"

# Install dependencies
echo "Installing npm dependencies..."
npm install --silent

# Run quality gates
echo "Running bb lint..."
bb lint

echo "Running bb check..."
bb check

echo "Running bb test..."
bb test

echo ""
echo "=== ✅ Template test PASSED ==="
echo "Test project created at: /tmp/test-evo-template"
echo ""
echo "To explore:"
echo "  cd /tmp/test-evo-template"
echo "  npm start"
