#!/usr/bin/bash

echo "Iteration, Regular compiler (s) , K2 compiler (s)" > stats.csv


for i in {0..10}
do

rm -r build
cp build.gradle.original.txt build.gradle.kts
start=`date +%s.%N`
make b
end=`date +%s.%N`
runtimeRegular=$( echo "$end - $start" | bc -l )
echo $runtimeRegular



rm -r build
k2config=$(<k2config.kts)
cp build.gradle.original.txt build.gradle.kts
echo $k2config >> build.gradle.kts
start=`date +%s.%N`
make b
end=`date +%s.%N`
runtimeK2=$( echo "$end - $start" | bc -l )
echo $runtimeK2

echo "$i , $runtimeRegular , $runtimeK2" >> stats.csv

done;