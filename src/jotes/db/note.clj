(ns jotes.db.note
  (:require [next.jdbc.sql :as jdbc-sql]
            [jotes.db]))

(defn insert
  [{:keys [user category txt]}]
  (jdbc-sql/insert! jotes.db/rs-opts :note {:user-id (:id user)
                                            :category-id (:id category)
                                            :txt txt}))
