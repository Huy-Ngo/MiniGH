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

data class CommitItem (
        val sha: String?,
        val author: Author,
        val commit: Commit
): Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            TODO("author"),
            TODO("commit")) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(sha)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CommitItem> {
        override fun createFromParcel(parcel: Parcel): CommitItem {
            return CommitItem(parcel)
        }

        override fun newArray(size: Int): Array<CommitItem?> {
            return arrayOfNulls(size)
        }
    }
}

