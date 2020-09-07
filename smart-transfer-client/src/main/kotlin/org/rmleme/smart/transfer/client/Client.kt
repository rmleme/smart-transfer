package org.rmleme.smart.transfer.client

import com.amazonaws.services.s3.transfer.TransferManager
import com.amazonaws.services.s3.transfer.TransferManagerBuilder
import java.io.Closeable
import java.io.File
import java.nio.file.Files

private const val DOWNLOAD_FOLDER = "downloads"
private const val UPLOAD_FOLDER = "uploads"

class Client(private val fileRepositoryName: String) : Closeable {

    private val transferManager: TransferManager = TransferManagerBuilder.standard().build()

    fun download(file: File) {
        val download = transferManager.download(fileRepositoryName, "$DOWNLOAD_FOLDER/${file.name}", file)
        download.waitForCompletion()
    }

    fun upload(file: File) {
        val upload = transferManager.upload(fileRepositoryName, "$UPLOAD_FOLDER/${file.name}", file)
        upload.waitForCompletion()
    }

    override fun close() = transferManager.shutdownNow()
}

fun main(args: Array<String>) {
    val client = Client("smart-transfer-bucket")

    client.use {
        val fileToUpload = File("${System.getProperty("user.home")}/arquivo_upload.txt")
        fileToUpload.writeText("[Uploads]\nHello World!")
        client.upload(fileToUpload)

        val fileToDownload = File("${System.getProperty("user.home")}/arquivo_download.txt")
        client.download(fileToDownload)
        Files.lines(fileToDownload.toPath()).forEach { println(it) }
    }
}
