package com.dakshay.userfeed.dto;


import com.dakshay.userfeed.enums.AffectionContextType;
import lombok.Data;

@Data
public class AffectionRequestDTO {

    private Long contextId;
    private AffectionContextType contextType;
    private Long userId;



    public String getCacheKey(){
        return this.contextId + "-"+ this.contextType + "-" + this.userId;
    }
}
