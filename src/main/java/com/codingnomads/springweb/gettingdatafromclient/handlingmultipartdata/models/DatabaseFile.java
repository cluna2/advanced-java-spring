/* CodingNomads (C)2024 */

package com.codingnomads.springweb.gettingdatafromclient.handlingmultipartdata.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "database_files")
public class DatabaseFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fileName;
    private String fileType;

    @Lob
    @Column(columnDefinition = "LONGBLOB NOT NULL")
    private byte[] data;

    @Transient
    private String downloadUrl;
}
