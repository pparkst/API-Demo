package com.pparkst.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pparkst.api.domain.Board;
import com.pparkst.api.mapper.BoardMapper;
import com.pparkst.api.repository.BoardRepository;
import com.pparkst.api.web.dto.BoardCreateRequestDto;
import com.pparkst.api.web.dto.BoardResponseDto;
import com.pparkst.api.web.dto.BoardUpdateRequestDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
    
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;
    private final static Logger logger = LoggerFactory.getLogger(BoardService.class);

    public BoardResponseDto saveNewBoard(BoardCreateRequestDto boardCreateRequestDto) {
        logger.info("Saving new board");
        final Board board = this.boardMapper.toEntity(boardCreateRequestDto);
        board.softCreate();
        logger.debug("Board saved: " + board.getId() + " " + board.getTitle() + " " + board.getContent() + " " + board.getMember_No() + " " + board.getCreatedAt() + " " + board.getUpdatedAt() + " " + board.getIsDeleted() + " " + board.getDeletedAt());
        return boardMapper.toResponseDto(boardRepository.save(board));
    }
 
    @Transactional
    public BoardResponseDto editBoard(Long id, BoardUpdateRequestDto boardUpdateRequestDto) {
        logger.info("Editing board");
        logger.debug("Editing board by id: " + id);
        logger.debug("Received content: " + boardUpdateRequestDto.getTitle() + " " + boardUpdateRequestDto.getContent());
        final Board boardOrigin = boardRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new RuntimeException("해당하는 보드가 없습니다."));
        boardOrigin.softUpdate(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());
        return boardMapper.toResponseDto(boardOrigin);
    }

    public BoardResponseDto findBoardById(Long id) {
        logger.info("Finding board by id: " + id);
        return boardMapper.toResponseDto(boardRepository.findByIdAndIsDeletedFalse(id)
        .orElseThrow(() -> new RuntimeException("해당하는 보드가 없습니다.")));
    }

    @Transactional
    public void deleteBoardById(Long id) {
        logger.info("Deleting board by id: " + id);
        final Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("해당하는 보드가 없습니다"));
        board.softDelete();
    }
}
