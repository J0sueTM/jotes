(ns jotes.db.user
  (:require [next.jdbc.sql :as jdbc-sql]
            [jotes.db]
            [crypto.password.bcrypt :as bcrypt])
  (:gen-class))

(defn insert
  [{:keys [email password]}]
  (let [hashed-password (bcrypt/encrypt password)]
    (jdbc-sql/insert! jotes.db/pg-src :_user {:email email
                                              :hash hashed-password})))

(defn get-by-email
  [email]
  (jotes.db/with-connection [conn]
    (jdbc-sql/query
     conn
     [(format "SELECT * FROM _user WHERE email = '%s';" email)])))
