package org.cheva.miniprojecttodolist.TambahTugas

data class TambahTugasState (
    val judul: String = "",
    val deskripsi: String = "",
    val kategori: String = "",
    val message: String = "",
    val success: Boolean = false,
)