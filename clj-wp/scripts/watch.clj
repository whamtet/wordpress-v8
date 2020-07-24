(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'clj-wp.main
   :output-to "../wordpress/clj_wp.js"
   :target :webworker
   :optimizations :simple
   :output-dir "out"})
