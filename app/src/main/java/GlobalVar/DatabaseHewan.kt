package GlobalVar

import Model.Hewan
import android.util.Base64

class DatabaseHewan {
    companion object {
        val STORAGE_PERMISSION_CODE: Int=3
        val listDataHewan = ArrayList<Hewan>()
        val CAMERA_PERMISSION_CODE = 5

        fun ByteArrToString(bArray: ByteArray): String {
            return Base64.encodeToString(bArray, Base64.DEFAULT)
        }

        fun StringToByteArr(raw: String):ByteArray{
            return Base64.decode(raw, Base64.DEFAULT)
        }
    }
}