#!/bin/bash

for i in {1..20}
do
	echo 1 > /sys/class/gpio/gpio44/value
	sleep 1
	echo 0 > /sys/class/gpio/gpio44/value
	sleep 1
done
