package com.finance.plutus.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import com.finance.plutus.model.invoice.dto.InvoiceDto;
import com.finance.plutus.util.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Plutus
 * Created by catalin on 1/15/2020
 */
@RequestMapping(Api.INVOICES)
public interface InvoiceApi {

	/**
	 * Find all invoices
	 */
	@ApiOperation(value = "Find all invoices",
			nickname = "findAll",
			tags = "invoice-controller")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 405, message = "Method not allowed"),
			@ApiResponse(code = 415, message = "Unsupported media type"),
			@ApiResponse(code = 500, message = "Internal server error")
	})
	@ApiImplicitParams({
			@ApiImplicitParam(name = "Content-Type",
					value = "The content type of the request body",
					paramType = "header", required = true, defaultValue = "application/json"),
			@ApiImplicitParam(name = "Authorization",
					value = "The authorization token header",
					paramType = "header", required = true),
	})
	@GetMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	List<InvoiceDto> findAll();

}
