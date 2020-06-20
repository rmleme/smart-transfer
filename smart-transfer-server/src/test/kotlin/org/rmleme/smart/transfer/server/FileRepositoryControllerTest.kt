package org.rmleme.smart.transfer.server

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.springframework.http.HttpStatus
import org.springframework.web.util.UriComponentsBuilder

class FileRepositoryControllerTest : StringSpec({

    val controller = FileRepositoryController()

    "GET /repository should return proper Repository" {
        val response = controller.getRepository()
        response.name shouldBe "Repository"
    }

    "POST /repository should return HTTP Status Code 201" {
        val response = controller.createRepository(FileRepository(), UriComponentsBuilder.newInstance())
        response.statusCode shouldBe HttpStatus.CREATED
    }
})
