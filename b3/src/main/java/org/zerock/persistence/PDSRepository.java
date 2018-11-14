package org.zerock.persistence;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.zerock.domain.PDS;

public interface PDSRepository extends CrudRepository<PDS, Long> {
	
	@Modifying
	@Query("UPDATE FROM PDSFile f set f.fname = ?2 "
			+ "WHERE f.fno = ?1 ")
	public int updatePDSFile(Long fno, String newFileName);
	
	@Modifying
	@Query("DELETE FROM PDSFile f where f.fno = ?1")
	public int deletePDSFile(Long fno);

}
