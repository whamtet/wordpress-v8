(ns clj-wp.core)

(def to-export (atom #{}))
(defmacro defphp [name args & body]
  (swap! to-export conj (str name))
  `(set!
     ~(symbol "js" (str name))
     (fn []
       (let [~args (clj-wp.core/v8->clj js/PHP.args)]
         ~@body))))

(defmacro definvoke [sym]
  `(def ~sym (fn [& args#] (apply clj-wp.core/invoke ~(-> sym str (.replace "-" "_")) args#))))
(defmacro definvokes [& syms]
  `(do
     ~@(for [sym syms]
         `(definvoke ~sym))))

(def to-eval (atom {}))
(defn- f-str->name [s]
  (as-> s $
        (re-find #"function (\w+)" $)
        (second $)
        (.replace $ "_" "-")
        (symbol $)))

(defmacro defun [s]
  (let [f-name (f-str->name s)]
    (swap! to-eval assoc f-name s)
    `(definvoke ~f-name)))

(defmacro defexport [symbol]
  `(defn ^:export ~symbol []
     (dorun
       (map clj-wp.eval-php/v8-assign ~(deref to-export)))
     (dorun
       (map clj-wp.eval-php/eval-php ~(vec (vals @to-eval))))))
