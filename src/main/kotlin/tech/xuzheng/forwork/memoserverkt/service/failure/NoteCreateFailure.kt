package tech.xuzheng.forwork.memoserverkt.service.failure

enum class NoteCreateFailure(val message: String) {
    DbServiceDisconnected("数据库连接失败"),
    KeyExist("笔记关键词已存在")
}