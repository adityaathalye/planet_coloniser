# planet_coloniser

"Librarified" some code from clojure by example.

The goal is to use this to create step-by-step instructions to help people
start off their very first clj project.

Ref: Code from ex06 of https://github.com/inclojure-org/clojure-by-example

## Usage

At the command line, cd into the root of `planet_coloniser` and:

    $ lein do clean, deps, uberjar

    $ java -jar target/planet-coloniser-0.1.0-SNAPSHOT-standalone.jar "resources/sensor_data/" "sensor_data_files.json" "consolidated_data.json"

Also try with lein run:

    $ lein run "resources/sensor_data/" "sensor_data_files.json" "consolidated_data_2.json"

## License

Copyright © 2017 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
