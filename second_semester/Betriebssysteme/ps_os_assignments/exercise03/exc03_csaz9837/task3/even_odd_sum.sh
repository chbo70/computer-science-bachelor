#!/bin/bash

#checks if ./even exists, if not it makes it
FILENAME="even"
if [ ! -e $FILENAME ] 
then
  make
fi

echo ""
EVEN=0 
ODD=0

for j in "$@"
do
./even "$j"
RETURNVAL="$?"
#echo "return: $RETURN"
if [ $RETURNVAL == 3 ] 
then
  echo "The input: $j is not a number"
  exit 0
fi
if [ $RETURNVAL == 0 ]
then 
  EVEN=$((EVEN+j))
fi
if [ $RETURNVAL == 1 ]
then
  ODD=$((ODD+j))
fi
done

echo "sum of even numbers: $EVEN"
echo "sum of odd numbers: $ODD"
