package br.com.tecnoinfo.baseapi.exception

import br.com.tecnoinfo.baseapi.wrapper.response.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class AdviceExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationError(
        ex: MethodArgumentNotValidException,
        request: WebRequest
    ): ResponseEntity<ApiResponse<String>> {

        val errorMessages = ex.bindingResult.fieldErrors.map {
            it.defaultMessage ?: ""
        }.toList()

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ApiResponse(
                status = HttpStatus.BAD_REQUEST,
                messages = errorMessages
            )
        )
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RegistryNotFoundException::class)
    fun registryNotFound(ex: RegistryNotFoundException): ResponseEntity<ApiResponse<*>> {
        return ResponseEntity(
            ApiResponse(
                status = HttpStatus.NOT_FOUND,
                data = null,
                messages = arrayListOf(ex.message ?: "")
            ),
            HttpStatus.NOT_FOUND
        )
    }
}