(ns signet.stash
  (:require
    [cljs.nodejs :as nodejs]
    ["bittorrent-dht" :as dht]
    [optimist]))

(nodejs/enable-util-print!)

(defn main [& cli-args]
  (println "signet.stash")
  (when cli-args
    (println (js->clj (.parse optimist (clj->js cli-args))))))

(set! *main-cli-fn* main)
