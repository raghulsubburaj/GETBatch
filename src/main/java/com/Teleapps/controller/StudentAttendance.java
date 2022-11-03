package com.Teleapps.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class StudentAttendance {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("WELCOME");
		System.out.println(java.time.LocalDate.now());
		System.out.println("MONTHLY ATTENDANCE");
		while (true) {
			System.out.println("1 IS FOR PUT ATTENDANCE 2 IS FOR CHECK ATTENDANCE PERCENTAGE");
			while (true) {
				int at = sc.nextInt();

				if (at == 1) {
					attendance();
					break;
				} else if (at == 2) {
					percentage();
					break;
				} else {
					System.out.println("enter correct choice");
				}
			}
			System.out.println("do you want to continue: 1.yes 2.no");
			int fi = sc.nextInt();
			if (fi == 2) {
				System.out.println("thank you");
				break;
			}
		}

	}

	private static void percentage() {
		LocalDate startday = LocalDate.parse("2022-10-01");
		LocalDate currentday = LocalDate.now();
		System.out.println("enter student name");
		int daysDiff = (int) startday.until(currentday, ChronoUnit.DAYS);
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:sqlserver://192.168.168.12:1433;databaseName=New_joinee_2022", "NewJoinee2022", "P@ssw0rd");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from ILAKKIYA_STUDENT");
			String name = sc.next();
			String name1 = " ";
			while (rs.next()) {
				name1 = rs.getString(2);
				// System.out.println(name1);
				if (name.equalsIgnoreCase(name1)) {
					int val = rs.getInt(3);
					int val1 = (val * 100) / daysDiff;
					// System.out.println(daysDiff);
					// System.out.println(val);
					System.out.println(name + " " + "attendance percentage:" + val1 + "%");// break;
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
	}

	private static void attendance() {
		LocalDate startday = LocalDate.parse("2022-10-01");
		LocalDate currentday = LocalDate.now();
		int daysDiff = (int) startday.until(currentday, ChronoUnit.DAYS);
		System.out.println("P FOR PRESENT A FOR ANBSENT");
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:sqlserver://192.168.168.12:1433;databaseName=New_joinee_2022", "NewJoinee2022", "P@ssw0rd");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from ILAKKIYA_STUDENT");
			while (rs.next()) {

				System.out.println("student name:" + " " + rs.getString(2));

				while (true) {
					String c = sc.next();
					if (c.equalsIgnoreCase("p")) {
						int val = rs.getInt(3);
						int inc = val + 1;
						PreparedStatement st2 = con.prepareStatement("update ILAKKIYA_STUDENT set ATTENDANCE=" + inc
								+ "where STUDENT_NAME ='" + rs.getString(2) + "';");
						int val1 = (inc * 100) / daysDiff;
						PreparedStatement st1 = con.prepareStatement("update ILAKKIYA_STUDENT set PERCENTAGE=" + val1
								+ "where STUDENT_NAME ='" + rs.getString(2) + "';");
						int i = st1.executeUpdate();
						int j = st2.executeUpdate();
						break;
					} else if (c.equalsIgnoreCase("a")) {
						break;
					} else {
						System.out.println("enter correct character");

					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}