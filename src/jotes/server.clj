(ns jotes.server
  (:require [ring.adapter.jetty :as ring-jetty]
            [reitit.ring :as rtt-ring]
            [reitit.ring.coercion :as rtt-coercion]
            [reitit.ring.middleware.muuntaja :as rtt-mtj]
            [reitit.ring.middleware.parameters :as rtt-params]
            [muuntaja.core :as mtj])
  (:gen-class))

(def port
  (-> (System/getenv "JOTES_PORT")
      (or "8080")
      (Integer/parseInt)))

(def routes [["/" (fn [_] {:status 200 :body "pong!"})]])

(def router-spec
  {:data {:muuntaja mtj/instance
          :middleware [rtt-params/parameters-middleware
                       rtt-mtj/format-negotiate-middleware
                       rtt-mtj/format-request-middleware
                       rtt-mtj/format-response-middleware
                       rtt-coercion/coerce-request-middleware
                       rtt-coercion/coerce-response-middleware]}})

(def router
  (-> (rtt-ring/router routes router-spec)
      (rtt-ring/ring-handler (rtt-ring/create-default-handler))))

(defn start []
  (ring-jetty/run-jetty #'router {:port port :join? false}))
