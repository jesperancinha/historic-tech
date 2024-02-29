install-all:
	curl --proto '=https' --tlsv1.2 -sSf https://get-ghcup.haskell.org | sh
start:
	ghci
run-demo:
	ghc factorial-recursive.hs -o factorial-recursive
	./factorial-recursive
run-dumps:
	ghc -O2 -ddump-simpl factorial-recursive.hs
	ghc -O2 -S factorial-recursive.hs
	ghc -O2 -prof -fprof-auto factorial-recursive.hs
