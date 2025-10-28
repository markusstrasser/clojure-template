// @ts-check
const { test, expect } = require('@playwright/test');

/**
 * Integration tests for {{name}}
 *
 * Run these tests with:
 *   npm run test:e2e
 *
 * Or with UI:
 *   npm run test:e2e:ui
 */

test.describe('{{name}} - Homepage', () => {
  test('loads successfully', async ({ page }) => {
    await page.goto('http://localhost:8080');

    // Check main title
    await expect(page.locator('h1')).toContainText('Welcome to {{name}}');
  });

  test('displays REPL hint', async ({ page }) => {
    await page.goto('http://localhost:8080');

    // Check REPL instruction is visible
    const codeBlock = page.locator('code').filter({ hasText: '(repl/go!)' });
    await expect(codeBlock).toBeVisible();
  });

  test('has correct page structure', async ({ page }) => {
    await page.goto('http://localhost:8080');

    // Should have centered layout
    const container = page.locator('div.min-h-screen.flex.items-center.justify-center');
    await expect(container).toBeVisible();

    // Should have text content
    const textDiv = page.locator('div.text-center');
    await expect(textDiv).toBeVisible();
  });
});

test.describe('{{name}} - Console Errors', () => {
  test('has no console errors on load', async ({ page }) => {
    const errors = [];

    page.on('console', msg => {
      if (msg.type() === 'error') {
        errors.push(msg.text());
      }
    });

    await page.goto('http://localhost:8080');
    await page.waitForLoadState('networkidle');

    // Should have no console errors
    expect(errors).toHaveLength(0);
  });

  test('has no uncaught exceptions', async ({ page }) => {
    const exceptions = [];

    page.on('pageerror', exception => {
      exceptions.push(exception.message);
    });

    await page.goto('http://localhost:8080');
    await page.waitForLoadState('networkidle');

    // Should have no uncaught exceptions
    expect(exceptions).toHaveLength(0);
  });
});

test.describe('{{name}} - JavaScript Loading', () => {
  test('loads main.js successfully', async ({ page }) => {
    const response = await page.goto('http://localhost:8080');

    // Page should load successfully
    expect(response.status()).toBe(200);

    // JavaScript should be loaded
    const scripts = await page.locator('script').count();
    expect(scripts).toBeGreaterThan(0);
  });

  test('renders app div', async ({ page }) => {
    await page.goto('http://localhost:8080');

    // App container should exist
    const appDiv = page.locator('#app');
    await expect(appDiv).toBeVisible();

    // Should have content
    const content = await appDiv.textContent();
    expect(content).toBeTruthy();
    expect(content.length).toBeGreaterThan(0);
  });
});

test.describe('{{name}} - Hot Reload (Manual Test)', () => {
  test('shows reload message in console', async ({ page }) => {
    const messages = [];

    page.on('console', msg => {
      if (msg.type() === 'log') {
        messages.push(msg.text());
      }
    });

    await page.goto('http://localhost:8080');
    await page.waitForLoadState('networkidle');

    // Initial load should show "Starting {{name}}..."
    const startMessage = messages.find(m => m.includes('Starting'));
    expect(startMessage).toBeTruthy();
  });
});

/**
 * Performance tests
 */
test.describe('{{name}} - Performance', () => {
  test('loads within acceptable time', async ({ page }) => {
    const startTime = Date.now();

    await page.goto('http://localhost:8080');
    await page.waitForLoadState('networkidle');

    const loadTime = Date.now() - startTime;

    // Should load in under 3 seconds
    expect(loadTime).toBeLessThan(3000);
  });

  test('has no slow network requests', async ({ page }) => {
    const slowRequests = [];

    page.on('response', async response => {
      const timing = response.timing();
      if (timing && timing.responseEnd > 1000) {
        slowRequests.push({
          url: response.url(),
          time: timing.responseEnd
        });
      }
    });

    await page.goto('http://localhost:8080');
    await page.waitForLoadState('networkidle');

    // Should have no requests taking longer than 1 second
    expect(slowRequests).toHaveLength(0);
  });
});
