(require '[cljs.build.api :as b])
(require '[clojure.java.shell :refer [sh]])

(b/watch "src"
  {:main 'clj-wp.main
   :output-to "../wp-content/plugins/wordpress_v8/clj_wp.js"
   :target :webworker
   :optimizations :simple
   :output-dir "out"
   :watch-fn #(slurp "http://localhost:8000")
   :watch-error-fn
   (fn [e]
     ;(->> e ex-data :file .getName (sh "say"))
     (prn e))})
