package org.example.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * TableName news_type
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Type implements Serializable {
    @TableId
    private Integer tid;

    private String tname;

    private Integer version;

    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}