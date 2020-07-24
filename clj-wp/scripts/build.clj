(require '[cljs.build.api :as b])

(println "Building ...")

(let [start (System/nanoTime)]
  (b/build "src"
           {:main 'clj-wp.main
            :output-to "../wordpress_v8/clj_wp.js"
            :target :webworker
            :optimizations :simple
            :output-dir "out"})
  (println "... done. Elapsed" (/ (- (System/nanoTime) start) 1e9) "seconds"))


