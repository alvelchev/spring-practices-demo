package com.springpageable.controller;

import com.springpageable.dto.GetUserResponseDTO;
import com.springpageable.enums.LdapGroup;
import com.springpageable.service.CriteriaBuilderExampleService;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.springpageable.configuration.WebPath.API_VERSION_1;
import static com.springpageable.configuration.WebPath.PATH_USERS;

@RestController
@RequestMapping(API_VERSION_1)
@Validated
@Tag(name = "Criteria Builder Example", description = "Pageing result from criteria builder")
public class CriteriaBuilderExampleController {

    public CriteriaBuilderExampleController(CriteriaBuilderExampleService criteriaBuilderExampleService) {
        this.criteriaBuilderExampleService = criteriaBuilderExampleService;
    }
    @Autowired
    private final CriteriaBuilderExampleService criteriaBuilderExampleService;

    @GetMapping(PATH_USERS)
    @SwaggerPageable
    @SwaggerErrorResponses
    @Operation(
            summary = "Retrieves users by username. Can be used for retrieving or excluding " +
                      "users with concrete usernames, but not in the same time")
    @Parameter(name = "exclude-usernames", description = "User names which to be excluded from the result set",
               array = @ArraySchema(schema = @Schema(example = "testUser")))
    @Parameter(name = "usernames", array = @ArraySchema(schema = @Schema(example = "testUser")),
               description = "Search by list of usernames")
    @Parameter(name = "searchParameter", example = "testUser", description = "Search by username, first name or last name")
    @ApiResponse(responseCode = "200",
                 content = @Content(array = @ArraySchema(schema = @Schema(implementation = GetUserResponseDTO.class))))
    public Page<GetUserResponseDTO> getUsers(
            @RequestHeader(name = "exclude-usernames", required = false, defaultValue = "") List<String> excludeUsernames,
            @RequestHeader(required = false, defaultValue = "") List<String> usernames,
            @RequestHeader(name = "ldap-groups", required = false, defaultValue = "") List<LdapGroup> ldapGroups,
            @RequestParam(required = false, defaultValue = "") String searchParameter,
            @PageableDefault(direction = Sort.Direction.ASC, sort = {"firstName", "lastName", "username"})
            Pageable p) {
        return criteriaBuilderExampleService.getUsers(usernames, excludeUsernames, ldapGroups, searchParameter, p);
    }
}
