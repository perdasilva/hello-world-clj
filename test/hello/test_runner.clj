(ns hello.test-runner
  (:require [clojure.test :as test]
            [hello.component.greeter-test]
            [hello.core-test]))

(defn -main [& _args]
  (let [{:keys [fail error]} (test/run-tests 'hello.component.greeter-test
                                              'hello.core-test)]
    (when (pos? (+ fail error))
      (System/exit 1))))
