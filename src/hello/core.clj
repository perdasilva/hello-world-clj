(ns hello.core
  (:require [hello.system :as system]
            [hello.component.greeter :as greeter]))

(defn -main [& args]
  (let [sys (-> [[:greeter (greeter/create-greeter)]]
                system/create-system
                system/start-system)
        greeting (if (first args)
                   (greeter/greet (:greeter sys) (first args))
                   (greeter/greet (:greeter sys)))]
    (println greeting)
    (system/stop-system sys)))
