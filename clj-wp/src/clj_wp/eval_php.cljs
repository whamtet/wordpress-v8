(ns clj-wp.eval-php
  (:require
    [clj-wp.core :as core])
  (:import
    goog.string.format))

(def format goog.string.format)

(defn eval-php [s]
  (js/PHP.foo.__call "ev" #js [s]))

(defn v8-assign [func-name]
  (eval-php
    (format
      "function %s() {
      return v8_args('%s', func_get_args());
      }" func-name func-name)))
