(ns metrics-server.api.files
  (:require [metrics-server.core :refer [call-api check-required-params with-collection-format]])
  (:import (java.io File)))

(defn get-files-with-http-info
  "Get files in directory on server"
  []
  (call-api "/files" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types []
             :accepts       []
             :auth-names    []}))

(defn get-files
  "Get files in directory on server"
  []
  (:data (get-files-with-http-info)))

(defn taskA [files]
  (filter (fn [x] (not (get x :directory))) files)
  )

(defn taskB [files]
  (filter (fn [x] (get x :executable)) files)
  )

(defn taskC [files]
  (map (fn [file] (clojure.string/replace (get file :name) #".conf" ".cfg" )) files)
  )

(defn taskD [files]
  (/
    (reduce + (map (fn [size] (get size :size)) (filter (fn [x] (not (get x :directory))) files)))
    (count (filter (fn [x] (not (get x :directory))) files))
    )
  )

(defn -main [& args]
  (println (taskA (get-files)))
  (println (taskB (get-files)))
  (println (taskC (get-files)))
  (println (taskD (get-files)))
  )