package Model

import android.os.Parcel
import android.os.Parcelable

open abstract class Hewan(
    open var nama:String?,
    open var jenis:String?,
    open var usia: String?
) {
    var imageUri: String? = ""
    open var hewansuara:String = ""
    open var hewanamakan:String = ""
}