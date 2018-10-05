package org.hackathon.distributedfs.distributedFs.repository;

import org.hackathon.distributedfs.distributedFs.model.entity.File;
import org.springframework.data.repository.CrudRepository;

public interface FileRepository extends CrudRepository<File,Long> {
}
