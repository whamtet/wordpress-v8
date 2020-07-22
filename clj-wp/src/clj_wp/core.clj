(ns clj-wp.core)

(defmacro defargs [name args & body]
  `(defn ^:export ~name []
     (let [~args js/PHP.args]
       ~@body)))
