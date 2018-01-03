(ns planet-coloniser.utils.export
  (:require [clojure.data.json]))


(defn write-out-json-file
  [dir-path file-name data]
  (let [file-path (str dir-path file-name)]
    (with-open [writer (clojure.java.io/writer file-path)]
      (clojure.data.json/write data writer))))
