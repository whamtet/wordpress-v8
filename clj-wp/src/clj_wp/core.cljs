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
  (js/PHP.foo.__call "log" #js [(.trim s)]))

(defn prn-php [s]
  (prn-php-abstract js/print s))
(defn pprint-php [s]
  (pprint-php-abstract js/print s))
(defn log [s]
  (pprint-php-abstract log-abstract s))

(defn keyword-safe [s]
  (keyword
    (if (and (string? s) (.startsWith s "$"))
      (.substring s 1)
      s)))
(defn v8->clj [m]
  (cond
    (goog.isArray m)
    (for [k (object.getKeys m)]
      (v8->clj (object.get m k)))
    (goog.isObject m)
    (into {}
          (for [k (object.getKeys m)
                :when (not= "v8js" k)] ;prevent circular references
            [(keyword-safe k) (v8->clj (object.get m k))]))
    :else
    m))

(defn invoke [f & args]
  (js/PHP.foo.__call "call" #js [f (clj->js (or args ()))]))

(defn ^:export tt []
  (-> "GET_POSTS"
      invoke
      v8->clj
      log))

(defn ^:export print-globals []
  (log
    (v8->clj js/PHP)))

;(js/exit)
