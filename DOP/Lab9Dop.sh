#!/bin/bash

cd /home/vadim/Labs/prog/lab9/DOP

rep="src"
jvrep="bin"
compile="javac -d ./bin $rep/*.java"
launch="java -cp $jvrep $rep.Main"

echo ""

echo $compile

$compile

echo $launch $*

echo ""

$launch $* 2> ./log/launch.log

cd -