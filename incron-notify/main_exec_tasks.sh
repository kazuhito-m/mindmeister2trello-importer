#!/bin/bash

# Markdownが更新されれば、HTMLの作成を行う。
rm -rf ${CUR_DIR}/html
mkdir ${CUR_DIR}/html

for i in $(ls ${CUR_DIR}/md/*.md) ; do
        pandoc -f markdown -t html ${i} -s -o `echo ${i} | sed -e 's/md/html/g'`
done

