package com.pparkst.api.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pparkst.api.service.BoardService;
import com.pparkst.api.service.DummyService;
import com.pparkst.api.web.dto.BoardCreateRequestDto;
import com.pparkst.api.web.dto.BoardResponseDto;
import com.pparkst.api.web.dto.BoardUpdateRequestDto;

import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final DummyService dummyService;
    private final Logger logger = LoggerFactory.getLogger(BoardController.class);

    @PostMapping()
    public ResponseEntity<BoardResponseDto> addNewBoard(@RequestBody BoardCreateRequestDto boardCreateRequestDto) {
        logger.info("Received request to add new board");
        logger.debug("Received content: " + boardCreateRequestDto.getTitle() + " " + boardCreateRequestDto.getContent() + " " + boardCreateRequestDto.getMember_No());
        BoardResponseDto boardResponseDto = boardService.saveNewBoard(boardCreateRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(boardResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long id) {
        logger.info("Received request to get board");
        logger.debug("Received request to get board by id: " + id);
        BoardResponseDto boardResponseDto = boardService.findBoardById(id);
        return ResponseEntity.ok(boardResponseDto);
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<BoardResponseDto> editBoard(@PathVariable Long id, @RequestBody BoardUpdateRequestDto boardUpdateRequestDto) {
        logger.info("Received request to edit board");
        logger.debug("Received request to edit board by id: " + id);
        logger.debug("Received content: " + boardUpdateRequestDto.getTitle() + " " + boardUpdateRequestDto.getContent());
        BoardResponseDto boardResponseDto = boardService.editBoard(id,boardUpdateRequestDto);
        return ResponseEntity.ok(boardResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeBoard(@PathVariable Long id) {
        logger.info("Received request to delete board");
        logger.debug("Received request to delete board by id: " + id);
        boardService.deleteBoardById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/dummy/{count}")
    public ResponseEntity<Void> createDummyData(@PathVariable Long count) {
        dummyService.createBoardDummyData(count);
        return ResponseEntity.noContent().build();
    }
    
    
    
}
