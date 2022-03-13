package com.unexpect.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Facility {

    private int facilityId;
    private String facilityName;
    private int facilityStatus;
    private Date facilityStartTime;
    private String facilityNote;
    private int meetingRoomId;

}
