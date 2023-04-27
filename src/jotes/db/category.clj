(ns jotes.db.category
  (:require [next.jdbc.sql :as jdbc-sql]
            [jotes.db]))

(defn insert
  [txt, color]
  (jotes.db/with-connection [conn]
    (jdbc-sql/insert! conn :category {:txt   txt
                                      :color color})))

(defn get-all
  []
  (jotes.db/with-connection [conn]
    (jdbc-sql/query conn ["SELECT * FROM category;"])))

(defn get-by-txt
  [txt]
  (jotes.db/with-connection [conn]
    (jdbc-sql/query conn ["SELECT * FROM category WHERE txt = ?;" txt])))

(defn update!
  [old-txt new-data]
  (jotes.db/with-connection [conn]
    (jdbc-sql/update! conn :category new-data {:txt old-txt})))

(defn delete
  [txt]
  (jotes.db/with-connection [conn]
    (jdbc-sql/delete! conn :category {:txt txt})))