package Model

class Kambing(override var nama:String?, override var jenis:String?, override var usia:String?):Hewan("", "Kambing", "") {
    override var hewansuara: String = "Mbek Bek Mbek ...."
    override var hewanamakan: String = "Kamu Memberi Makan Rumput Rumputan"
}