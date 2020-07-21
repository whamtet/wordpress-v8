(ns clj-wp.core
  (:import
    goog
    [goog object])
  (:require
    [clojure.pprint :refer [pprint]]))

(defn- prn-php-abstract [print-f o]
  (-> o pr-str print-f))
(defn- pprint-php-abstract [print-f o]
  (-> o pprint with-out-str print-f))
(defn- log-abstract [s]
  (js/PHP.foo.__call "log" #js [s]))

(defn prn-php [s]
  (prn-php-abstract js/print s))
(defn pprint-php [s]
  (pprint-php-abstract js/print s))
(defn log [s]
  (pprint-php-abstract log-abstract s))

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
  (log "fuck you"))

(defn ^:export print-globals []
  (log
    (v8->clj js/PHP)))

;(js/exit)
