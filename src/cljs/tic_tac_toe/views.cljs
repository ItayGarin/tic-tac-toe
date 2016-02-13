(ns tic-tac-toe.views
  (:require [reagent.core :as r :refer []]
            [re-frame.core :refer [subscribe dispatch]]
            [clojure.string :as s]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; FOOTER COMPONENT ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn player-letter
  "Player key (:x/:o) to capitalized letter ('X'/'O')"
  [player]
  (-> player
      (name)
      (s/upper-case)))

(defn message [msg]
  [:p.message msg])

(defn button [msg on-click-fn]
  [:button {:type "button"
            :class "btn btn-default btn-lg"
            :on-click on-click-fn}
   msg])

(defn player-button [player]
  [button
   (str "Player " (player-letter player))
   #(dispatch [:set-player player])])

(defn player-selector [game]
  [:div
   [message "Pick the First Player"]
   [player-button :x]
   [player-button :o]])

(defn turn-message [player]
  [message
   (str "It is " (player-letter player) "'s Turn to Play!")])

(defn replay-button []
  [button "Replay" #(dispatch [:init-db])])

(defn replay [msg]
  [:div
   [message msg]
   [replay-button]])

(defn won [player]
  [replay (str "Player " (player-letter player) " Won!")])

(defn stalemate [player]
  [replay "Stalemate"])

(defn footer []
  (let [state (subscribe [:state])]
    (fn []
      (case @state
        :pick [player-selector]
        :x [turn-message :x]
        :o [turn-message :o]
        :x-won [won :x]
        :o-won [won :o]
        :full [stalemate]))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; BOARD COMPONENT ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn empty-cell [i]
  (let [state (subscribe [:state])]
    (fn []
      (let [hyper-cell [:td
                        [:a
                         {:style {:cursor "pointer"
                                  :text-decoration "none"}
                          :on-click #(dispatch [:fill-cell i])}
                         "-"]]]
        (case @state
          :x hyper-cell
          :o hyper-cell
          [:td "-"])))))

(defn full-cell [value highlight?]
  (let [style (when highlight? {:style {:color "red"}})]
    [:td style (name value)]))

(defn cell [{:keys [index value highlight?]}]
  (case value
    :e [empty-cell index]
    :x [full-cell value highlight?]
    :o [full-cell value highlight?]))

(defn row [row-model]
  [:tr
   (for [cell-model row-model]
     ^{:key cell-model} [cell cell-model])])

(defn board []
  (let [board-model (subscribe [:board-model])]
    (fn []
      (let [{:keys [board highlight]} @board-model
            indexes (range (count board))
            cell-models (map #(hash-map :index %1
                                        :value %2
                                        :highlight? (some #{%1} highlight))
                             indexes board)
            row-models (partition 3 cell-models)]
        [:table
         [:tbody
          (for [row-model row-models]
            ^{:key row-model} [row row-model])]]))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; HEADER COMPONENT ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn header []
  [:h1.header "Tic Tac Toe"])

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;; GAME COMPONENT ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn game []
  [:div.tic-tac-toe
   [header]
   [board]
   [footer]])
