package org.rmleme.smart.transfer.server

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
    fun getRepository() = FileRepository(name = "Repository")

    // For authenticated admin users
    @PostMapping("/repository")
    fun createRepository(
        @RequestBody repository: FileRepository,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<FileRepository> {
        val uriComponents = uriBuilder.path("/repository/{id}").buildAndExpand(repository.id)
        return ResponseEntity.created(uriComponents.toUri()).build()
    }
}
