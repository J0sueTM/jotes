(ns jotes.core
  (:require [jotes.server]
            [jotes.db])
  (:gen-class))

(defn -main
  [& _]
  (jotes.db/start)
  (jotes.server/start))
