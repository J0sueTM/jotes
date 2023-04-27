(ns jotes.db
  (:require [next.jdbc :as jdbc]))

(def ^:private pg-db-name
  (-> (System/getenv "JOTES_DB_NAME")
      (or "jotesdb")))

(def ^:private pg-password (System/getenv "JOTES_DB_PASSWORD"))

(def pg-spec
  {:dbtype "postgresql"
   :port 5432
   :user "admin"
   :dbname pg-db-name
   :password pg-password})

(def pg-src (jdbc/get-datasource pg-spec))

(def rs-opts (jdbc/with-options pg-src jdbc/unqualified-snake-kebab-opts))

(defmacro with-connection
  [[conn-var] & body]
  (let [sym (gensym)]
    `(let [~sym (jdbc/get-connection pg-src)]
       (with-open [conn# ~sym]
         (def ~conn-var ~sym)
         (try
           ~@body
           (catch org.postgresql.util.PSQLException e#
             {:error (.getMessage e#)})
           (finally
             (.close conn#)))))))
