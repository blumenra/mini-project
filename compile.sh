#!/bin/bash

make -C src/c/compile clean
make -C src/c/compile

./compile
rm -f compile

make -C src/c/run clean
make -C src/c/run
