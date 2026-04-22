(ns hello.component.greeter-test
  (:require [clojure.string :as str]
            [clojure.test :refer [deftest testing is]]
            [clojure.test.check.clojure-test :refer [defspec]]
            [clojure.test.check.generators :as gen]
            [clojure.test.check.properties :as prop]
            [hello.component.greeter :as greeter]))

(deftest greet-default-test
  (testing "returns default greeting with no name"
    (is (= "Hello, World!" (greeter/greet (greeter/create-greeter))))))

(deftest greet-named-test
  (testing "returns personalized greeting with a name"
    (is (= "Hello, Clojure!" (greeter/greet (greeter/create-greeter) "Clojure")))))

(deftest greet-empty-string-test
  (testing "handles empty string"
    (is (= "Hello, !" (greeter/greet (greeter/create-greeter) "")))))

(defspec greet-format-property 100
  (prop/for-all [s gen/string-alphanumeric]
    (= (str "Hello, " s "!")
       (greeter/greet (greeter/create-greeter) s))))

(defspec greet-prefix-suffix-property 100
  (prop/for-all [s gen/string]
    (let [result (greeter/greet (greeter/create-greeter) s)]
      (and (str/starts-with? result "Hello, ")
           (str/ends-with? result "!")))))
