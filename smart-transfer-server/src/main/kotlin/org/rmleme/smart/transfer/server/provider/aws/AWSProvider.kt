package org.rmleme.smart.transfer.server.provider.aws

import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.BucketTaggingConfiguration
import com.amazonaws.services.s3.model.TagSet
import org.rmleme.smart.transfer.server.provider.CloudProvider

class AWSProvider : CloudProvider {

    private val s3: AmazonS3 = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build()

    override fun fileRepositoryExists(repositoryName: String) = s3.doesBucketExistV2(repositoryName)

    override fun createRepository(repositoryName: String) {
        s3.createBucket(repositoryName)
    }

    override fun applyTags(repositoryName: String, tags: Map<String, String>) {
        s3.setBucketTaggingConfiguration(repositoryName, BucketTaggingConfiguration(listOf(TagSet(tags))))
    }
}
