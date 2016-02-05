(ns tic-tac-toe.board-test
  (:require-macros [cljs.test :refer [deftest testing is are]])
  (:require [cljs.test :as t]
            [tic-tac-toe.board :as b]))

(def ^:const ^:private board-size 9)

(defn make-board [{:keys [x o]}]
  (let [filled (+ x o)
        empties (- board-size filled)]
    (shuffle
     (concat (repeat x :x)
             (repeat o :o)
             (repeat empties :e)))))

(defn make-win-result [player triplet]
  {:win-player player
   :win-triplet triplet})

(deftest test-board-valid? []
  (testing "Testing board-valid?"
    (testing "/ Happy Path"
      (are [expected board] (= expected (b/board-valid? board))
        true (make-board {:x 4 :o 3})
        true (b/init-board)))
    (testing "/ Bad elements"
      (are [expected board] (= expected (b/board-valid? board))
        false (repeat board-size 42)))
    (testing "/ Bad Count"
      (are [expected board] (= expected (b/board-valid? board))
        false (repeat 6 :o)))
    (testing "/ Bad Ratio"
      (are [expected board] (= expected (b/board-valid? board))
        false (make-board {:x 4 :o 4})
        false (make-board {:x 4 :o 2})
        false (make-board {:x 5 :o 1})))))

(deftest test-board-full? []
  (testing "Testing board-full?"
    (testing "/ Happy Path"
      (are [expected board] (= expected (b/board-full? board))
        false (b/init-board)
        true (make-board {:x 4 :o 5})))))

(deftest test-board-won? []
  (testing "Testing board-won?"
    (testing "/ Happy Path"
      (are [expected board] (= expected (b/board-won? board))
        nil (b/init-board)
        (make-win-result :x #{0 1 2}) (concat (repeat 3 :x) (repeat 6 :e))
        (make-win-result :o #{6 7 8}) (concat (repeat 6 :e) (repeat 3 :o))
        (make-win-result :x #{0 4 8}) [:x :e :e
                                       :e :x :e
                                       :e :e :x]
        (make-win-result :x #{2 4 6}) [:e :e :x
                                       :e :x :e
                                       :x :e :e]
        (make-win-result :o #{3 4 5}) [:e :e :x
                                       :o :o :o
                                       :x :e :e]
        nil [:o :o :x
             :x :o :o
             :o :x :x]))))
