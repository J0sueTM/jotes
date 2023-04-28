(ns jotes.db.note
  (:require [next.jdbc.sql :as jdbc-sql]
            [jotes.db]))

(defn insert
  [txt category-id]
  (jotes.db/with-connection [conn]
    (jdbc-sql/insert! conn :note {:category_id category-id
                                  :txt txt})))

(defn get-all
  []
  (jotes.db/with-connection [conn]
    (jdbc-sql/query conn ["SELECT * FROM note;"])))

(defn update!
  [id new-data]
  (jotes.db/with-connection [conn]
    (jdbc-sql/update! conn :note new-data {:id id})))

(defn delete
  [id]
  (jotes.db/with-connection [conn]
    (jdbc-sql/delete! conn :note {:id id})))