package br.com.tecnoinfo.baseapi.service

import br.com.tecnoinfo.baseapi.feign.ApiLojaFeign
import br.com.tecnoinfo.baseapi.feign.wrapper.LojaWrapper
import org.springframework.stereotype.Service

@Service
class LojaService(private val lojaFeign: ApiLojaFeign) {
    fun findById(id: Long) = lojaFeign.findById(id)
    fun findAll(id: Long) = lojaFeign.findAll(LojaWrapper.LojaFilterRequest(arrayListOf(id, 10)), 10)
}