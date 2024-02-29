(defpackage tailrec-lisp-tco/tests/main
  (:use :cl
        :tailrec-lisp-tco
        :rove))
(in-package :tailrec-lisp-tco/tests/main)

;; NOTE: To run this test file, execute `(asdf:test-system :tailrec-lisp-tco)' in your Lisp.

(deftest test-target-1
  (testing "should (= 1 1) to be true"
    (ok (= 1 1))))
