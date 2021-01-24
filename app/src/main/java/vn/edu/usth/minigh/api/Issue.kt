package vn.edu.usth.minigh.api

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

data class IssueOfUser (
    val total_count: Int,
    val items: ArrayList<Issue>,
)

data class Issue(
        val url: String?,
        val repository_url: String?,
        val number: Int,
        val title: String?,
        val state: String?,
        val body: String?,
        val login: String?,
        val comments_url: String?
): Parcelable{
    protected constructor(parcelInt: Parcel): this(
            parcelInt.readString(),
            parcelInt.readString(),
            parcelInt.readInt(),
            parcelInt.readString(),
            parcelInt.readString() ,
            parcelInt.readString(),
            parcelInt.readString(),
            parcelInt.readString()
    )

    override fun writeToParcel(dest: Parcel?, flags: Int) {
//        TODO("Not yet implemented")
        dest?.writeString(url)
        dest?.writeString(repository_url)
        dest?.writeString(title)
        dest?.writeString(state)
        dest?.writeString(body)
        dest?.writeString(login)
        dest?.writeString(comments_url)
        dest?.writeInt(number)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Issue> {
        override fun createFromParcel(parcel: Parcel): Issue {
            return Issue(parcel)
        }

        override fun newArray(size: Int): Array<Issue?> {
            return arrayOfNulls(size)
        }
    }
}