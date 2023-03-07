#!/bin/bash

zip=$(cd "$1" ; zip -r "$1" task1/ task2/ task3/)
move=$(cd "$1" ; mv "$1".zip $HOME/OneDrive/Betriebssysteme/PS/Aufgaben/)

echo $zip ; echo $move
echo "Successful zipped: "$1".zip"
echo "Successful moved: "$1".zip"

exit 0
