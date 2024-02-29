(defsystem "tailrec-lisp-tco"
  :version "0.0.1"
  :author ""
  :license ""
  :depends-on ()
  :components ((:module "src"
                :components
                ((:file "main"))))
  :description ""
  :in-order-to ((test-op (test-op "tailrec-lisp-tco/tests"))))

(defsystem "tailrec-lisp-tco/tests"
  :author ""
  :license ""
  :depends-on ("tailrec-lisp-tco"
               "rove")
  :components ((:module "tests"
                :components
                ((:file "main"))))
  :description "Test system for tailrec-lisp-tco"
  :perform (test-op (op c) (symbol-call :rove :run c)))
