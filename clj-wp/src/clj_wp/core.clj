(ns clj-wp.core)

(defmacro defphp [name args & body]
  ;; we'll attach everything to global js ns
  `(do
     (set!
       ~(symbol "js" (str name))
       (fn []
         (let [~args (clj-wp.core/v8->clj js/PHP.args)]
           ~@body)))
     (clj-wp.core/v8-assign ~(str name))))
