(ns jotes.server
  (:require [ring.adapter.jetty :as ring-jetty]
            [reitit.ring :as rtt-ring]
            [reitit.ring.coercion :as rtt-coercion]
            [reitit.coercion.spec :as rtt-cspec]
            [reitit.ring.middleware.muuntaja :as rtt-mtj]
            [reitit.ring.middleware.parameters :as rtt-params]
            [muuntaja.core :as mtj]
            [jotes.api.auth]
            [buddy.auth.backends :as buddy-backends]
            [buddy.auth.middleware :as buddy-mddwr])
  (:gen-class))

(def port
  (-> (System/getenv "JOTES_PORT")
      (or "8080")
      (Integer/parseInt)))

(def routes
  [["/auth/signin" {:post jotes.api.auth/signin-spec}]
   ["/auth/signup" {:post jotes.api.auth/signup-spec}]])

(def router-spec
  {:data {:muuntaja mtj/instance
          :coercion rtt-cspec/coercion
          :middleware [rtt-mtj/format-middleware
                       rtt-params/parameters-middleware
                       rtt-coercion/coerce-exceptions-middleware
                       rtt-coercion/coerce-request-middleware
                       rtt-coercion/coerce-response-middleware]}})

(def jwt-backend (buddy-backends/jws {:secret jotes.api.auth/jwt-secret}))

(def router
  (-> (rtt-ring/router routes router-spec)
      (rtt-ring/ring-handler (rtt-ring/create-default-handler))
      (buddy-mddwr/wrap-authentication jwt-backend)))

(defn start []
  (ring-jetty/run-jetty #'router {:port port :join? false}))
