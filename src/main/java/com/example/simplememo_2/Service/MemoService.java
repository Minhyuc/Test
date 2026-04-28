package com.example.simplememo_2.Service;

import com.example.simplememo_2.Dto.MemoRequestDto;
import com.example.simplememo_2.Entity.Memo;
import com.example.simplememo_2.Repository.MemoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class MemoService {
    private final MemoRepository memoRepository;
    public Memo createMemo(MemoRequestDto request) {
        Memo memo = request.toEntity();
        return memoRepository.save(memo);
    }

    //메모 단건 조회
    public Memo getMemo(Long memoId) {
        return memoRepository.findById(memoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 메모가 존재하지 않습니다."));
    }

    // 메모 전체 조회
    public List<Memo> getMemos() {
        return memoRepository.findAll();
    }

    //수정
    public Memo updateMemo(Long memoId, MemoRequestDto request) {
        Memo memo = getMemo(memoId); // 메모 단건 조회 기능을 이용하여 메모가 데이터베이스에 실존하는지 확인
        memo.setTitle(request.getTitle());
        memo.setContent(request.getContent());
        return memoRepository.save(memo); // Repository의 save 메소드를 이용하여 수정된 메모를 데이터베이스에 저장
    }

    //삭제
    public void deleteMemo(Long memoId) {
        Memo memo = getMemo(memoId); // 메모 단건 조회 기능을 이용하여 메모가 데이터베이스에 실존하는지 확인
        memoRepository.delete(memo); // Repository의 delete 메소드를 이용하여 메모를 데이터베이스에서 삭제
    }
}
