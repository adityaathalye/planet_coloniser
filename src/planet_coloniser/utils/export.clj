(ns planet-coloniser.utils.export
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]))

(defn write-out-json-file
  [dir-path file-name data]
  (let [file-path (str dir-path file-name)]
    (with-open [writer (io/writer file-path)]
      (json/write data writer))))
