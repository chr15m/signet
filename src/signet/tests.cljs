(ns signet.tests
  (:require
    [tape]
    [signet.formats :as formats]))

(def test-vector-hex "0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF0123456789ABCDEF")
(def test-vector-b58 "pkNARwXmFh2KgiDF38ChWbiJ5XVf7usW67SFhvjDHVXvz98LbhC4W")

(tape "formats"
      (fn [t]
        (.plan t 4)
        (let [; turn into bs58check encoded pubkey
              k (formats/encode-pubkey (js/Buffer.from test-vector-hex "hex"))
              ; decode from bs58check encoded
              r (.toString (formats/decode-pubkey k) "hex")
              ; extract regex
              e (re-find formats/pubkey-regex (str "blah blah " k " hello"))]
          (.equal t k test-vector-b58 "bs58 encoding match")
          (.equal t (.indexOf k "pkN") 0 "prefix check")
          (.equal t r (.toLowerCase test-vector-hex))
          (.equal t e test-vector-b58 "regex extraction"))))

