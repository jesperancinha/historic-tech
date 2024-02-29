(defsystem "tailrec-lisp"
  :version "0.0.1"
  :author ""
  :license ""
  :depends-on ()
  :components ((:module "src"
                :components
                ((:file "main"))))
  :description ""
  :in-order-to ((test-op (test-op "tailrec-lisp/tests"))))

(defsystem "tailrec-lisp/tests"
  :author ""
  :license ""
  :depends-on ("tailrec-lisp"
               "rove")
  :components ((:module "tests"
                :components
                ((:file "main"))))
  :description "Test system for tailrec-lisp"
  :perform (test-op (op c) (symbol-call :rove :run c)))
