package org.rmleme.smart.transfer.server

import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.BucketTaggingConfiguration
import com.amazonaws.services.s3.model.TagSet
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

private const val FILE_REPOSITORY_NAME_PREFIX = "smart-transfer-"
private const val OWNER_ID_TAG = "ownerId"
private const val OWNER_NAME_TAG = "ownerName"

@Service
class FileRepositoryService {

    private val s3: AmazonS3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build()

    fun createRepository(repository: FileRepository): Boolean {
        val repositoryName = FILE_REPOSITORY_NAME_PREFIX + repository.name

        return if (s3.doesBucketExistV2(repositoryName)) {
            logger.warn("File repository $repositoryName already exists.")
            false
        } else {
            s3.createBucket(repository.name)
            logger.info("File repository $repositoryName successfully created.")
            createTags(repository, repositoryName)
            true
        }
    }

    private fun createTags(repository: FileRepository, fileRepositoryName: String) {
        val tags = listOf(
            TagSet(
                mapOf(
                    OWNER_ID_TAG to repository.ownerId.toString(),
                    OWNER_NAME_TAG to repository.ownerName
                )
            )
        )
        s3.setBucketTaggingConfiguration(repository.name, BucketTaggingConfiguration(tags))
        logger.debug("Successfully applied tags {${tags[0].allTags.entries.joinToString()}} to $fileRepositoryName.")
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FileRepositoryService::class.java)
    }


}
