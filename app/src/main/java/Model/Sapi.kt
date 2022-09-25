package Model

class Sapi(override var nama: String?, override var jenis: String?, override var usia: String?): Hewan("", "Sapi", "") {
    override var hewanamakan: String = "Kamu Memberi Makan Rumput Rumputan"
    override var hewansuara: String = "Moo Moo Moo ....."
}