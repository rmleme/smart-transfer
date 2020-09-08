package org.rmleme.smart.transfer.server

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.CONFLICT
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FileRepositoryController {

    @Autowired
    private lateinit var fileRepositoryService: FileRepositoryService

    // For authenticated clients
    @GetMapping("/repository")
    fun getRepository() {
        // TODO
    }

    // For authenticated admin users
    @PostMapping("/repository")
    fun createRepository(@RequestBody repository: FileRepository): ResponseEntity<FileRepository> {
        val created = fileRepositoryService.createRepository(repository)
        return ResponseEntity.status(if (created) CREATED else CONFLICT).build()
    }
}
