package org.rmleme.smart.transfer.server

import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.BucketTaggingConfiguration
import com.amazonaws.services.s3.model.TagSet
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

private const val OWNER_ID_TAG = "ownerId"
private const val OWNER_NAME_TAG = "ownerName"

@RestController
class FileRepositoryController {

    // For authenticated clients
    @GetMapping("/repository")
    fun getRepository() {
        // TODO
    }

    // For authenticated admin users
    @PostMapping("/repository")
    fun createRepository(
        @RequestBody repository: FileRepository,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<FileRepository> {
        val s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build()
        return if (s3.doesBucketExistV2(repository.name)) {
            logger.warn("Bucket ${repository.name} already exists.")
            ResponseEntity.ok().build()
        } else {
            s3.createBucket(repository.name)
            logger.info("File repository ${repository.name} successfully created.")

            val tags = listOf(
                TagSet(
                    mapOf(
                        OWNER_ID_TAG to repository.ownerId.toString(),
                        OWNER_NAME_TAG to repository.ownerName
                    )
                )
            )
            s3.setBucketTaggingConfiguration(repository.name, BucketTaggingConfiguration(tags))

            val uriComponents = uriBuilder.path("/repository/{id}").buildAndExpand(repository.id)
            ResponseEntity.created(uriComponents.toUri()).build()
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FileRepositoryController::class.java)
    }
}
