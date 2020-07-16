(ns clj-wp.core
  (:import
    goog
    [goog object]))

(defn prn-php [s]
  (js/print (pr-str s)))

(defn v8->clj [m]
  (if (goog.isObject m)
    (into {}
          (for [k (object.getKeys m)]
            [(keyword k) (v8->clj (object.get m k))]))
    m))

(prn-php
  (v8->clj js/PHP))
(js/exit)
