(ns tic-tac-toe.db
  (:require [tic-tac-toe.board :as b]))

(def ^:const default-value
  {:board (b/init-board)
   :state :pick
   :highlight #{}})

(defn player->won-state [player]
  (case player
    :x :x-won
    :o :o-won))

(defn toggle-turn-state [{:keys [state] :as db}]
  (let [next (case state
               :x :o
               :o :x)]
    (assoc db :state next)))

(defn fill-and-toggle [db i]
  (let [player (:state db)]
    (-> db
        (update :board #(b/fill-cell (:board db) i player))
        (toggle-turn-state))))
