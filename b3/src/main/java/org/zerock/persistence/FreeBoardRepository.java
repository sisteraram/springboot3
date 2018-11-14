package org.zerock.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.FreeBoard;

public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long> {
	
	@Query("select b from FreeBoard b where b.bno > 0")
	public List<FreeBoard> findList(Pageable pageable);
	
	@Query("SELECT b, count(r) from FreeBoard b"
			+ " LEFT OUTER JOIN FreeReply r on b.bno = r.board "
			+ "group by b")
	public Page<Object[]> listPage(Pageable pageable);
	
	@Query("SELECT b, count(r) from FreeBoard b"
			+ " LEFT OUTER JOIN FreeReply r on b.bno = r.board "
			+ "where b.title like %?1% "
			+ "group by b")
	public Page<Object[]> listByTitlePage(String keyword, Pageable pageable);
	
	@Query("SELECT b, count(r) from FreeBoard b"
			+ " LEFT OUTER JOIN FreeReply r on b.bno = r.board "
			+ "where (b.title like %?1% or b.content like %?1%) "
			+ "group by b")
	public Page<Object[]> listByTitleOrContentPage(String keyword, Pageable pageable);

}
