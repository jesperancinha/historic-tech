#!/usr/bin/bash

rm -r build
cp build.gradle.original.txt build.gradle.kts
start=`date +%s.%N`
make b
end=`date +%s.%N`
runtimek2=$( echo "$end - $start" | bc -l )
echo $runtimek2


read love


rm -r build
k2config=$(<k2config.kts)
cp build.gradle.original.txt build.gradle.kts
echo $k2config >> build.gradle.kts
start=`date +%s.%N`
make b
end=`date +%s.%N`
runtime=$( echo "$end - $start" | bc -l )
echo $runtime

echo "$runtimek2 - $runtime"