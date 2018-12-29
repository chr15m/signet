(require 'lumo.build.api
         'optimist)

(let [script (-> optimist/argv (aget "_") (last) (.replace ".js" ""))]
  (lumo.build.api/build
    "src"
    {:main (str "signet." script)
     :output-to (str script ".js")
     :target :nodejs
     :optimizations :advanced
     :verbose true}))

