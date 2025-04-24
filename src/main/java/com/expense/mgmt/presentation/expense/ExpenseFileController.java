package com.expense.mgmt.presentation.expense;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.expense.mgmt.application.ExpenseService;
import com.expense.mgmt.domain.model.dto.Expense;
import com.expense.mgmt.domain.model.dto.FileRequest;
import com.expense.mgmt.domain.model.dto.FileStreamInfo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/expense")
public class ExpenseFileController {

    private final ExpenseService expenseService;


    @RequestMapping(method = RequestMethod.POST, value = "/upload/{expenseId}", consumes = {
            MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public Expense uploadFile(@PathVariable("expenseId") Long expenseId, @RequestParam("file") MultipartFile file) {
        return expenseService.uploadFile(expenseId, file);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/download/file",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE
            }
            //produces = {
            //        MediaType.APPLICATION_OCTET_STREAM_VALUE,
            //        MediaType.APPLICATION_JSON_VALUE
            //},
            //headers = {
            //        HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE
            // }
    )
    public FileStreamInfo downloadFile(@RequestBody FileRequest request) {
        //return expenseService.downloadFile(request.path())
        //        .map(fileStreamInfo -> ResponseEntity.status(HttpStatus.OK)
        //                .header(HttpHeaders.CONTENT_DISPOSITION, ContentDisposition.attachment().filename(request.path()).build().toString())
        //                .body(new InputStreamResource(fileStreamInfo.bytes()))
        //        ).orElseGet(() -> ResponseEntity.notFound().build());
        return expenseService.downloadFile(request.path()).get();
    }


    @RequestMapping(method = RequestMethod.POST, value = "/download/file2",
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE
            }
    )
    public Mono<FileStreamInfo> reactorDownloadFile(@RequestBody FileRequest request) {
        return expenseService.downloadFileAsync(request.path());
    }

}
