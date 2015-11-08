#!/bin/bash
# ja) GitHubPagesにしている簡易MavenRepositryにバイナリをアップするスクリプト。
# en) upload scripts to MavenRepositry as a GitHubPages

# ja) 先ずはmasterに移動
# en) move to master
git checkout master

# ja) まずは、バイナリー一式へとビルド。
# en) 1st build all.
sbt clean publish

# ja) Github-Pagesのブランチへ移動。1
# en) move to Github-Pages branch
git checkout gh-pages

# ja) 設定により、./target/repo にビルドした一式が入っているはずなので、そこからトップへコピー。
# en) built files will be in './target/repo', copy them into the top
cp -rf ./target/repo/com ./

# git commit
git add ./
git commit ./ -m 'Build and publish Binally.'
git push

# ja) ブランチを元に戻す。
# en) roll back branch
git checkout master
