(ns jotes.api.note
  (:require [jotes.db.note]
            [jotes.api.util]))

(defn category?
  [{:keys [id]}]
  (int? id))

(defn create
  [{{:keys [category, txt]} :body-params}]
  (let [db-note (jotes.db.note/insert txt (:id category))]
    {:status 200
     :body (jotes.api.util/remove-map-ns db-note)}))

(def create-spec
  {:parameters {:body {:category category?
                       :txt      string?}}
   :handler create})

(defn get-all
  [_]
  (let [db-notes (jotes.db.note/get-all)]
    {:status 200
     :body (-> (assoc {} :notes db-notes)
               (assoc :total (count db-notes))
               (jotes.api.util/remove-map-ns))}))

(def get-all-spec {:handler get-all})

(defn update!
  [{{:keys [id]} :path-params
    new-data :body-params}]
  (let [updated-count (:next.jdbc/update-count (jotes.db.note/update!
                                                 (Integer/parseInt id)
                                                 new-data))]
    (if (>= updated-count 1)
      {:status 200
       :body {:result (format "updated %d notes" updated-count)}}
      {:status 400})))

(def update!-spec
  {:parameters {:path {:id string?}
                :body {:category category?
                       :txt string?}}
   :handler update!-spec})

(defn delete
  [{{:keys [id]} :path-params}]
  (let [deleted-count (:next.jdbc/update-count (jotes.db.note/delete
                                                 (Integer/parseInt id)))]
    (if (>= deleted-count 1)
      {:status 200
       :body {:result (format "deleted %d notes" deleted-count)}}
      {:status 400})))

(def delete-spec
  {:parameters {:path {:id string?}}
   :handler delete})