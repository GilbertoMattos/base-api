package br.com.tecnoinfo.baseapi.wrapper.response

import org.springframework.http.HttpStatus
import java.io.Serializable

data class ApiResponse<T>(
    val status: HttpStatus = HttpStatus.OK,
    val message: String = "",
    val messages: List<String> = arrayListOf(),
    val data: T? = null,
    val page: PageDet? = null
) : Serializable

data class PageDet(
    val totalRegistros: Long,
    val totalPage: Int,
    val pageNumber: Int,
    val pageSize: Int,
    val isLast: Boolean,
    val isFirst: Boolean,
)