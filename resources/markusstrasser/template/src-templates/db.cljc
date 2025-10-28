(ns {{raw-name}}.db
  "Database and state management for {{name}}

  TODO: Customize this for your domain")

(defn create-db
  "Create initial database

  TODO: Define your database schema here"
  []
  {:version 1
   :data {}})

(defn valid-db?
  "Validate database structure

  TODO: Add validation logic"
  [db]
  (and (map? db)
       (contains? db :version)
       (contains? db :data)))

(comment
  ;; REPL experiments
  (def db (create-db))
  (valid-db? db)
  ;; => true
  )
