include ../Makefile.mk

start:
	ghci
run-demo-profile:
	ghc -v -O -O2 -prof -fprof-auto factorial-recursive.hs -o factorial-recursive
	./factorial-recursive +RTS -p -RTS
	ghc -v -O -O2  -prof -fprof-auto factorial-recursive-tco.hs -o factorial-recursive-tco
	./factorial-recursive-tco +RTS -p -RTS
	ghc -v -O -O2  -prof -fprof-auto factorial-non-tail-recursive -o factorial-non-recursive
	./factorial-non-recursive +RTS -p -RTS
run-demo:
	ghc factorial-recursive.hs -o factorial-recursive
	./factorial-recursive
	ghc factorial-recursive-tco.hs -o factorial-recursive-tco
	./factorial-recursive-tco
	ghc factorial-non-tail-recursive -o factorial-non-recursive
	./factorial-non-recursive
run-dumps: run-intermediate-dump run-assembly-dump
	ghc -O2 -prof -fprof-auto factorial-recursive.hs
run-intermediate-dump:
	ghc -O2 -ddump-simpl factorial-recursive.hs
run-intermediate-dump-tco:
	ghc -O2 -ddump-simpl factorial-recursive-tco.hs
run-assembly-dump:
	ghc -O2 -S factorial-recursive.hs
run-assembly-dump-tco:
	ghc -O2 -S factorial-recursive-tco.hs