# GitHub Actions CI

This directory contains CI/CD workflows for GitHub Actions.

## Included Workflows

### ci.yml - Continuous Integration

Runs on every push to `main`/`develop` and on pull requests.

**Steps:**
1. Setup Java 21
2. Setup Node.js 20
3. Install Clojure CLI and Babashka
4. Cache dependencies (Clojure + Node)
5. Install npm dependencies
6. Run quality gates:
   - `bb lint` - clj-kondo linting
   - `bb check` - Compilation check
   - `bb test` - Full test suite
   - `bb check-deps-sync` - Verify deps alignment

**Required secrets:** None (public builds)

## Customization

### Add deployment

```yaml
- name: Build production
  run: npm run build

- name: Deploy
  # Your deployment steps
```

### Add code coverage

```bash
# Install coverage tool
bb dep add :test lambdaisland/kaocha-cloverage

# Add to workflow
- name: Coverage
  run: bb test-coverage
```

### Add performance tests

```yaml
- name: Benchmarks
  run: bb bench
```

## Local Testing

Test CI locally using [act](https://github.com/nektos/act):

```bash
# Install act
brew install act

# Run CI workflow
act push

# Run specific job
act -j test
```
