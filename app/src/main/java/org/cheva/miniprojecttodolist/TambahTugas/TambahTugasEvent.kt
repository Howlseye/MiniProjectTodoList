package org.cheva.miniprojecttodolist.TambahTugas

sealed interface TambahTugasEvent {
    data class OnJudulChanged(val judul: String) : TambahTugasEvent
    data class OnDeskripsiChanged(val deskripsi: String) : TambahTugasEvent
    data class OnKategoriChanged(val kategori: String) : TambahTugasEvent
    object onTambahClicked : TambahTugasEvent
    object onUpdateClicked : TambahTugasEvent
    object onDismissDialog : TambahTugasEvent
}