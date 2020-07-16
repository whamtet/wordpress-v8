(ns clj-wp.core
  (:import
    goog
    [goog object])
  (:require
    [clojure.pprint :refer [pprint]]))

(defn prn-php [s]
  (js/print (pr-str s)))
(defn pprint-php [s]
  (-> s pprint with-out-str js/print))

(defn v8->clj [m]
  (if (goog.isObject m)
    (into {}
          (for [k (object.getKeys m)]
            [(keyword k) (v8->clj (object.get m k))]))
    m))

(pprint-php
  (v8->clj js/PHP))
(js/exit)
