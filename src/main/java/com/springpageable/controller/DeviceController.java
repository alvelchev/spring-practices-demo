package com.springpageable.controller;

import com.springpageable.dto.FutureDeviceDTO;
import com.springpageable.dto.GetFutureDeviceResponseDTO;
import com.springpageable.service.FutureDeviceService;
import com.springpageable.swagger.SwaggerErrorResponses;
import com.springpageable.swagger.SwaggerPageable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import static com.springpageable.configuration.WebPath.*;

@RestController
@RequestMapping(API_VERSION_1)
@Tag(name = "Device future operations", description = "Basic CRUD operations related to device future")
public class DeviceController {

  private final FutureDeviceService futureDeviceService;

  @Autowired
  public DeviceController(FutureDeviceService futureDeviceService) {
    this.futureDeviceService = futureDeviceService;
  }

  @GetMapping(PATH_FUTURE_DEVICES)
  @Operation(summary = "Retrieves all future devices")
  @SwaggerPageable
  @SwaggerErrorResponses
  @ApiResponse(
      responseCode = "200",
      content =
          @Content(
              array =
                  @ArraySchema(
                      schema = @Schema(implementation = GetFutureDeviceResponseDTO.class))))
  @Parameter(
      name = "searchParameter",
      example = "gts",
      description = "Search by serial number, product id or customer name")
  public Page<GetFutureDeviceResponseDTO> retrieveFutureDevices(
      @PageableDefault(direction = Sort.Direction.ASC, sort = "customerId") Pageable p,
      @RequestParam(name = "searchParameter", required = false, defaultValue = "")
          String searchParameter) {
    return futureDeviceService.retrieveFutureDevices(p, searchParameter);
  }

  @PostMapping(PATH_FUTURE_DEVICES)
  @SwaggerErrorResponses
  @ResponseStatus(HttpStatus.CREATED)
  @ApiResponse(responseCode = "201")
  @Operation(summary = "Creates new device future")
  public void createFutureDevice(@Valid @RequestBody FutureDeviceDTO futureDeviceDTO) {
    futureDeviceService.createFutureDevice(futureDeviceDTO);
  }

  @DeleteMapping(value = PATH_REMOVE_FUTURE_DEVICE)
  @SwaggerErrorResponses
  @Operation(summary = "Deletes a future device by id")
  @Parameter(name = "id", example = "1", description = "The id of the future device to be deleted")
  @ApiResponse(responseCode = "200")
  public void deleteFutureDevice(@PathVariable @Positive long id) {
    futureDeviceService.deleteFutureDevice(id);
  }
}
