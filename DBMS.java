package Database;

import java.lang.*;
import java.util.*;

class Student {
    public int RID;
    public String Name;
    public int Salary;
    private static int Generator;

    static {
        Generator = 0;
    }

    public Student(String str, int value) {

        this.RID = ++Generator;
        this.Name = str;
        this.Salary = value;
    }

    public void DisplayData() {

        System.out.println(this.RID + "\t" + this.Name + "\t" + this.Salary);
    }

}

public class DBMS {
    public LinkedList<Student> lobj;
    public int count = 0;

    public DBMS() {
        lobj = new LinkedList<>();
    }

    public void Authentication() {
        Scanner sobj = new Scanner(System.in);
        String password;
        System.out.print("Enter your password : ");
        password = sobj.nextLine();

        if ("omkar".equals(password)) {
            StartDBMS();
        } else {
            System.out.println("Incorrect password");
        }
    }

    public void StartDBMS() {
        Scanner sobj = new Scanner(System.in);
        System.out.println("Database started successfully...");
        String Query = " ";
        while (true) {
            System.out.print("\nDBMS console > ");
            Query = sobj.nextLine();
            Query = Query.toLowerCase();
            String tokens[] = Query.split(" ");
            int QuerySize = tokens.length;

            if (QuerySize == 1) {
                if ("help".equals(tokens[0])) {
                    System.out.println("\nThis application is used to demonstrate the  customised DBMS\n");
                    System.out.println("Exit : Terminate DBMS");
                    System.out.println("Display all data : select * from student");
                    System.out.println("Display data by id : select * from student where RID = 'enter_RID'");
                    System.out.println("Display data by name : select * from student where Name = 'enter_Name'");
                    System.out.println("Delete by id : delete from student where RID = 'enter_RID';");
                    System.out.println("Delete by name : delete from student where Name = 'enter_Name';");
                    System.out.println(
                            "Update the name : update student set Name = 'enter_Name' where RID = 'enter_RID'");
                    System.out.println("Insert Data : insert into student enter_Name enter_Salary");
                    System.out.println("Count : select count(*) from student");
                    System.out.println("Sum : select sum(Salary) from student");
                    System.out.println("Average : select avg(Salary) from student");
                    System.out.println("maximum : select max(Salary) from student");
                    System.out.println("minimum : select min(Salary) from student");
                    System.out.println();
                } else if ("exit".equals(tokens[0])) {
                    System.out.println("Thank you for using DBMS");
                    return;
                } else {
                    System.out.println("No such query found\nHelp : check manual");
                }

            }

            else if (QuerySize == 4) {
                if (("select".equals(tokens[0])) &&
                        ("*".equals(tokens[1])) &&
                        ("from".equals(tokens[2])) &&
                        ("student".equals(tokens[3]))) {

                    count = lobj.size();
                    DisplayAll();
                    System.out.println("\n" + count + " rows in a set");

                } else if (("select".equals(tokens[0])) &&
                        ("from".equals(tokens[2])) &&
                        ("student".equals(tokens[3]))) {
                    if ("count(*)".equals(tokens[1])) {

                        AggregateCount();
                    } else if ("sum(salary)".equals(tokens[1])) {
                        AggregateSum();
                    } else if ("avg".equals(tokens[1])) {
                        AggregateAvg();
                    } else if ("max(salary)".equals(tokens[1])) {
                        AggregateMax();
                    } else if ("min(salary)".equals(tokens[1])) {
                        AggregateMin();
                    } else {
                        System.out.println("enter valid query\nhelp : manual");
                    }

                } else {
                    System.out.println("enter valid query\nhelp : manual");
                }
            } else if (QuerySize == 5) {
                if ("insert".equals(tokens[0]) &&
                        ("into".equals(tokens[1])) &&
                        ("student".equals(tokens[2]))) {
                    if (((tokens[3]).isEmpty()) || ((tokens[4]).isEmpty())) {
                        System.out.println("enter valid query\nhelp : manual");
                        return;
                    }
                    try {
                        InsertData(tokens[3], Integer.parseInt(tokens[4]));
                        System.out.println("\n" + "Query OK, 1 row affected");
                    } catch (Exception e) {
                        System.out.println("enter valid query\nhelp : manual");
                    }
                } else {
                    System.out.println("enter valid query\nhelp : manual");
                }
            }

            else if (QuerySize == 7) {
                if (("delete".equals(tokens[0])) &&
                        ("from".equals(tokens[1])) &&
                        ("student".equals(tokens[2])) &&
                        ("where".equals(tokens[3])) &&
                        ("=".equals(tokens[5]))) {
                    if ("id".equals(tokens[4])) {
                        DeleteSpecific(Integer.parseInt(tokens[6]));
                    } else if ("name".equals(tokens[4])) {
                        DeleteSpecific(tokens[6]);
                        System.out.println("Query OK, " + count + " row affected");

                    } else {
                        System.out.println("enter valid query\nhelp : manual");
                    }
                } else {
                    System.out.println("enter valid query\nhelp : manual");
                }
            }

            else if (QuerySize == 8) {
                if (("select".equals(tokens[0])) &&
                        ("salary".equals(tokens[1])) &&
                        ("from".equals(tokens[2])) &&
                        ("student".equals(tokens[3])) &&
                        ("where".equals(tokens[4])) &&
                        ("rid".equals(tokens[5])) &&
                        ("=".equals(tokens[6]))) {

                    DisplaySpcific(Integer.parseInt(tokens[7]));
                    System.out.println("\n" + count + " rows in a set");
                }

                else if (("select".equals(tokens[0])) &&
                        ("*".equals(tokens[1])) &&
                        ("from".equals(tokens[2])) &&
                        ("student".equals(tokens[3])) &&
                        ("where".equals(tokens[4])) &&
                        ("name".equals(tokens[5])) &&
                        ("=".equals(tokens[6]))) {
                    DisplaySpcific(tokens[7]);
                    System.out.println("\n" + count + " rows in a set");
                } else {
                    System.out.println("enter valid query\nhelp : manual");
                }
            }

            else if (QuerySize == 10) {
                int num = 0;
                if (("update".equals(tokens[0])) &&
                        ("student".equals(tokens[1])) &&
                        ("set".equals(tokens[2])) &&
                        ("=".equals(tokens[4])) &&
                        ("where".equals(tokens[6])) &&
                        ("rid".equals(tokens[7])) &&
                        (("=".equals(tokens[8])))) {
                    if ((tokens[5]).isEmpty() && ((tokens[9]).isEmpty())) {
                        System.out.println("enter valid query\nhelp : manual");
                    }

                    if ("name".equals(tokens[3])) {
                        updateData(Integer.parseInt(tokens[9]), tokens[5]);
                        System.out.println("Query OK, " + count + " row affected");
                    }

                    if ("salary".equals(tokens[3])) {
                        updateData(Integer.parseInt(tokens[9]), Integer.parseInt(tokens[5]));
                        System.out.println("Query OK, " + count + " row affected");

                    }
                } else {
                    System.out.println("enter valid query\nhelp : manual");
                }
            } else {
                System.out.println("enter valid query\nhelp : manual");
            }
        }
    }

    public void InsertData(String str, int value) {
        boolean status = false;
        Student sobj = new Student(str, value);
        status = lobj.add(sobj);

        if (status == false) {
            System.out.println("enter valid query\nhelp : manual");
            return;
        }
    }

    public void DisplayAll() {
        System.out.println("\nRID\tName\tSalary");
        for (Student sref : lobj) {
            sref.DisplayData();
        }
    }

    // Display by rid
    public void DisplaySpcific(int rid) {
        count = 0;
        System.out.println("here");
        System.out.println("\nRID\tName\tSalary");
        for (Student sref : lobj) {
            if (sref.RID == rid) {
                count++;
                sref.DisplayData();
                break;
            }
        }
    }

    // Display by name
    public void DisplaySpcific(String str) {
        count = 0;

        System.out.println("\nRID\tName\tSalary");
        for (Student sref : lobj) {
            if (str.equals(sref.Name)) {
                count++;
                sref.DisplayData();
            }
        }
    }

    public void updateData(int rid, String name) {
        count = 0;
        for (Student sref : lobj) {
            if (sref.RID == rid) {
                sref.Name = name;
                count++;
            }

        }

    }

    public void updateData(int rid, int salary) {
        count = 0;
        for (Student sref : lobj) {
            if (sref.RID == rid) {
                sref.Salary = salary;
                count++;
            }

        }

    }

    public void DeleteSpecific(int rid) {
        int index = 0;
        count = 0;
        for (Student sref : lobj) {
            if (sref.RID == rid) {
                lobj.remove(index);
                count++;
                break;
            }

            index++;
        }
    }

    // delete by name
    public void DeleteSpecific(String name) {
        int index = 0;
        count = 0;
        for (Student sref : lobj) {
            if (sref.Name.equals(name)) {
                count++;
                lobj.remove(index);
                break;
            }
            index++;
        }
    }

    public void AggregateMax() {
        int max = 0;

        System.out.println("\nRID\tName\tSalary");
        for (Student sref : lobj) {
            if (max < sref.Salary) {
                max = sref.Salary;
            }
        }

        for (Student sref1 : lobj) {
            if (max == sref1.Salary) {
                sref1.DisplayData();
            }
        }
    }

    public void AggregateMin() {
        int min = lobj.getFirst().Salary;
        Student temp = lobj.getFirst();

        System.out.println("\nRID\tName\tSalary");
        for (Student sref : lobj) {
            if (min > sref.Salary) {
                min = sref.Salary;
                temp = sref;
            }
        }
        temp.DisplayData();
    }

    public void AggregateSum() {
        long sum = 0;

        for (Student sref : lobj) {
            sum = sum + sref.Salary;
        }

        System.out.println("Summation of salraies is " + sum);
    }

    public void AggregateAvg() {
        long sum = 0;

        for (Student sref : lobj) {
            sum = sum + sref.Salary;
        }

        System.out.println("Avergae of salraies is " + sum / lobj.size());
    }

    public void AggregateCount() {
        System.out.println("Number of record " + lobj.size());
    }

}
