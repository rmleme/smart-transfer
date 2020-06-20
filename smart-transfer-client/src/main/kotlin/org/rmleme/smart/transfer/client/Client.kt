package org.rmleme.smart.transfer.client

import com.amazonaws.services.s3.transfer.Download
import com.amazonaws.services.s3.transfer.TransferManager
import com.amazonaws.services.s3.transfer.TransferManagerBuilder
import java.io.File
import java.nio.file.Files

private const val ROOT_DIRECTORY = "/tmp"

class Client(private val bucket: String) {

    fun download(key: String): File {
        val transferManager: TransferManager = TransferManagerBuilder.standard().build()
        val file = File("$ROOT_DIRECTORY/$key")
        val download: Download = transferManager.download(bucket, key, file)
        download.waitForCompletion()
        transferManager.shutdownNow()
        return file
    }
}

fun main(args: Array<String>) {
    val client = Client("smart-transfer-bucket")
    val file = client.download("download/arquivo.txt")
    Files.lines(file.toPath()).forEach { print(it) }
}
