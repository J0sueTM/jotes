(ns jotes.db
  (:require [next.jdbc :as jdbc])
  (:gen-class))

;; (when (= (System/getenv "JOTES_ENV") "dev")
;;   (let [logger (logging.Logger/getLogger "org.postgresql")
;;         console-handler (logging.ConsoleHandler.)]
;;     (.setLevel logger logging.Logger/FINE)
;;     (.setFormatter console-handler (logging.SimpleFormatter.))
;;     (.addHandler logger console-handler)))

(def ^:private pg-db-name
  (-> (System/getenv "JOTES_DB_NAME")
      (or "jotesdb")))

(def ^:private pg-password (System/getenv "JOTES_DB_PASSWORD"))

(def pg-spec {:dbtype "postgresql"
           :port 5432
           :user "admin"
           :dbname pg-db-name
           :password pg-password})

(def pg-src (jdbc/get-datasource pg-spec))

(def rs-opts (jdbc/with-options pg-src jdbc/unqualified-snake-kebab-opts))
