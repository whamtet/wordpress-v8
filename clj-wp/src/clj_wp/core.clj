(ns clj-wp.core)

(def to-export (atom #{}))
(defmacro defphp [name args & body]
  (swap! to-export conj (str name))
  `(set!
     ~(symbol "js" (str name))
     (fn []
       (let [~args (clj-wp.core/v8->clj js/PHP.args)]
         ~@body))))

(defmacro defexport [symbol]
  `(defn ^:export ~symbol []
     (dorun
       (map clj-wp.core/v8-assign ~(deref to-export)))))

(defmacro definvoke [sym]
  `(def ~sym (fn [& args] (apply clj-wp.core/invoke ~(str sym) args))))
(defmacro definvokes [& syms]
  `(do
     ~@(for [sym syms]
         `(definvoke ~sym))))
