# Task Management RESTful API Design

## Base URL
/api
# User API
## 1. Lấy danh sách tất cả người dùng
**Method**

GET

**Endpoint**

/api/users

## 2. Tạo người dùng mới

**Method**

POST

**Endpoint**

/api/users

**Request Body**

json
{
    "name": "Nguyen Van A",
    "email": "a@gmail.com",
    "role": "USER"
}

## 3. Cập nhật vai trò người dùng

**Method**


PUT


**Endpoint**


/api/users/{id}/role


**Request Body**

json
{
    "role": "ADMIN"
}

## 4. Xóa người dùng

**Method**


DELETE


**Endpoint**


/api/users/{id}

## 5. Liệt kê toàn bộ công việc của một người dùng

**Method**


GET


**Endpoint**


/api/users/{id}/tasks


# Task API

## 6. Lấy toàn bộ danh sách công việc

**Method**


GET


**Endpoint**


/api/tasks

## 7. Tạo công việc mới

**Method**


POST


**Endpoint**


/api/tasks


**Request Body**

json
{
    "title": "Hoàn thành bài tập",
    "description": "Làm bài Spring Boot",
    "priority": "HIGH",
    "status": "TODO"
}

## 8. Cập nhật trạng thái công việc

**Method**


PATCH


**Endpoint**


/api/tasks/{id}/status


**Request Body**

json
{
    "status": "DONE"
}

## 9. Xóa công việc

**Method**


DELETE


**Endpoint**


/api/tasks/{id}

## 10. Tìm các công việc có độ ưu tiên HIGH

**Method**


GET

**Endpoint**

/api/tasks?priority=HIGH

## 11. Tìm các công việc có độ ưu tiên HIGH và của người dùng id = 1

**Method**

GET

**Endpoint**

/api/tasks?priority=HIGH&userId=1

## 12. Gắn công việc cho người dùng

**Method**

PUT

**Endpoint**

/api/tasks/{taskId}/user

**Request Body**

json
{
    "userId": 1
}

# Validation

## User

- name không được để trống.
- email không được để trống.
- role chỉ nhận:
    - ADMIN
    - USER

## Task

- title không được để trống.
- priority chỉ nhận:
    - LOW
    - MEDIUM
    - HIGH
- status chỉ nhận:
    - TODO
    - IN_PROGRESS
    - DONE