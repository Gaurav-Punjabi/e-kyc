package org.hackathon.distributedfs.distributedFs.repository;

import org.hackathon.distributedfs.distributedFs.model.entity.FilePart;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FilePartRepository extends CrudRepository<FilePart,Long> {
    Iterable<FilePart> findAllByFileId(Long fileId);
}
