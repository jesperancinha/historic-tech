include ../Makefile.mk

start:
	ghci
run-demo-profile:
	ghc -v -O -O2 -prof -fprof-auto fibonacci-tail-rec.hs -o fibonacci-recursive-tail-rec
	./fibonacci-recursive-tail-rec +RTS -p -RTS
	ghc -v -O -O2 -prof -fprof-auto fibonacci-recursive.hs -o fibonacci-recursive
	./fibonacci-recursive +RTS -p -RTS
run-demo: run-demo-fast
	ghc fibonacci-recursive.hs -o fibonacci-recursive
	./fibonacci-recursive
run-demo-fast:
	ghc fibonacci-tail-rec.hs -o fibonacci-tail-rec
	./fibonacci-tail-rec
run-dumps: run-intermediate-dump run-assembly-dump
	ghc -O2 -prof -fprof-auto fibonacci.hs
run-intermediate-dump:
	ghc -O2 -ddump-simpl fibonacci.hs
run-intermediate-dump-tco:
	ghc -O2 -ddump-simpl fibonacci-tco.hs
run-assembly-dump:
	ghc -O2 -S fibonacci.hs
run-assembly-dump-tco:
	ghc -O2 -S fibonacci-tco.hs