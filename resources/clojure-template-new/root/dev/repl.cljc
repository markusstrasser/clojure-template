(ns repl
  "REPL development utilities for {{name}}

  Quick start:
    (repl/go!)  ; One-command startup

  Testing:
    (repl/rt!)              ; Run all tests
    (repl/rq! 'namespace)   ; Run specific test namespace"
  #?(:cljs (:require-macros [repl :refer [measure-time]]))
  (:require
   #?@(:clj [[clojure.test :as t]
             [clojure-plus.hashp :as hashp]
             [clojure-plus.print :as print]
             [clojure-plus.error :as error]
             [clojure-plus.test :as test]]
       :cljs [[cljs.test :as t]])))

;;; ============================================================
;;; REPL Connection & Initialization
;;; ============================================================

(defn connect!
  "Connect to shadow-cljs nREPL (ClojureScript only).
  In Clojure, this is a no-op."
  []
  #?(:clj (println "✓ Clojure REPL already connected")
     :cljs (println "Connect to shadow-cljs REPL via your editor")))

(defn init!
  "Initialize REPL environment with:
  - Clojure+ enhancements (better printing, errors, tests)
  - Project namespace loading

  Called automatically by (go!)"
  []
  #?(:clj
     (do
       ;; Install clojure+ enhancements
       (println "Installing clojure+ enhancements...")
       (hashp/install!)
       (print/install!)
       (error/install!)
       (test/install!)

       ;; Load your project namespaces here
       (println "Loading project namespaces...")
       ;; TODO: Add your namespaces
       ;; (require '[{{top/ns}}.{{name}}.db :as db])
       ;; (require '[{{top/ns}}.{{name}}.core :as core])

       (println "✓ REPL initialized"))
     :cljs
     (println "✓ ClojureScript REPL ready")))

(defn quick-health-check!
  "Quick diagnostics check"
  []
  (println "=== REPL Health Check ===")
  (println "Environment:" #?(:clj "Clojure" :cljs "ClojureScript"))
  #?(:clj (println "Namespaces loaded:" (count (all-ns))))
  (println "✓ REPL operational"))

(defn go!
  "One-command REPL startup.

  Usage:
    (require '[repl :as repl])
    (repl/go!)"
  []
  (connect!)
  (init!)
  (quick-health-check!)
  (println "\n✓ REPL ready! Try (repl/rt!) to run tests"))

;;; ============================================================
;;; Test Runners
;;; ============================================================

#?(:clj
   (defn rt!
     "Run all tests"
     []
     (println "Running all tests...")
     (t/run-all-tests #".*-test$")))

#?(:clj
   (defn rq!
     "Reload and run specific test namespace

     Usage:
       (repl/rq! 'db-test)
       (repl/rq! '{{top/ns}}.{{name}}.db-test)"
     [test-ns]
     (let [test-ns-sym (if (namespace test-ns)
                         test-ns
                         (symbol (str "{{top/ns}}.{{name}}." test-ns)))]
       (println (str "Reloading and running " test-ns-sym "..."))
       (require test-ns-sym :reload)
       (t/test-ns test-ns-sym))))

;;; ============================================================
;;; Project-Specific Helpers
;;; ============================================================

;; TODO: Add your domain-specific REPL helpers here
;; Examples:
;;
;; (defn sample-db!
;;   "Load sample database for testing"
;;   [fixture-key]
;;   ...)
;;
;; (defn inspect!
;;   "Inspect database state"
;;   [path]
;;   ...)

;;; ============================================================
;;; Utility Macros
;;; ============================================================

#?(:clj
   (defmacro measure-time
     "Measure execution time of an expression"
     [expr]
     `(let [start# (. System (nanoTime))
            result# ~expr
            elapsed# (/ (double (- (. System (nanoTime)) start#)) 1000000.0)]
        (println (str "Elapsed time: " elapsed# " ms"))
        result#)))

(comment
  ;; Quick start
  (go!)

  ;; Run tests
  (rt!)
  (rq! 'db-test)

  ;; Measure performance
  #?(:clj (measure-time (Thread/sleep 100)))

  ;; TODO: Add your domain-specific REPL experiments here
  )
