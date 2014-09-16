(defproject democlojure "1.0.0"
  :description "A Clojure console application for creating and
                storing ringtones."
  :url "http://github.com/28/democlojure.git"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"
            :distribution :repo
            :comment "Same as Clojure"}
  :repl-options {
                 :prompt (fn [ns] (str "democlojure-" ns "=>"))
                 :welcome (println "Welcome to democlojure")
                 :init-ns console.console
                 :init (println "Here is the list of the functions to use: log-in-console, sign-console,
                              play-console, crop-console, log-out-console")}
  :dependencies [[org.clojure/clojure "1.5.1"]])
