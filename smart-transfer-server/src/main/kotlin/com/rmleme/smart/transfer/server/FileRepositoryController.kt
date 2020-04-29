package com.rmleme.smart.transfer.server

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FileRepositoryController {

    @GetMapping("/repository")
    fun getBucketAccess() = "Successfully got access."
}