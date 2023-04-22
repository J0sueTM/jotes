(ns jotes.db.user
  (:require [next.jdbc.sql :as jdbc-sql]
            [jotes.db]
            [crypto.password.bcrypt :as password])
  (:gen-class))

(defn insert
  [{:keys [email password]}]
  (let [hashed-password (password/encrypt password)]
    (jdbc-sql/insert! jotes.db/pg-src :_user {:email email
                                              :hash hashed-password})))
