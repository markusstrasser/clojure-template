# Browser Tests (E2E)

End-to-end browser tests using [Playwright](https://playwright.dev/).

## Prerequisites

Playwright is already installed as a dev dependency. If you need to install browsers:

```bash
npx playwright install
```

## Running Tests

### Run all tests

```bash
npm run test:e2e
```

### Run with UI (interactive mode)

```bash
npm run test:e2e:ui
```

### Run specific test file

```bash
npx playwright test integration.spec.js
```

### Run in headed mode (see browser)

```bash
npx playwright test --headed
```

### Run specific browser

```bash
npx playwright test --project=chromium
npx playwright test --project=firefox
npx playwright test --project=webkit
```

## Test Structure

Tests are organized by feature area:

- **Homepage** - Basic rendering and structure
- **Console Errors** - JavaScript error detection
- **JavaScript Loading** - Script loading and execution
- **Performance** - Load time and request speed

## Writing Tests

Create new test files in `test-browser/` directory:

```javascript
// @ts-check
const { test, expect } = require('@playwright/test');

test.describe('Feature Name', () => {
  test('does something', async ({ page }) => {
    await page.goto('http://localhost:8080');

    // Your test here
    await expect(page.locator('selector')).toBeVisible();
  });
});
```

## Debugging

### Debug mode

```bash
npx playwright test --debug
```

### Generate test code

```bash
npx playwright codegen http://localhost:8080
```

### View test report

```bash
npx playwright show-report
```

## CI Integration

Tests are configured to:
- Run on all commits in `.github/workflows/ci.yml`
- Retry failed tests 2 times on CI
- Run sequentially on CI (parallel locally)
- Take screenshots on failure
- Generate HTML report

## Tips

1. **Keep tests fast** - Use `waitForLoadState('networkidle')` sparingly
2. **Test user behavior** - Click, type, navigate like a real user
3. **Be resilient** - Use `waitFor` and retries for flaky elements
4. **Isolate tests** - Each test should be independent
5. **Use data-testid** - Add `data-testid` attributes for reliable selectors

Example:
```clojure
;; In your ClojureScript component:
[:button {:data-testid "submit-button"} "Submit"]
```

```javascript
// In your test:
await page.locator('[data-testid="submit-button"]').click();
```
