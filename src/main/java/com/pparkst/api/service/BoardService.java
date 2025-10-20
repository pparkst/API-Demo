package com.pparkst.api.service;

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

    public BoardResponseDto saveNewBoard(BoardCreateRequestDto boardCreateRequestDto) {
        System.out.println("saveNewBoard");
        final Board board = this.boardMapper.toEntity(boardCreateRequestDto);
        board.softCreate();
        System.out.println("board: " + board.getId() + " " + board.getTitle() + " " + board.getContent() + " " + board.getMember_No() + " " + board.getCreatedAt() + " " + board.getUpdatedAt() + " " + board.getIsDeleted() + " " + board.getDeletedAt());
        return boardMapper.toResponseDto(boardRepository.save(board));
    }
 
    @Transactional
    public BoardResponseDto editBoard(Long id, BoardUpdateRequestDto boardUpdateRequestDto) {
        final Board originBoard = boardRepository.findByIdAndIsDeletedFalse(id).orElseThrow(() -> new RuntimeException("해당하는 보드가 없습니다."));
        originBoard.softUpdate(boardUpdateRequestDto.getTitle(), boardUpdateRequestDto.getContent());
        return boardMapper.toResponseDto(originBoard);
    }

    public BoardResponseDto findBoardById(Long id) {
        return boardMapper.toResponseDto(boardRepository.findByIdAndIsDeletedFalse(id)
        .orElseThrow(() -> new RuntimeException("해당하는 보드가 없습니다.")));
    }

    public void deleteBoardById(Long id) {
        final Board board = boardRepository.findById(id).orElseThrow(() -> new RuntimeException("해당하는 보드가 없습니다"));
        board.softDelete();
        boardRepository.save(board);
    }
}
