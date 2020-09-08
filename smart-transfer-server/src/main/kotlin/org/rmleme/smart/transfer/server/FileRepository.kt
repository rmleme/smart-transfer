package org.rmleme.smart.transfer.server

data class FileRepository(
    val id: Int,
    val name: String,
    val ownerId: Int,
    val ownerName: String
)
