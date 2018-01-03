(ns planet-coloniser.core
  (:gen-class) ; add this, and the :require expression below:
  (:require [planet-coloniser.sensor-processor :as sensproc]
            [planet-coloniser.utils.ingest :as ingest]
            [planet-coloniser.utils.export :as export]
            [clojure.walk :as cwalk]))


(defn -main
  [data-dir source-data-files dest-data-file]
  (let [source-data-files
        (cwalk/keywordize-keys
         (ingest/ingest-json-file data-dir
                                  source-data-files))]
    (export/write-out-json-file
     data-dir
     dest-data-file
     (sensproc/denormalized-planetary-data
      (ingest/gather-all-sensor-data! data-dir source-data-files)))))
