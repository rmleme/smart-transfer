package com.rmleme.smart.transfer.client

import com.amazonaws.services.s3.transfer.Download
import com.amazonaws.services.s3.transfer.TransferManager
import com.amazonaws.services.s3.transfer.TransferManagerBuilder
import java.io.File

class Client {

}

fun main(args: Array<String>) {
    val transferManager: TransferManager = TransferManagerBuilder.standard().build()
    val file = File("arquivo.txt")
    val download: Download = transferManager.download("bucket", "key", file)
    download.waitForCompletion()
    transferManager.shutdownNow()
}