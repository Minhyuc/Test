package com.example.simplememo_2.Controller;

import com.example.simplememo_2.CommonResponse;
import com.example.simplememo_2.Dto.MemoRequestDto;
import com.example.simplememo_2.Dto.MemoResponseDto;
import com.example.simplememo_2.Entity.Memo;
import com.example.simplememo_2.Service.MemoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/memo")
@AllArgsConstructor

public class MemoController {
    public final MemoService memoService;

    @PostMapping
    public ResponseEntity<CommonResponse<MemoResponseDto>> memoCreate(@RequestBody MemoRequestDto request) {
        Memo memo = memoService.createMemo(request);
        MemoResponseDto response = new MemoResponseDto(memo);
        return ResponseEntity.ok()
                .body(CommonResponse.<MemoResponseDto>builder()
                        .statusCode(200)
                        .msg("메모 생성이 완료되었습니다.")
                        .data(response)
                        .build());
    }

    //메모 단건 조회
    @GetMapping("/{memoId}")
    public ResponseEntity<CommonResponse<MemoResponseDto>> getMemo(@PathVariable Long memoId) {
        Memo memo = memoService.getMemo(memoId);
        MemoResponseDto response = new MemoResponseDto(memo);
        return ResponseEntity.ok()
                .body(CommonResponse.<MemoResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("단건 조회가 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    //메모 전체 조회
    @GetMapping
    public ResponseEntity<CommonResponse<List<MemoResponseDto>>> getMemos() {
        List<Memo> memos = memoService.getMemos();
        List<MemoResponseDto> response = memos.stream()
                .map(MemoResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok()
                .body(CommonResponse.<List<MemoResponseDto>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("목록 조회가 완료 되었습니다.")
                        .data(response)
                        .build());
    }

    //수정
    @PutMapping("/{memoId}")
    public ResponseEntity<CommonResponse<MemoResponseDto>> updateMemo(
            @PathVariable Long memoId,
            @RequestBody MemoRequestDto request) {
        Memo memo = memoService.updateMemo(memoId, request);
        MemoResponseDto response = new MemoResponseDto(memo);
        return ResponseEntity.ok()
                .body(CommonResponse.<MemoResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("메모 수정이 완료되었습니다.")
                        .data(response)
                        .build());
    }

    //삭제
    @DeleteMapping("/{memoId}")
    public ResponseEntity<CommonResponse<Void>> deleteMemo(@PathVariable Long memoId) {
        memoService.deleteMemo(memoId);
        return ResponseEntity.ok()
                .body(CommonResponse.<Void>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("메모 삭제가 완료되었습니다.")
                        .build());
    }
}
