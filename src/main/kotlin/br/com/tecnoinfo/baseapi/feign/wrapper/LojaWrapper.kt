package br.com.tecnoinfo.baseapi.feign.wrapper

class LojaWrapper {

    data class LojaResponse(
            val id: Long = 0L,
            val nomeReduzido: String =""
    )

    data class LojaFilterRequest(
            val lojaId: List<Long>
    )
}