#!/bin/bash

make -C src/c/to_compile clean
make -C src/c/to_compile

./compile
rm -f compile

make -C src/c/run clean
make -C src/c/run
