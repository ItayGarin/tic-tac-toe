(ns tic-tac-toe.handlers
  (:require [tic-tac-toe.db :as db]
            [re-frame.core :refer [debug register-handler]]
            [tic-tac-toe.board :as b]))

(register-handler
 :init-db
 (fn [_ _]
   db/default-value))

(register-handler
 :fill-cell
 (fn [db [_ i]]
   (let [{:keys [board] :as next} (db/fill-and-toggle db i)
         full? (b/board-full? board)
         {:keys [win-player win-triplet] :as won?} (b/board-won? board)]
     (if won?
       (-> next
           (assoc :state (db/player->won-state win-player))
           (assoc :highlight win-triplet))
       (if full?
         (assoc next :state :full)
         next)))))

(register-handler
 :set-player
 (fn [db [_ player]]
   (assoc db :state player)))
