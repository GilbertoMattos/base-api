package br.com.tecnoinfo.baseapi.feign

import br.com.tecnoinfo.baseapi.feign.wrapper.LojaWrapper
import br.com.tecnoinfo.baseapi.wrapper.response.ApiResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.*

@FeignClient(
    url = "\${tecnoinfo.url-api.loja-api}",
    value = "fsj-loja-api",
    path = "api/v1/lojas",
    decode404 = false
)
interface ApiLojaFeign {

    @GetMapping("findLojasResumidoById/{id}")
    fun findById(@PathVariable("id") id: Long): ApiResponse<LojaWrapper.LojaResponse>

    @PostMapping("findAll")
    fun findAll(
        @RequestBody payload: LojaWrapper.LojaFilterRequest,
        @RequestParam("page") page: Int
    ): List<LojaWrapper.LojaResponse>
}