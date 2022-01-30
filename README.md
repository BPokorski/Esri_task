# Esri_task
CRUD operations on employee.

## Features

* Adding new Employee
* Validate personal id
* Compute birth date and sex based on personal id
* Assign Employee to Department based on job position
* Set Employee supervisor of Department
* Delete employee
* Retrieve data about Employee
* Retrieve data about Department
* Update email or job position of employee


## API Endpoints

here is complete list of available endpoints

### GET Methods

* `localhost:8080/department/{id}`  
Endpoint parameters: id of department  
Returns: Department data with given id.

* `localhost:8080/employee`  
Endpoint parameters: page and size  
Returns: Paged Employees data.

* `localhost:8080/employee/{id}`  
Endpoint parameters: id of employee  
Returns: Employee data with given id.

* `localhost:8080/job_position`  
Endpoint parameters: jobPostion, page and size  
Returns: Paged Employees data filtered by given jobPosition.

### POST Methods

* `localhost:8080/employee`  
Endpoint parameters: firstName, lastName, personalId, email, jobPosition  
Returns: Newly created Employee with given parameters, birthDate  
calculated from personalId and Department based on given jobPosition or  
Bad Request response with proper message.

### PUT Methods

* `localhost:8080/employee/{id}/job_position`  
Endpoint parameters: id of employee, new jobPostion  
Returns: Updated Employee with given id or Bad Request response with message.

* `localhost:8080/employee/{id}/email`  
Endpoint parameters: id of employee, new email  
Returns: Updated Employee with given id or Bad Request response with message.

* `localhost:8080/department/{id}/supervisor`  
Endpoint parameters: id of department, new supervisorId  
Returns: Updated Department with given id or Bad Request response with message.

### DELETE Methods

* `localhost:8080/employee/{id}`  
Endpoint parameters: id of employee  
Returns: Message with successfully deleted employee.

