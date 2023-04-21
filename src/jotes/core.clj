(ns jotes.core
  (:require [jotes.server])
  (:gen-class))

(defn -main
  [& _]
  (jotes.server/start))
