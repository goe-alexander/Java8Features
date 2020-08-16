package utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@Builder
@ToString
public class MonitorStatus {
    Date timestamp;
    Integer duration;
    String ip;
    String status;
}
