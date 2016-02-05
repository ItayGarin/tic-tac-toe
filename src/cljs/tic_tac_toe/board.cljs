(ns tic-tac-toe.board)

(def ^:const ^:private players-set #{:x :o})
(def ^:const ^:private legal-keys (conj players-set :e))

(def ^:const ^:private board-size 9)
(def ^:const ^:private row-size 3)
(def ^:const ^:private col-size 3)

;;;;;;;;;;;;;;;;; PRIVATE ;;;;;;;;;;;;;;;;;;;

(defn- random-board! []
  (repeatedly board-size #(rand-nth (vec legal-keys))))

(defn- get-rows []
  (let [indexes (range board-size)]
    (partition row-size indexes)))

(defn- get-cols []
  (let [rows (get-rows)]
    (apply map vector rows)))

(defn- get-diagonals []
  (let [rows (get-rows)]
    [(map nth rows (range row-size))
     (map nth (reverse rows) (range row-size))]))

(defn- same-triplet? [board triplet]
  (let [vals (map #(nth board %) triplet)]
    (or (every? #{:x} vals)
        (every? #{:o} vals))))

(defn- board-empty? [board]
  (let [{:keys [e]} (frequencies board)]
    (= e board-size)))

(defn- board-valid-ratio? [board]
  (let [{:keys [x o]} (frequencies board)
        diff (Math/abs (- x o))]
    (if (board-empty? board)
      true
      (= diff 1))))

;;;;;;;;;;;;;;;;; PUBLIC ;;;;;;;;;;;;;;;;;;;

(defn init-board
  "Returns a board with empty (:e keyword) cells"
  []
  (into []
        (repeat board-size :e)))

(defn player-valid? [player]
  "A predicate that checks if a given value matches a player's key"
  (boolean
   (players-set player)))

(defn board-valid?
  "A predicate that checks the board cells"
  [board]
  (boolean
   (and (every? legal-keys board)
        (= (count board) board-size)
        (board-valid-ratio? board))))

(defn board-full?
  "A predicate that checks whether the are any empty cells"
  [board]
  (boolean (every? players-set board)))

(defn board-won?
  "If there's a winner, returns a map with the :win-player and the :win-triplet"
  [board]
  (let [rows (get-rows)
        cols (get-cols)
        diags (get-diagonals)
        all-triplets (concat rows cols diags)]
    (when-let [winning-triplet (first
                                (filter #(same-triplet? board %) all-triplets))]
      {:win-player (nth board (first winning-triplet))
       :win-triplet (set winning-triplet)})))

(defn fill-cell
  "Fills a cell with the key of the given player"
  [board index player-key]
  (assoc board index player-key))
