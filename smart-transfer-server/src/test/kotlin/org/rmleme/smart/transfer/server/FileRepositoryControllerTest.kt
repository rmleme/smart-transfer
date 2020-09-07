package org.rmleme.smart.transfer.server

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class FileRepositoryControllerTest : StringSpec({

    val controller = FileRepositoryController()

    "GET /repository should return proper file repository" {
        val response = controller.getRepository()
        response.name shouldBe "smart-transfer-bucket"
    }

    "POST /repository should return HTTP Status Code 201" {
        // TODO: mock S3
        // val response = controller.createRepository(FileRepository(), UriComponentsBuilder.newInstance())
        // response.statusCode shouldBe HttpStatus.CREATED
    }
})
