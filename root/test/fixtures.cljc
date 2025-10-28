(ns fixtures
  "Test fixtures and sample data generators

  TODO: Add your domain-specific test data here")

(def sample-db
  "Sample database for testing"
  {:version 1
   :data {:items []}})

(defn generate-id
  "Generate a test ID"
  []
  (str "test-" (random-uuid)))

(comment
  ;; REPL experiments
  sample-db
  (generate-id)
  )
