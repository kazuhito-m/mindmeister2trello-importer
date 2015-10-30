#!/bin/bash

TITLE="引継資料作成"
DESCRIPTION="Markdownファイルを更新しました。HTMLの再作成を行いました。"
CUR_DIR=`dirname ${0}`

export DISPLAY=:0

# ロックファイルがあれば起動しない
LOCKFILE=/tmp/`basename $0`.lock
if [ -e ${LOCKFILE} ] ; then
	exit 1;
else
	touch ${LOCKFILE}
fi

# メインタスクをキック
${CUR_DIR}/main_exec_tasks.sh

/usr/bin/notify-send -u low ${TITLE} ${DESCRIPTION}

# どうしても複数回呼ばれるため、インターバル入れる。
sleep 1

# ロックファイルを削除して終了
rm $LOCKFILE
exit 0

