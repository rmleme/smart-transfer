package org.rmleme.smart.transfer.client

import com.amazonaws.services.s3.transfer.TransferManager
import com.amazonaws.services.s3.transfer.TransferManagerBuilder
import org.slf4j.LoggerFactory
import java.io.Closeable
import java.io.File

private const val DOWNLOAD_FOLDER = "downloads"
private const val UPLOAD_FOLDER = "uploads"

class Client(private val fileRepositoryName: String) : Closeable {

    private val transferManager: TransferManager = TransferManagerBuilder.standard().build()

    fun download(file: File) {
        logger.info("Starting download of ${file.name}...")
        val download = transferManager.download(fileRepositoryName, "$DOWNLOAD_FOLDER/${file.name}", file)
        download.waitForCompletion()
        logger.info("Download completed.")
    }

    fun upload(file: File) {
        logger.info("Starting upload of ${file.name}...")
        val upload = transferManager.upload(fileRepositoryName, "$UPLOAD_FOLDER/${file.name}", file)
        upload.waitForCompletion()
        logger.info("Upload completed.")
    }

    override fun close() = transferManager.shutdownNow()

    companion object {
        private val logger = LoggerFactory.getLogger(Client::class.java)
    }
}

fun main(args: Array<String>) {
    val client = Client("smart-transfer-bucket")

    client.use {
        val fileToUpload = File("${System.getProperty("user.home")}/arquivo_upload.txt")
        if (!fileToUpload.exists()) {
            fileToUpload.writeText("[Uploads]\nHello World!")
        }
        client.upload(fileToUpload)

        val fileToDownload = File("${System.getProperty("user.home")}/arquivo_download.txt")
        client.download(fileToDownload)
        println("Successfully downloaded file ${fileToDownload.name}")
    }
}
