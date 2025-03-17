package org.cheva.miniprojecttodolist.TambahTugas

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.cheva.miniprojecttodolist.navigation.Tugas

class TambahTugasViewModel: ViewModel() {
    private val _state = MutableStateFlow(TambahTugasState())
    val state = _state.asStateFlow()



    fun onEvent(event: TambahTugasEvent) {
        when(event) {
            is TambahTugasEvent.OnJudulChanged -> ubahJudul(event.judul)
            is TambahTugasEvent.OnDeskripsiChanged -> ubahDeskripsi(event.deskripsi)
            is TambahTugasEvent.OnKategoriChanged -> ubahKategori(event.kategori)
            TambahTugasEvent.onTambahClicked -> tambah()
            TambahTugasEvent.onUpdateClicked -> edit()
            TambahTugasEvent.onDismissDialog -> dismissDialog()
        }
    }

    private fun ubahJudul(judul: String) {
        _state.update {
            it.copy(judul = judul)
        }
    }

    private fun ubahDeskripsi(deskripsi: String) {
        _state.update {
            it.copy(deskripsi = deskripsi)
        }
    }

    private fun ubahKategori(kategori: String) {
        _state.update {
            it.copy(kategori = kategori)
        }
    }

    private fun tambah() {
        if (state.value.judul.isEmpty()) {
            _state.update {
                it.copy(message = "Judul is required")
            }
            return
        }
        if (state.value.deskripsi.isEmpty()) {
            _state.update {
                it.copy(message = "Deskripsi is required")
            }
            return
        }
        if (state.value.kategori.isEmpty()) {
            _state.update {
                it.copy(message = "Kategori is required")
            }
            return
        }
        _state.update {
            it.copy(message = "Tugas Ditambahkan", success = true)
        }
    }

    private fun edit() {
        if (state.value.judul.isEmpty()) {
            _state.update {
                it.copy(message = "Judul is required")
            }
            return
        }
        if (state.value.deskripsi.isEmpty()) {
            _state.update {
                it.copy(message = "Deskripsi is required")
            }
            return
        }
        if (state.value.kategori.isEmpty()) {
            _state.update {
                it.copy(message = "Kategori is required")
            }
            return
        }
        _state.update {
            it.copy(message = "Tugas Diupdate", success = true)
        }
    }

    fun dismissDialog() {
        _state.update { it.copy(message = "", success = false) }
    }
}