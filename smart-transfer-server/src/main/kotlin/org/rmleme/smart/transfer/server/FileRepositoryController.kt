package org.rmleme.smart.transfer.server

import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
class FileRepositoryController {

    // For authenticated clients
    @GetMapping("/repository")
    fun getRepository() = FileRepository(name = "smart-transfer-bucket")

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
            val uriComponents = uriBuilder.path("/repository/{id}").buildAndExpand(repository.id)
            ResponseEntity.created(uriComponents.toUri()).build()
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(FileRepositoryController::class.java)
    }
}
