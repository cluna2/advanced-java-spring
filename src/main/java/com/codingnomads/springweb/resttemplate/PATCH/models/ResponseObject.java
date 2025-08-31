/* CodingNomads (C)2024 */
package com.codingnomads.springweb.resttemplate.PATCH.models;

import lombok.Data;

@Data
public class ResponseObject {
    User data;
    Error error;
    String status;
}
