package org.hackathon.distributedfs.distributedFs.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "file_parts")
public class FilePart extends BaseModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "part_id")
    private Long id;

    @Column(name = "size")
    private Long size;

    /*@Column(name = "content")
    private String content;*/
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "file_id" , referencedColumnName = "file_id")
    private File file;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "resides_on" , referencedColumnName = "server_id")
    private Server server;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    /*public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }*/
}
