package com.example.simplememo_2.Dto;

import com.example.simplememo_2.Entity.Memo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoRequestDto {
    private String title;
    private String content;

    public Memo toEntity() {
        return Memo.builder()
                .title(title)
                .content(content)
                .build();
    }
}
