(ns planet-coloniser.sensor-processor
  (:require [clojure.walk :as cw]))

(defn sensor-data->planet
  "Given a sensor key, a planetary hash-map, and a tuple having
  sensor data for a planet, inject the sensor data under the
  planet name found in the planetary hash-map."
  [sensor-key planets [pname sensor-pdata]]
  (assoc-in planets            ; given these planets as a hash-map
            [pname sensor-key] ; follow this nested path
            sensor-pdata))     ; and assoc this value under that path
;; Note:
;; Just like `assoc` (into map) is the partner of `get` (from map),
;; `assoc-in` (nested map) is the partner of `get-in` (from nested map).


(defn moons->planets
  "Inject moons sensor data, into planets sensor data."
  [moons planets]
  (reduce (partial sensor-data->planet :moons)
          planets
          moons))


(defn atmosphere->planets
  "Inject atmosphere sensor data, into planets sensor data."
  [atmosphere planets]
  (reduce (partial sensor-data->planet :atmosphere)
          planets
          atmosphere))


(defn denormalise-planetary-data
  "Given a hash-map of planetary data (keyed by planet names),
  return just the planetary data, with the planet's names added in.

  Also ensure all keys are keywordized, for convenient look-ups."
  [planets]
  (map (fn [[pname pdata]]
         (let [keywordized-pdata (cw/keywordize-keys
                                  pdata)]
           (assoc keywordized-pdata
                  :name pname)))
       planets))


(defn denormalized-planetary-data
  "Given all sensor data, produce a collection of denormalized
  planetary data."
  [{:keys [planets atmosphere moons]
    :as all-sensor-data}]
  ((comp denormalise-planetary-data
         (partial atmosphere->planets atmosphere)
         (partial moons->planets moons))
   planets))
