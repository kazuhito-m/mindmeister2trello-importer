#!/bin/bash
# GitHubPagesにしている簡易MavenRepositryにバイナリをアップするスクリプト。

# 先ずはmasterに移動
git checkout master

# まずは、バイナリー一式へとビルド。
sbt clean publish

# Github-Pagesのブランチへ移動。1
git checkout gh-pages

# 設定により、./target/repo にビルドした一式が入っているはずなので、そこからトップへコピー。
cp -rf ./target/repo/com ../

# git commit
git add ../
git commit ../ -m 'Build and publish Binally.'
git push

# ブランチを元に戻す。
git checkout master
