(ns jotes.core
  (:require [jotes.server]
            [jotes.db])
  (:gen-class))

(defn -main
  [& _]
  (jotes.server/start))
