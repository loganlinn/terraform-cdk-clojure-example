(ns build
  (:refer-clojure :exclude [compile])
  (:require [clojure.tools.build.api :as b]))

(def lib 'dev.llinn/terraform-cdk-clojure-example)
(def version (format "1.2.%s" (b/git-count-revs nil)))
(def jar-file (format "target/%s-%s.jar" (name lib) version))
(def class-dir "target/classes")
(def basis (b/create-basis {:project "deps.edn"}))

(defn clean [_]
  (b/delete {:path class-dir}))

(defn compile [_]
  (b/javac {:src-dirs ["src/main/java"] ;; This is where `cdktf get` places the files
            :class-dir class-dir
            :basis basis
            :javac-opts ["-source" "8" "-target" "8"]}))
