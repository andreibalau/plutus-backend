package com.finance.plutus.api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import javax.validation.Valid;
import java.util.List;

import com.finance.plutus.model.common.EntityCreatedDto;
import com.finance.plutus.model.invoice.dto.InvoiceDto;
import com.finance.plutus.model.invoice.dto.ModifyInvoiceDto;
import com.finance.plutus.model.invoice.dto.PreviewInvoiceDto;
import com.finance.plutus.util.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	List<PreviewInvoiceDto> findAll();

	/**
	 * Invoice creation
	 */
	@ApiOperation(value = "Create a new invoice",
			nickname = "create",
			tags = "invoice-controller")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Invoice creation successfully"),
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
	@ResponseStatus(CREATED)
	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	EntityCreatedDto create(
			@ApiParam(value = "The request containing the invoice payload", required = true)
			@Valid @RequestBody ModifyInvoiceDto modifyInvoiceDto);

	/**
	 * Find invoice by id
	 */
	@ApiOperation(value = "Find invoice by id",
			nickname = "findById",
			tags = "invoice-controller")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 404, message = "Invoice not found"),
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
	@GetMapping(value = "/{invoiceId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	InvoiceDto findById(
			@ApiParam(value = "The id of the invoice", required = true)
			@PathVariable Long invoiceId);

	/**
	 * Update a invoice
	 */
	@ApiOperation(value = "Update invoice",
			nickname = "update",
			tags = "invoice-controller")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 404, message = "Invoice not found"),
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
	@PutMapping(value = "/{invoiceId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	void update(
			@ApiParam(value = "The id of the invoice", required = true)
			@PathVariable Long invoiceId,
			@ApiParam(value = "The invoice payload", required = true)
			@Valid @RequestBody ModifyInvoiceDto modifyInvoiceDto);

	/**
	 * Delete an invoice
	 */
	@ApiOperation(value = "Delete an invoice",
			nickname = "delete",
			tags = "invoice-controller")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 404, message = "Invoice not found"),
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
	@DeleteMapping(value = "/{invoiceId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	void delete(
			@ApiParam(value = "The id of the invoice", required = true)
			@PathVariable Long invoiceId);

}
