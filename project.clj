(defproject jotes "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [ring "1.10.0"]
                 [metosin/reitit "0.6.0"]
                 [metosin/muuntaja "0.6.8"]
                 [com.github.seancorfield/next.jdbc "1.3.874"]
                 [org.postgresql/postgresql "42.3.3"]]
  :main jotes.core
  :aot [jotes.core]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
