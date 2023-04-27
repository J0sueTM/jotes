(ns jotes.api.util
  (:require [clojure.walk]))

(defn remove-map-ns [m]
  (clojure.walk/postwalk
    (fn [x] (if (and (keyword? x) (namespace x))
              (keyword (name x)) x))
    m))