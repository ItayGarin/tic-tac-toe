(ns tic-tac-toe.app
  (:require [tic-tac-toe.views :as v]
            [reagent.core :as r]
            [re-frame.core :refer [dispatch-sync]]))

(enable-console-print!)

(defn mount-root []
  (r/render [v/game]
            (.getElementById js/document "tic-tac-toe")))

(defn ^:export init []
  (dispatch-sync [:init-db])
  (mount-root))
