(ns jotes.server
  (:require [ring.adapter.jetty :as ring-jetty]
            [reitit.ring :as rtt-ring]
            [reitit.ring.coercion :as rtt-coercion]
            [reitit.coercion.spec :as rtt-cspec]
            [reitit.ring.middleware.muuntaja :as rtt-mtj]
            [reitit.ring.middleware.parameters :as rtt-params]
            [muuntaja.core :as mtj]
            [jotes.api.category]))

(def port
  (-> (System/getenv "JOTES_PORT")
      (or "8080")
      (Integer/parseInt)))

(def routes
  [["/categories/:txt" {:get    jotes.api.category/get-by-txt-spec
                        :put    jotes.api.category/update!-spec
                        :delete jotes.api.category/delete-spec}]
   ["/categories" {:get  jotes.api.category/get-all-spec
                   :post jotes.api.category/create-spec}]])

(def router-spec
  {:data {:muuntaja mtj/instance
          :coercion rtt-cspec/coercion
          :middleware [rtt-mtj/format-middleware
                       rtt-params/parameters-middleware
                       rtt-coercion/coerce-exceptions-middleware
                       rtt-coercion/coerce-request-middleware
                       rtt-coercion/coerce-response-middleware]}})

(def router
  (-> (rtt-ring/router routes router-spec)
      (rtt-ring/ring-handler (rtt-ring/create-default-handler))))

(defn start []
  (ring-jetty/run-jetty #'router {:port port :join? false}))