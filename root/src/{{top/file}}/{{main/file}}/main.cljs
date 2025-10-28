(ns {{top/ns}}.{{name}}.main
  "Main entry point for {{name}}"
  (:require
   [replicant.dom :as d]
   [{{top/ns}}.{{name}}.db :as db]))

(defn app-component
  "Root application component"
  [{:keys [db]}]
  [:div {:class "min-h-screen flex items-center justify-center"}
   [:div {:class "text-center"}
    [:h1 {:class "text-4xl font-bold text-gray-900 mb-4"}
     "Welcome to {{name}}"]
    [:p {:class "text-gray-600"}
     "Edit src/{{top/file}}/{{main/file}}/main.cljs to get started"]
    [:div {:class "mt-8"}
     [:p {:class "text-sm text-gray-500"}
      "REPL: " [:code {:class "bg-gray-100 px-2 py-1 rounded"} "(repl/go!)"]]]]])

(defn render!
  "Render the application"
  []
  (let [db (db/create-db)]
    (d/render (js/document.getElementById "app")
              (app-component {:db db}))))

(defn ^:dev/after-load reload!
  "Hot reload hook - called after code changes"
  []
  (println "Reloading...")
  (render!))

(defn main
  "Application entry point"
  []
  (println "Starting {{name}}...")
  (render!))
