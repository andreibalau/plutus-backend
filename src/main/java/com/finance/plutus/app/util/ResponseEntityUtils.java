package com.finance.plutus.app.util;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

/** Plutus Created by Catalin on 12/31/2020 */
public class ResponseEntityUtils {

  public static ResponseEntity<Resource> prepareDownloadResponse(byte[] content, String filename) {
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
    headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
    headers.add("Pragma", "no-cache");
    headers.add("Expires", "0");
    ByteArrayResource resource = new ByteArrayResource(content);
    return ResponseEntity.ok()
        .headers(headers)
        .contentLength(content.length)
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(resource);
  }
}
