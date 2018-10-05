package org.hackathon.distributedfs.distributedFs.repository;

import org.hackathon.distributedfs.distributedFs.model.entity.FilePart;
import org.springframework.data.repository.CrudRepository;

public interface FilePartRepository extends CrudRepository<FilePart,Long> {
}
