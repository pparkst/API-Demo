package com.pparkst.api.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pparkst.api.service.BoardService;
import com.pparkst.api.web.dto.BoardCreateRequestDto;
import com.pparkst.api.web.dto.BoardResponseDto;
import com.pparkst.api.web.dto.BoardUpdateRequestDto;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {
    
    private final BoardService boardService; 

    @PostMapping()
    public ResponseEntity<BoardResponseDto> addNewBoard(@RequestBody BoardCreateRequestDto boardCreateRequestDto) {
        System.out.println("Received content: " + boardCreateRequestDto.getTitle() + " " + boardCreateRequestDto.getContent() + " " + boardCreateRequestDto.getMember_No());
        BoardResponseDto boardResponseDto = boardService.saveNewBoard(boardCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(boardResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long id) {
        BoardResponseDto boardResponseDto = boardService.findBoardById(id);
        return ResponseEntity.ok(boardResponseDto);
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> editBoard(@PathVariable Long id, @RequestBody BoardUpdateRequestDto boardUpdateRequestDto) {
        BoardResponseDto boardResponseDto = boardService.editBoard(id,boardUpdateRequestDto);
        return ResponseEntity.ok(boardResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeBoard(@PathVariable Long id) {
        boardService.deleteBoardById(id);
        return ResponseEntity.noContent().build();
    }
    
    
}
