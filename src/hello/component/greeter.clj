(ns hello.component.greeter
  (:require [hello.system :as system]))

(defrecord Greeter []
  system/Lifecycle
  (start [this] this)
  (stop [this] this))

(defn greet
  ([_component]
   "Hello, World!")
  ([_component a-name]
   (str "Hello, " a-name "!")))

(defn create-greeter []
  (->Greeter))
