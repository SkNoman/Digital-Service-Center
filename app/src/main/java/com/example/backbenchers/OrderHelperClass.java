package com.example.backbenchers;

public class OrderHelperClass {



    String username;
    String phone;
    String city;
    String service_type;
    String service_id;
    String message;

    public OrderHelperClass() {
    }

    public OrderHelperClass(String username, String phone, String city, String service_type,String service_id, String message) {
        this.username = username;
        this.phone = phone;
        this.city = city;
        this.service_type = service_type;
        this.service_id = service_id;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getService_type() {
        return service_type;
    }

    public void setService_type(String service_type) {
        this.service_type = service_type;
    }

    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
