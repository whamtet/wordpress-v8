(ns clj-wp.core)

(defmacro defargs [name args & body]
  `(defn ^:export ~name []
     (let [~args (clj-wp.core/v8->clj js/PHP.args)]
       ~@body)))
