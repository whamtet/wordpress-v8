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
          (for [k (object.getKeys m)
                :when (not= "v8js" k)] ;prevent circular references
            [(keyword k) (v8->clj (object.get m k))]))
    m))

(defn invoke [f & args]
  (js/PHP.foo.__call "call" #js [f (clj->js args)]))

(defn ^:export tt []
  (invoke "barber" "hello"))

(defn ^:export print-globals []
  (pprint-php
    (v8->clj js/PHP))
  (js/exit))


;(js/exit)
