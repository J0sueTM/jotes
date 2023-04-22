(ns jotes.db.category
  (:require [next.jdbc.sql :as jdbc-sql]
            [jotes.db])
  (:gen-class))

(defn ^:private assoc-with-user
  [{:keys [user-id, category-id]}]
  (jdbc-sql/insert! jotes.db/rs-opts :user_category {:user-id user-id
                                                     :category-id category-id}))

(defn insert
  [{:keys [user, txt, color]}]
  (let [new-cat (jdbc-sql/insert! jotes.db/rs-opts
                                  :category {:txt txt
                                             :color color})]
    (assoc-with-user {:user-id (:id user)
                      :category-id (:id new-cat)})))
