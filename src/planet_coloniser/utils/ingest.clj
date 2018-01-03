(ns planet-coloniser.utils.ingest
  (:require [clojure.data.json]))


(defn ingest-json-file
  [dir-path file-name]
  (let [file-path (str dir-path file-name)]
    (with-open [reader (clojure.java.io/reader file-path)]
      (clojure.data.json/read reader))))


(defn gather-all-sensor-data!
  "Use our global collection of sensor keys to look up all the
  sensor data files, and ingest them at one go. Return a hash-map
  containing each sensor's data mapped to the corresponding sensor key."
  [data-dir sensor-files-map]
  (let [ingest-sensor-data
        (fn [out-map [sensor-key sensor-file]]
          (assoc out-map
                 sensor-key (ingest-json-file data-dir
                                              sensor-file)))]
    (reduce ingest-sensor-data
            {} ; out-map starts empty
            sensor-files-map)))
