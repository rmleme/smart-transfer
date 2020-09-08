package org.rmleme.smart.transfer.server

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

private const val FILE_REPOSITORY_NAME_PREFIX = "smart-transfer-"
private const val OWNER_ID_TAG = "ownerId"
private const val OWNER_NAME_TAG = "ownerName"

@Service
class FileRepositoryService {

    fun createRepository(repository: FileRepository): Boolean {
        val cloudProvider = repository.cloudProviderId.cloudProvider
        val repositoryName = FILE_REPOSITORY_NAME_PREFIX + repository.name

        return if (cloudProvider.fileRepositoryExists(repositoryName)) {
            logger.warn("File repository $repositoryName already exists.")
            false
        } else {
            cloudProvider.createRepository(repositoryName)
            logger.info("File repository $repositoryName successfully created.")
            applyTags(repository, repositoryName)
            true
        }
    }

    private fun applyTags(repository: FileRepository, repositoryName: String) {
        val tags = mapOf(
            OWNER_ID_TAG to repository.ownerId.toString(),
            OWNER_NAME_TAG to repository.ownerName
        )
        repository.cloudProviderId.cloudProvider.applyTags(repositoryName, tags)
        logger.debug("Successfully applied tags {${tags.entries.joinToString()}} to $repositoryName.")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FileRepositoryService::class.java)
    }
}
