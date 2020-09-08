package org.rmleme.smart.transfer.server

import org.rmleme.smart.transfer.server.provider.CloudProviderId

data class FileRepository(
    val name: String,
    val ownerId: Int,
    val ownerName: String,
    val cloudProviderId: CloudProviderId = CloudProviderId.AWS
)
