(ns signet.formats
  (:require 
    [bs58]
    [bs58check]
    [tweetnacl :as nacl]))

(def ADDRESSPREFIX (js/Buffer.from "492101" "hex"))

(def ALPHABET "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz")

(defn encode-pubkey [pubkey]
  (bs58check/encode (js/Buffer.concat #js [ADDRESSPREFIX pubkey])))

(defn decode-pubkey [pubkey-bs58]
  (let [decoded (bs58check/decode pubkey-bs58)]
    (when (= (.compare (.slice decoded 0 3) ADDRESSPREFIX) 0)
      (.slice decoded 3))))

(def pubkey-regex (js/RegExp. (str "pkN[" ALPHABET "]{50}")))

; code to find the right prefix
#_ (doseq [i (range 16777215)]
    (let [h (.join (.reverse (.split (.slice (str "000000" (.toString i "16")) -6) "")) "")
          prefix (js/Buffer.from h "hex")
          v (bs58check/encode (js/Buffer.concat #js [prefix (nacl/randomBytes 32)]))]
      ;(println (.toString prefix "hex"))
      (when
        (= (.indexOf v "pkN") 0)
        (println (.toString prefix "hex") v))))

;(println (encode-pubkey (js/Buffer. (to-array (for [i (range 32)] 0x00)))))
;(println (encode-pubkey (js/Buffer. (to-array (for [i (range 32)] 0xff)))))
;(println (encode-pubkey (.-publicKey (nacl/sign.keyPair))))
