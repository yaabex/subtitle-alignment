#!/bin/bash

for file in $(ls . | grep .txt); do
	COUNT=$(cat $file | sed -r '/.+ --- .+$/d' | wc -l);
	if [ $COUNT != 0 ]; then
		echo -e $COUNT '\t' $file;
	fi
done