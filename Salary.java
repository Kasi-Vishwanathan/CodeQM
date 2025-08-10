public class EmployeeSalary {
    public static void main(String[] args) {
        String empName;
        int empId;
        double basicSalary;
        double hra;
        double da;
        double grossSalary;

        empName = null;  // BUG: empName is null, will cause NullPointerException if used
        empId = 101;
        basicSalary = 0; // BUG: zero salary, leads to zero pay but no check

        hra = basicSalary * 0.1;   // House Rent Allowance
        da = basicSalary * 0.15;   // Dearness Allowance
        grossSalary = basicSalary + hra + da;

        System.out.println("Employee Details:");
        System.out.println("Name: " + empName.toUpperCase());  // NullPointerException here
        System.out.println("ID: " + empId);
        System.out.println("Basic Salary: " + basicSalary);
        System.out.println("HRA: " + hra);
        System.out.println("DA: " + da);
        System.out.println("Gross Salary: " + grossSalary);

        // Calculate tax without checking salary limits
        double tax;
        if (grossSalary > 50000) {
            tax = grossSalary * 0.3;
        } else if (grossSalary > 20000) {
            tax = grossSalary * 0.2;
        } else {
            tax = grossSalary * 0.1;
        }

        double netSalary = grossSalary - tax;
        System.out.println("Tax deducted: " + tax);
        System.out.println("Net Salary: " + netSalary);

        // Array with fixed size but iterating beyond length
        int[] monthlySales = new int[5];
        monthlySales[0] = 10000;
        monthlySales[1] = 12000;
        monthlySales[2] = 11000;
        monthlySales[3] = 9000;
        monthlySales[4] = 13000;

        System.out.println("Monthly Sales:");
        for (int i = 0; i <= monthlySales.length; i++) {  // BUG: i <= length causes ArrayIndexOutOfBoundsException
            System.out.println("Month " + (i + 1) + ": " + monthlySales[i]);
        }

    }
}
