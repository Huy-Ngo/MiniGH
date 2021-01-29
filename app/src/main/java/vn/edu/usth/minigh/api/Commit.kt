package vn.edu.usth.minigh.api

import android.os.Parcel
import android.os.Parcelable

data class Author (
    val name: String
)

data class Commit (
        val message: String,
        val author: Author
)

data class CommitContent(
        val files: ArrayList<FileChanges>
)

data class FileChanges (
        val filename: String,
        val patch: String
)


class CommitItem(
        val sha: String?,
        val commit: Commit
)
