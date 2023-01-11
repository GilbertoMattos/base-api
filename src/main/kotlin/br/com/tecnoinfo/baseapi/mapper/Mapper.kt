package br.com.tecnoinfo.baseapi.mapper

interface Mapper<E, S> {
    fun map(param: E): S
}