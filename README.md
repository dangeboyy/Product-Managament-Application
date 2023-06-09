## Product Management Application

In this homework you are expected to implement and design a “ **_Product Management Application_** ”
in Java. You should fulfill the concepts of:

- Composite Design Pattern
- State Design Pattern
- GRASP Principles
1. In this application managers can control their products’ lifecycle. A manager is responsible from a
product and each product consist of parts and assemblies. Managers assign an employee to a
part and each employee is responsible from a part.

**For example:** A manager is responsible from an Engine. Manager creates parts and assemblies of the
engine. For each created part, manager creates an employee. So as an example, an employee is
responsible from Crankshaft.

#Engine (product)

Pistons (assembly) Engine Block (assembly) Crankshaft (part)

Lifters (assembly) Camshaft (part) parts...

parts...

2. Each assembly and each part will have an id, title, and status.
    Statuses are:
       - **Not Started**
       - **In Progress**
       - **Complete**
3. There is an admin of the program and admin creates managers and their products.
    Managers create assemblies and parts. For each part, managers create an employee. Each part
    and each assembly are created as **Not Started**.
4. Employees can change the status of their related part. When the status of a part is changed,
    connected assemblies also change their statuses automatically. If one of the parts of an assembly
    changes its status as **In Progress** or **Complete** , assembly also changes its status as **In Progress**. If
    all of the parts and assemblies of an assembly are in **Complete** status, related assembly will be
    changed as **Complete** automatically.
5. The admin, managers and employees should be able to login to the designed system.


```
Admin should be able to see all the managers, employees, and related product trees.
Managers should be able to see their related employees, and related product tree.
Employees should be able to see only their part and should be able to change the status of the
part.
```
6. Application should run continuously unless the user wants to exit (not logout). Therefore, you are
    expected to handle with possible errors and exceptions.
7. The data should be stored in a JSON file.
8. You are expected to follow GRASP principles and write clean, readable, and tester-friendly code
    (make the selections with numbers).



