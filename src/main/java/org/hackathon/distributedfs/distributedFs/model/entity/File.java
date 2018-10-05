package org.hackathon.distributedfs.distributedFs.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "files")
public class File  extends BaseModel{
    @Id
    @GeneratedValue
    @Column(name = "file_id")
    private Long id;

    @Column(name = "file_name")
    private String name;

    @Column(name = "file_size")
    private Long sie;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSie() {
        return sie;
    }

    public void setSie(Long sie) {
        this.sie = sie;
    }
}
