package com.rmleme.smart.transfer.server

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class FileRepositoryControllerTest : StringSpec({

    val controller = FileRepositoryController()

    "GET /repository should return proper response message" {
        val response = controller.getRepositoryAccess()
        response shouldBe "Successfully got access."
    }
})