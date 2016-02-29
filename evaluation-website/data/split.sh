#!/bin/bash

LINES=$(cat $1 | wc -l);
MID_START=$(($LINES / 2));
MID_END=$(($MID_START + 75));
EXPRESSION=$MID_START,${MID_END}p;
DIR=$(echo $1 | sed -r 's/\..+//g');

mkdir -p $DIR;
head -n 75 $1 			> $DIR/$(echo $1 | sed -r 's/([0-9]+)/\1.1/g');
sed -n $EXPRESSION $1 	> $DIR/$(echo $1 | sed -r 's/([0-9]+)/\1.2/g');
tail -n 75 $1 			> $DIR/$(echo $1 | sed -r 's/([0-9]+)/\1.3/g');

mv $1 $DIR;