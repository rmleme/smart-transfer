package org.rmleme.smart.transfer.client

import com.amazonaws.event.ProgressEvent
import com.amazonaws.services.s3.model.GetObjectRequest
import com.amazonaws.services.s3.transfer.Download
import com.amazonaws.services.s3.transfer.PersistableDownload
import com.amazonaws.services.s3.transfer.PersistableTransfer
import com.amazonaws.services.s3.transfer.TransferManagerBuilder
import com.amazonaws.services.s3.transfer.internal.S3SyncProgressListener
import org.slf4j.LoggerFactory
import java.io.Closeable
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

private const val DOWNLOAD_FOLDER = "downloads"
private const val UPLOAD_FOLDER = "uploads"

class Client(private val fileRepositoryName: String) : Closeable {

    private val transferManager = TransferManagerBuilder.standard().build()

    private val progressListener = object : S3SyncProgressListener() {
        override fun progressChanged(progressEvent: ProgressEvent) {
            logger.info("Bytes transferred: ${progressEvent.bytesTransferred}")
        }

        override fun onPersistableTransfer(persistableTransfer: PersistableTransfer) {
            val downloadStateFile = File(System.getProperty("user.home") + "/download-state")
            if (!downloadStateFile.exists()) {
                downloadStateFile.createNewFile()
            }
            FileOutputStream(downloadStateFile).use { persistableTransfer.serialize(it) }
        }
    }

    fun download(file: File) {
        val downloadStateFile = File(System.getProperty("user.home") + "/download-state")
        if (!downloadStateFile.exists()) {
            startNewDownload(file, downloadStateFile)
        } else {
            resumeDownload(file, downloadStateFile)
        }
    }

    private fun startNewDownload(file: File, downloadStateFile: File) {
        logger.info("Starting download of ${file.name}...")
        val getObjectRequest = GetObjectRequest(fileRepositoryName, "$DOWNLOAD_FOLDER/${file.name}")
        val download = transferManager.download(getObjectRequest, file, progressListener)
        completeDownload(download, downloadStateFile)
    }

    private fun resumeDownload(file: File, downloadStateFile: File) {
        logger.info("Resuming download of ${file.name}...")
        FileInputStream(downloadStateFile).use {
            val persistableDownload = PersistableDownload.deserializeFrom<PersistableDownload>(it)
            val download = transferManager.resumeDownload(persistableDownload)
            download.addProgressListener(progressListener)
            completeDownload(download, downloadStateFile)
        }
    }

    private fun completeDownload(download: Download, downloadStateFile: File) {
        download.waitForCompletion()
        transferManager.shutdownNow()
        if (download.isDone) {
            downloadStateFile.delete()
        }
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
        /*val fileToUpload = File("${System.getProperty("user.home")}/arquivo_upload.txt")
        if (!fileToUpload.exists()) {
            fileToUpload.writeText("[Uploads]\nHello World!")
        }
        client.upload(fileToUpload)*/

        val fileToDownload = File("${System.getProperty("user.home")}/arquivo_download.txt")
        client.download(fileToDownload)
        println("Successfully downloaded file ${fileToDownload.name}")
    }
}
