#!/bin/sh
#Compile the document written in Markdown to HTML

EXE=Markdown.pl

IN="src/main/md"
OUT="src/main/doc"
for d in `ls $IN/`; do
    name=`basename $d .md`
    $EXE $IN/$d > $OUT/$name.html
    cp $IN/$d $OUT/$name.txt
done
