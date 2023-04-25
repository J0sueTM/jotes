(ns jotes.api.auth
  (:require [jotes.db.user]
            [crypto.password.bcrypt :as bcrypt]
            [buddy.sign.jwt :as jwt])
  (:gen-class))

(def jwt-secret (System/getenv "JOTES_AUTH_SECRET"))

(defn generate-jwt-token
  [email]
  (jwt/sign {:user email} jwt-secret))

(defn signin
  [{{:keys [email, password]} :body-params}]
  (let [db-user (first (jotes.db.user/get-by-email email))
        authorized? (bcrypt/check password (:_user/hash db-user))]
    (if authorized?
      {:status 200
       :body {:token (generate-jwt-token email)}}
      {:status 401
       :body {:status "unauthorized"}})))

(def signin-spec
  {:parameters {:body {:email string?
                       :password string?}}
   :handler signin})

(defn signup
  [{{:keys [email, password]} :body-params}]
  (let [user (jotes.db.user/insert {:email email :password password})]
    {:status 200
     :body user}))

(def signup-spec
  {:parameters {:body {:email string?
                       :password string?}}
   :handler signup})
