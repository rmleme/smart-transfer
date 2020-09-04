package org.rmleme.smart.transfer.client

import com.amazonaws.services.s3.transfer.TransferManager
import com.amazonaws.services.s3.transfer.TransferManagerBuilder
import java.io.File
import java.nio.file.Files

class Client(private val fileRepositoryName: String) {

    private val transferManager: TransferManager = TransferManagerBuilder.standard().build()

    fun download(key: String): File {
        val file = File("/tmp/$key")
        val download = transferManager.download(fileRepositoryName, key, file)
        download.waitForCompletion()
        transferManager.shutdownNow()
        return file
    }

    fun upload(file: File) {
        val upload = transferManager.upload("uploads/$fileRepositoryName", file.name, file)
        upload.waitForCompletion()
        transferManager.shutdownNow()
    }
}

fun main(args: Array<String>) {
    val client = Client("smart-transfer-bucket")

    var file = File("/tmp/arquivo.txt")
    file.writeText("Hello World!")

    client.upload(file)

    file = client.download("downloads/arquivo.txt")
    Files.lines(file.toPath()).forEach { print(it) }
}
