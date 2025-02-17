#!/bin/bash

find_slides_using_it() {
	for slide in *.md; do
		if grep "$1" "$slide" > /dev/null; then
			echo "$slide"
		fi
	done
}

check_extension() {
	for extension in $1; do
		[ "${2##*.}" == "$extension" ] && return 0
	done
	return 1
}


for fname in images/*; do
	# already avif image
	if check_extension "avif" "$fname"; then
		continue
	fi

	# conversion to avif not supported
	if ! check_extension "jpeg jpg png" "$fname"; then
		echo "[NS]" "$fname"
		continue
	fi

	# useless image
	slides=$(find_slides_using_it "$fname")
	if [ -z "$slides" ]; then
		echo "[NU]" "$fname"
		rm -rf "$fname"
		continue
	fi

	# convert to avif and update slides
	fname_avif="${fname%.*}".avif
	echo "[OK]" "$fname"
	avifenc "$fname" "$fname_avif" > /dev/null
	rm -rf "$fname"

	for slide in "$slides"; do
		sed -i s/$(basename "$fname")/$(basename "$fname_avif")/g "$slide"				
	done
done