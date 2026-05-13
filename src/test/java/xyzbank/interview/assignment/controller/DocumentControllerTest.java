
package xyzbank.interview.assignment.controller;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.context.annotation.Import;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;

import org.springframework.mock.web.MockMultipartFile;

import org.springframework.test.web.servlet.MockMvc;

import xyzbank.interview.assignment.exception.GlobalExceptionHandler;

import xyzbank.interview.assignment.security.JwtAuthenticationFilter;
import xyzbank.interview.assignment.security.JwtUtil;

import xyzbank.interview.assignment.service.DocumentService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DocumentController.class)

@AutoConfigureMockMvc(addFilters = false)

@Import(GlobalExceptionHandler.class)

class DocumentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DocumentService documentService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @MockBean
    private JwtUtil jwtUtil;

	/*
	 * @Test void shouldUploadDocumentSuccessfully() throws Exception {
	 * 
	 * MockMultipartFile file = new MockMultipartFile( "file", "test.pdf",
	 * "application/pdf", "sample".getBytes() );
	 * 
	 * when(documentService.getCustomerIdByUsername("john123")) .thenReturn(1L);
	 * 
	 * doNothing().when(documentService) .uploadDocumentAsync( anyLong(),
	 * any(byte[].class), anyString(), anyString() );
	 * 
	 * mockMvc.perform( multipart("/documents/upload") .file(file) .principal(() ->
	 * "john123") ) .andDo(print()) .andExpect(status().isOk()); }
	 */

	/*
	 * @Test void shouldFailUploadDocument() throws Exception {
	 * 
	 * MockMultipartFile file = new MockMultipartFile( "file", "test.pdf",
	 * "application/pdf", "sample".getBytes() );
	 * 
	 * when(documentService.getCustomerIdByUsername(anyString())) .thenThrow( new
	 * RuntimeException("Upload failed") );
	 * 
	 * mockMvc.perform( multipart("/documents/upload") .file(file) .principal(() ->
	 * "john123") ) .andDo(print()) .andExpect(status().isBadRequest()); }
	 */
	/*
	 * @Test void shouldDownloadDocumentSuccessfully() throws Exception {
	 * 
	 * Resource resource = new ByteArrayResource( "sample".getBytes() ) {
	 * 
	 * @Override public String getFilename() { return "test.pdf"; } };
	 * 
	 * when(documentService.downloadDocument(anyString())) .thenReturn(resource);
	 * 
	 * mockMvc.perform( get("/documents/download") .principal(() -> "john123") )
	 * .andDo(print()) .andExpect(status().isOk()); }
	 */

	/*
	 * @Test void shouldFailDownloadDocument() throws Exception {
	 * 
	 * when(documentService.downloadDocument(anyString())) .thenThrow( new
	 * RuntimeException("Download failed") );
	 * 
	 * mockMvc.perform( get("/documents/download") .principal(() -> "john123") )
	 * .andDo(print()) .andExpect(status().isInternalServerError()); }
	 */
}

