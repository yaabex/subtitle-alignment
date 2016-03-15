#!/bin/bash
DIRS=$(ls -l | grep ^d | sed -r 's/.*\s(\w+)$/\1/g');

while read -r folder; do
	if [ "$(ls $folder)" ]
		then
			rm -f $folder/$folder.txt;
			cat $folder/* > $folder/$folder.txt;
	fi
done <<< "$DIRS"