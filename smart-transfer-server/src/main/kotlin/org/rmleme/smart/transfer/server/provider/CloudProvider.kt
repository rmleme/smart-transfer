package org.rmleme.smart.transfer.server.provider

import org.rmleme.smart.transfer.server.provider.aws.AWSProvider

enum class CloudProviderId(val cloudProvider: CloudProvider) {
    AWS(AWSProvider())
}

interface CloudProvider {

    fun fileRepositoryExists(repositoryName: String): Boolean

    fun createRepository(repositoryName: String)

    fun applyTags(repositoryName: String, tags: Map<String, String>)
}
