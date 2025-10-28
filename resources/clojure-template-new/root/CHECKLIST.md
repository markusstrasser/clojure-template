# Post-Creation Checklist

After creating your project from evo-template, work through this checklist to customize it for your needs.

## Immediate (5 minutes)

- [ ] **Verify setup works**
  ```bash
  npm install
  bb lint    # Should pass
  bb test    # Should pass
  bb check   # Should compile
  ```

- [ ] **Start development**
  ```bash
  # Terminal 1
  npm start

  # Terminal 2
  clj -M:nrepl
  # In editor: connect to port 7888, then (repl/go!)

  # Browser
  open http://localhost:8080
  ```

- [ ] **Initialize git** (if not already done)
  ```bash
  git init
  git add .
  git commit -m "Initial commit from evo-template"
  bb install-hooks
  ```

## Configuration (15 minutes)

### Project Metadata

- [ ] **Update README.md**
  - Replace TODO sections
  - Add project description
  - Add license

- [ ] **Update package.json**
  - Verify `name` field
  - Add repository URL
  - Add keywords

- [ ] **Update CLAUDE.md**
  - Fill in Architecture section
  - Document your domain model
  - Add project-specific instructions

### Version Control

- [ ] **Review .gitignore**
  - Add project-specific ignores
  - Verify `.ck/` and `node_modules/` excluded

- [ ] **Setup remote**
  ```bash
  git remote add origin git@github.com:yourusername/yourproject.git
  git push -u origin main
  ```

## Customization (30 minutes)

### Domain Logic

- [ ] **Define database schema** (`src/.../db.cljc`)
  - Replace sample `create-db` with your schema
  - Add validation functions
  - Document schema in comments

- [ ] **Create test fixtures** (`test/fixtures.cljc`)
  - Add domain-specific sample data
  - Create generators for property-based tests
  - Document test data patterns

- [ ] **Update main component** (`src/.../main.cljs`)
  - Replace welcome message with your UI
  - Connect to your database schema
  - Add event handlers

### Dependencies

- [ ] **Review included libraries**
  - Keep what you need
  - Remove unused deps (update both `deps.edn` and `shadow-cljs.edn`)

- [ ] **Add project-specific deps**
  ```bash
  neil dep add :lib your/library
  # Then sync to shadow-cljs.edn manually
  bb check-deps-sync
  ```

### Build Configuration

- [ ] **Verify build targets** (`shadow-cljs.edn`)
  - Keep `:frontend` or rename
  - Add additional targets if needed (admin, mobile, etc.)

- [ ] **Update package.json scripts**
  - Adjust `watch:cljs` if you renamed `:frontend`
  - Add custom build steps

### Testing

- [ ] **Write first real test**
  ```clojure
  ;; test/.../your_logic_test.cljc
  (deftest your-first-test
    (is (= expected actual)))
  ```

- [ ] **Verify test runner**
  ```bash
  bb test
  (repl/rt!)    # In REPL
  ```

## Optional Enhancements (1 hour)

### CI/CD

- [ ] **Enable GitHub Actions**
  - Push to GitHub
  - Verify `.github/workflows/ci.yml` runs
  - Fix any failing checks

- [ ] **Add badges to README**
  ```markdown
  ![CI](https://github.com/user/repo/workflows/CI/badge.svg)
  ```

### Skills (AI Workflows)

- [ ] **Add first skill** (see `skills/README.md`)
  ```bash
  mkdir skills/my-skill
  # Add SKILL.md and run.sh
  ```

- [ ] **Document in CLAUDE.md**

### Quality Tools

- [ ] **Configure module boundaries** (`.clj-kondo/config.edn`)
  ```clojure
  :dependencies {my-app.db #{my-app.db}
                 my-app.api #{my-app.db}}
  ```

- [ ] **Add custom lint rules**
  - Document forbidden patterns
  - Add to `:linters` config

### Semantic Search

- [ ] **Build embeddings index**
  ```bash
  bb index
  ```

- [ ] **Test search**
  ```bash
  ck --sem "your query" src/
  ```

### Documentation

- [ ] **Add architecture diagrams** (if needed)
  - Create `docs/` directory
  - Add diagrams, ADRs, etc.

- [ ] **Document key decisions**
  - Why this library over that?
  - Trade-offs made
  - Future considerations

## Production Readiness (varies)

Only needed if deploying:

- [ ] **Add deployment scripts**
  - Production build config
  - Environment management
  - Deployment automation

- [ ] **Add monitoring**
  - Error tracking
  - Performance monitoring
  - User analytics

- [ ] **Security review**
  - Audit dependencies
  - Add CSP headers
  - Review authentication

- [ ] **Optimize build**
  - Code splitting
  - Tree shaking
  - Minification settings

## Validation

After completing setup, verify:

```bash
# Quality gates pass
bb lint && bb check && bb test

# Development works
npm start
# Open http://localhost:8080
# See your UI (not template default)

# REPL works
(repl/go!)
(repl/rt!)    # Tests pass

# Git hooks work
git commit -m "test"
# Should run pre-push hook on next push

# Semantic search works (if setup)
ck --sem "test query" src/
```

## Getting Help

- **Template issues:** [evo-template repo](https://github.com/yourusername/evo-template)
- **Clojure questions:** #beginners on Clojurians Slack
- **Shadow-cljs:** [Shadow-cljs documentation](https://shadow-cljs.github.io/docs/UsersGuide.html)

## Next Steps

After completing this checklist:

1. Delete this file (`CHECKLIST.md`)
2. Commit your customizations
3. Start building features!

```bash
rm CHECKLIST.md
git add -A
git commit -m "Complete project setup"
git push
```

Happy coding! 🚀
