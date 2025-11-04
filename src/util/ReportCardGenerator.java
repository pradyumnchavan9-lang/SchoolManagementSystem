package  util;

import model.Student;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class ReportCardGenerator{
    
    /**
     * Generates an HTML report card for the given student, saves it under ./reports/,
     * opens it in the default browser, and returns the generated file.
     */
    public static File generateReport(Student s)
    {
        if(s==null)
        {
            JOptionPane.showMessageDialog(null, "No Student provided to generate report.","Error",JOptionPane.ERROR_MESSAGE);
            return null;
        }
        try{
            // Ensure reports directory exists
            Path reportsDir=Paths.get("reports");
            if(!Files.exists(reportsDir)) Files.createDirectories(reportsDir);
            
            //FileName with timestamp to avoid overwritting
            String timestamp=new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String safeName=s.getUsername().replaceAll("\\W+","_");
            Path outFile=reportsDir.resolve("report_" + safeName + "_" + timestamp + ".html");

            String html=buildHtml(s);
            Files.write(outFile,html.getBytes(StandardCharsets.UTF_8));

            //Open in Default browser
            if(Desktop.isDesktopSupported())
            {
                Desktop.getDesktop().browse(outFile.toUri());
            }
            else{
                JOptionPane.showMessageDialog(null, "Report Created"  + outFile.toAbsolutePath(), "Report Generated", JOptionPane.INFORMATION_MESSAGE);
            }
            return outFile.toFile();
        }catch(Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to create report: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    private static String buildHtml(Student s)
    {
        StringBuilder sb=new StringBuilder();
        sb.append("<!doctype html>\n<html>\n<head>\n<meta charset='utf-8'/>\n");
                sb.append("<title>Report Card - ").append(escapeHtml(s.getName())).append("</title>\n");

        // Simple CSS for a clean printable look
        sb.append("<style>\n");
        sb.append("body { font-family: 'Segoe UI', Tahoma, sans-serif; margin: 24px; color: #222; }\n");
        sb.append(".card { max-width: 800px; margin: auto; border: 1px solid #ddd; padding: 20px; box-shadow: 0 2px 4px rgba(0,0,0,0.06); }\n");
        sb.append(".header { text-align: center; padding-bottom: 12px; border-bottom: 2px solid #eee; }\n");
        sb.append(".school-name { font-size: 22px; font-weight: 700; color: #2C3E50; }\n");
        sb.append(".subtitle { color: #555; margin-top: 4px; }\n");
        sb.append(".meta { margin-top: 14px; display:flex; justify-content:space-between; }\n");
        sb.append("table { width:100%; border-collapse: collapse; margin-top: 16px; }\n");
        sb.append("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }\n");
        sb.append("th { background: #2C3E50; color: #fff; }\n");
        sb.append(".summary { margin-top: 16px; display:flex; justify-content:space-between; }\n");
        sb.append(".footer { margin-top: 18px; font-size: 12px; color: #666; text-align: center; }\n");
        sb.append("@media print { .card { box-shadow:none; border:none; } }\n");
        sb.append("</style>\n");

        sb.append("</head>\n<body>\n<div class='card'>\n");

        // Header
        sb.append("<div class='header'><div class='school-name'>School Management System</div>");
        sb.append("<div class='subtitle'>Report Card</div></div>\n");

        // Student meta
        sb.append("<div class='meta'>");
        sb.append("<div><strong>Name:</strong> ").append(escapeHtml(s.getName())).append("<br/>");
        sb.append("<strong>Username:</strong> ").append(escapeHtml(s.getUsername())).append("</div>");
        sb.append("<div><strong>Attendance:</strong> ").append(String.format("%.2f", s.getAttendancePercentage())).append(" %<br/>");
        sb.append("<strong>Generated:</strong> ").append(new SimpleDateFormat("dd MMM yyyy HH:mm").format(new Date())).append("</div>");
        sb.append("</div>\n");

        // Subject table
        sb.append("<table>\n<thead><tr><th>Subject</th><th>Marks</th></tr></thead>\n<tbody>\n");

        double total = 0;
        int count = 0;
        for (Map.Entry<String, Integer> entry : s.getSubjects().entrySet()) {
            sb.append("<tr><td>").append(escapeHtml(entry.getKey())).append("</td>");
            sb.append("<td>").append(entry.getValue()).append("</td></tr>\n");
            total += entry.getValue();
            count++;
        }
        sb.append("</tbody>\n</table>\n");

        double average = count == 0 ? 0 : total / count;
        String grade = gradeFromAverage(average);

        // Summary
        sb.append("<div class='summary'>");
        sb.append("<div><strong>Total:</strong> ").append(String.format("%.0f", total)).append("</div>");
        sb.append("<div><strong>Average:</strong> ").append(String.format("%.2f", average)).append("</div>");
        sb.append("<div><strong>Grade:</strong> ").append(escapeHtml(grade)).append("</div>");
        sb.append("</div>\n");

        // Footer
        sb.append("<div class='footer'>This report was generated by the School Management System.</div>\n");

        sb.append("</div>\n</body>\n</html>");
        return sb.toString();
    
    }
    private static String gradeFromAverage(double avg)
    {
        if(avg>=90) return "A+";
        if(avg>=80) return "A";
        if(avg>=70) return "B";
        if (avg >= 70) return "B";
        if (avg >= 60) return "C";
        if (avg >= 50) return "D";
        return "F";
    }
    private static String escapeHtml(String text)
    {
        if(text==null) return "";
        return text.replace("&","&amp;").replace("<","&lt;").replace(">","&gt;")
                   .replace("\"","&quot;").replace("'","&#39;");
    }
}