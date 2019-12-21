(ns metrics-server.api.hardware
  (:require [metrics-server.core :refer [call-api check-required-params with-collection-format]])
  (:import (java.io File))
  (:import (clojure.core)))


(defn get-metrics-with-http-info
  "Get hardware metrics"
  []
  (call-api "/hardware" :get
            {:path-params   {}
             :header-params {}
             :query-params  {}
             :form-params   {}
             :content-types []
             :accepts       []
             :auth-names    []}))

(defn get-metrics
  "Get hardware metrics"
  []
  (:data (get-metrics-with-http-info)))

(defn taskA [metrics]
  (filter (fn [x] (> (get x :cpuTemp) 2)) metrics)
  )

(defn taskB [metrics]
  (/
    (reduce + (map (fn [metrics] (get metrics :cpuTemp) ) metrics) )
    (count metrics)
    )
  )

(defn taskC [metrics]
  (/
    (reduce + (map (fn [metrics] (get metrics :cpuLoad) ) metrics) )
    (count metrics)
    )
  )

(defn -main [& args]
  (println (taskA (get-metrics)))
  (println (taskB (get-metrics)))
  (println (taskC (get-metrics)))
  )