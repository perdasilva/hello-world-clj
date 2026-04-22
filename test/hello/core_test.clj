(ns hello.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [hello.system :as system]
            [hello.component.greeter :as greeter]))

(deftest system-lifecycle-test
  (testing "system starts and stops without errors"
    (let [sys (-> [[:greeter (greeter/create-greeter)]]
                  system/create-system
                  system/start-system)
          stopped (system/stop-system sys)]
      (is (contains? sys :greeter))
      (is (contains? stopped :greeter)))))

(deftest system-greet-default-test
  (testing "full system produces default greeting"
    (let [sys (-> [[:greeter (greeter/create-greeter)]]
                  system/create-system
                  system/start-system)
          result (greeter/greet (:greeter sys))]
      (is (= "Hello, World!" result))
      (system/stop-system sys))))

(deftest system-greet-named-test
  (testing "full system produces named greeting"
    (let [sys (-> [[:greeter (greeter/create-greeter)]]
                  system/create-system
                  system/start-system)
          result (greeter/greet (:greeter sys) "CI")]
      (is (= "Hello, CI!" result))
      (system/stop-system sys))))
