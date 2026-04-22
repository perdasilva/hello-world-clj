(ns hello.system)

(defprotocol Lifecycle
  (start [component])
  (stop [component]))

(defn create-system [component-pairs]
  (apply array-map (mapcat identity component-pairs)))

(defn start-system [system]
  (reduce-kv
   (fn [acc k component]
     (assoc acc k (start component)))
   (array-map)
   system))

(defn stop-system [system]
  (reduce
   (fn [acc [k component]]
     (assoc acc k (stop component)))
   (array-map)
   (reverse system)))
