package com.pparkst.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pparkst.api.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
    public abstract Optional<Board> findByIdAndIsDeletedFalse(Long id);

    
    // public Boolean AddBoard(Board board) {
    //     return true;
    // }

    // public Boolean EditBoard(Board board) {

    //     return true;
    // }

    // public Boolean DeleteBoardById(int id) {
    //     return true;
    // }

    // public Board GetBoardById(int id) {

    //     Board board = new Board();

    //     return board;
    // }

    // public List<Board> GetBoardList() {
    //     List<Board> boardList = new ArrayList<>();
    //     return boardList;
    // }
}
