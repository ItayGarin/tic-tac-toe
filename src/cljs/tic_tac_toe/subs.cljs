(ns tic-tac-toe.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :refer [register-sub]]))

(register-sub
 :board-model
 (fn [db _]
   (reaction (select-keys @db [:board :highlight]))))

(register-sub
 :state
 (fn [db _]
   (reaction (:state @db))))
