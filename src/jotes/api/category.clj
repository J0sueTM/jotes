(ns jotes.api.category
  (:require [jotes.db.category]
            [jotes.api.util]))

(defn create
  [{{:keys [txt, color]} :body-params}]
  (let [db-category (jotes.db.category/insert txt color)]
    {:status 200
     :body   (jotes.api.util/remove-map-ns db-category)}))

(def create-spec
  {:parameters {:body {:txt   string?
                       :color string?}}
   :handler    create})

(defn get-all
  [_]
  (let [db-categories (jotes.db.category/get-all)]
    {:status 200
     :body (-> (assoc {} :categories db-categories)
               (assoc :total (count db-categories))
               (jotes.api.util/remove-map-ns))}))

(def get-all-spec {:handler get-all})

(defn get-by-txt
  [{{:keys [txt]} :path-params}]
  (let [db-categories (jotes.db.category/get-by-txt txt)]
    {:status 200
     :body   (-> (assoc {} :categories db-categories)
                 (assoc :total (count db-categories))
                 (jotes.api.util/remove-map-ns))}))

(def get-by-txt-spec
  {:parameters {:path {:txt string?}}
   :handler    get-by-txt})

(defn update!
  [{{:keys [txt]} :path-params
    new-data      :body-params}]
  (let [updated-count (:next.jdbc/update-count (jotes.db.category/update! txt new-data))]
    (if (>= updated-count 1)
      {:status 200
       :body {:result (format "updated %d categories" updated-count)}}
      {:status 400})))

(def update!-spec
  {:parameters {:path {:txt string?}
                :body {:txt string?
                       :color string?}}
   :handler    update!})

(defn delete
  [{{:keys [txt]} :path-params}]
  (let [deleted-count (:next.jdbc/update-count (jotes.db.category/delete txt))]
    (if (>= deleted-count 1)
      {:status 200
       :body {:result (format "deleted %d categories" deleted-count)}}
      {:status 400})))

(def delete-spec
  {:parameters {:path {:txt string?}}
   :handler    delete})