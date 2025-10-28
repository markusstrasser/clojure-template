(ns {{raw-name}}.db-test
  (:require
   #?(:clj  [clojure.test :refer [deftest is testing]]
      :cljs [cljs.test :refer [deftest is testing]])
   [{{raw-name}}.db :as db]))

(deftest create-db-test
  (testing "create-db returns valid database"
    (let [db (db/create-db)]
      (is (db/valid-db? db))
      (is (= 1 (:version db)))
      (is (map? (:data db))))))

(deftest valid-db?-test
  (testing "valid-db? validates structure"
    (is (db/valid-db? {:version 1 :data {}}))
    (is (not (db/valid-db? {})))
    (is (not (db/valid-db? {:version 1})))
    (is (not (db/valid-db? {:data {}})))
    (is (not (db/valid-db? nil)))))
