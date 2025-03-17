package org.cheva.miniprojecttodolist.navigation

data class Tugas(
    val judul: String,
    val deskripsi: String,
    val kategori: String
)

//private var judul: String,
//private var deskripsi: String,
//private var kategori: String
//) {
//    init {
//        require(judul.isNotBlank()) { "Judul tugas tidak boleh kosong" }
//        require(deskripsi.isNotBlank()) { "Deskripsi tugas tidak boleh kosong" }
//        require(kategori.isNotBlank()) { "Kategori tugas tidak boleh kosong" }
//    }
//
//    fun ubahJudul(judulBaru: String) {
//        require(judulBaru.isNotBlank()) { "Judul baru tidak boleh kosong" }
//        judul = judulBaru
//    }
//
//    fun ubahDeskripsi(deskripsiBaru: String) {
//        require(deskripsiBaru.isNotBlank()) { "Deskripsi baru tidak boleh kosong" }
//        deskripsi = deskripsiBaru
//    }
//
//    fun ubahKategori(kategoriBaru: String) {
//        require(kategoriBaru.isNotBlank()) { "Kategori baru tidak boleh kosong" }
//        kategori = kategoriBaru
//    }
//
//    fun getJudul(): String {
//        return judul
//    }
//
//    fun getDeskripsi(): String {
//        return deskripsi
//    }
//
//    fun getKategori(): String {
//        return kategori
//    }
