package hap_org.core.map.dto;

import com.hand.hap.system.dto.ResponseData;

public class leBaseResponse extends ResponseData {
    private Object data;

    private Status status = new Status();

    public leBaseResponse(){}

    public leBaseResponse(Object data){
        this();
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public leBaseResponse.Status getStatus() {
        return status;
    }

    public void setStatus(leBaseResponse.Status status) {
        this.status = status;
    }

    public static class Status {
        private int code = 0;
        private String message = "success";

        public Status() {

        }

        public Status(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}