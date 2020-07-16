(require '[cljs.build.api :as b])

(b/watch "src"
  {:main 'clj-wp.core
   :output-to "../wordpress/clj_wp.js"
   :target :nodejs
   :optimizations :simple
   :output-dir "out"})
