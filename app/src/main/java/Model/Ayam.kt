package Model

class Ayam(override var nama:String?, override var jenis:String?, override var usia:String?): Hewan("", "Ayam", "") {
    override var hewanamakan: String = "Kamu Memberi Makan Biji Bijian"
    override var hewansuara: String = "Pok Pok Petok ....."
}