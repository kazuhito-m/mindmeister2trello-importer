#!/bin/bash
# upload scripts to MavenRepositry as a GitHubPages

#  move to master
git checkout master

# 1st build all.
sbt clean publish

# move to Github-Pages branch
git checkout gh-pages

# built files will be in './target/repo', copy them into the top
cp -rf ./target/repo/com ./

# git commit
git add ./
git commit ./ -m 'Build and publish Binally.'
git push

# roll back branch
git checkout master
