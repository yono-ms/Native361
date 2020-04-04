/*
 * Copyright (c) 2020. No name
 */

package com.example.native361.ui

import com.example.native361.constant.RequestCode

/**
 * アラートダイアログ情報.
 * 組み合わせ:
 * 1. title/message
 * 2. title/items
 * 3. title/exception
 */
data class DialogMessage(
    /**
     * リクエストコード. 必須.
     */
    val requestCode: RequestCode,
    /**
     * タイトルリソースID. 必須.
     */
    val title: Int,
    /**
     * メッセージリソースID. 任意.
     */
    val message: Int? = null,
    /**
     * 単一選択用リスト.
     */
    val items: List<String>? = null,
    /**
     * 例外情報. 指定すると例外フォーマット表示に切り替え.
     */
    val exception: Throwable? = null
)
