package br.com.tecnoinfo.baseapi.mapper

import br.com.tecnoinfo.baseapi.wrapper.response.PageDet
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class PagetoPageDetMapper : Mapper<Page<*>, PageDet> {
    override fun map(param: Page<*>): PageDet {
        return PageDet(
            totalRegistros = param.totalElements,
            totalPage = param.totalPages,
            pageNumber = param.number,
            pageSize = param.size,
            isLast = param.isLast,
            isFirst = param.isFirst
        )
    }
}