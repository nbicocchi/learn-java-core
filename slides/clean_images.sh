#!/bin/bash

for fname in images/*; do
	if ! grep $(basename "$fname") *.md >/dev/null; then
		echo "$fname"
		rm -rf "$fname"
	fi
done